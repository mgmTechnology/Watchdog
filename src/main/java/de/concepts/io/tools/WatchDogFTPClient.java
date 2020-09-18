/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import de.concepts.WatchDogConfiguration;
import de.concepts.io.db.SQLiter;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * implements a ftp client with apache commons
 */
public class WatchDogFTPClient {

    FTPClient ftp = null;

    public FTPClient getApacheFtpClient() {
        return this.ftp;
    }

    public WatchDogFTPClient(String host, String user, String pwd) throws Exception {
        ftp = new FTPClient();
        if (WatchDogConfiguration.watchdogFTPProtocolCommandListenerEnabled.equalsIgnoreCase("true")) {
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        } else {
            // System.out.println("No FTP protocolCommandListener added.");
        }
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(user, pwd);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    public static String getParentDirPath(String fileOrDirPath) {
        Path workDirPath = Paths.get(fileOrDirPath).getParent();
        return workDirPath.toString();
    }

    /**
     * @param subdirectory
     * @param filetypeFilter using * as filter does not filter anything, other filter based on contains()
     * @return
     * @throws IOException
     */
    public List<String> listFiles(String subdirectory, String filetypeFilter) throws IOException {
        List<String> listOfFiles = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(WatchDogConfiguration.watchdogFTPServer, 21);
        ftpClient.login(WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
        boolean success = ftpClient.changeWorkingDirectory(subdirectory);
        // lists files and directories in the current working directory
        FTPFile[] files = ftpClient.listFiles();

        Stream<FTPFile> ftpFileStream = Arrays.stream(files);
        if (filetypeFilter.equals("*")) {
            listOfFiles = ftpFileStream.map(f -> f.getName()).collect(Collectors.toList());
        } else {
            listOfFiles =
                    ftpFileStream.map(f -> f.getName()).filter(name -> name.contains(filetypeFilter)).collect(Collectors.toList());
        }
        ftpClient.logout();
        ftpClient.disconnect();

        return listOfFiles;
    }

    /**
     * delete files older than <days>
     *
     * @param subdirectory
     * @param filetypeFilter
     * @param days
     * @return
     * @throws IOException
     */
    public List<String> deleteOldFiles(String subdirectory, String filetypeFilter, int days) throws IOException {
        List<String> listOfFiles = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(WatchDogConfiguration.watchdogFTPServer, 21);
        ftpClient.login(WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
        boolean success = ftpClient.changeWorkingDirectory(subdirectory);
        // lists files and directories in the current working directory
        FTPFile[] files = ftpClient.listFiles();
        SQLiter.logFtpAccess("Check for old files" );
        for (FTPFile file : files) {
            if (file.isFile()) {
                int age = checkAgeOfFile(ftpClient, file);
                if (age >= days) {
                    System.out.println(file.getName() + " -> " + age  + " days old");
                    boolean deleted = ftpClient.deleteFile(file.getName());
                    if (deleted) {
                        System.out.println("The file was deleted successfully.");
                        SQLiter.logFtpAccess("DEL " + file.getName() + " / " + age + " day");
                    } else {
                        System.out.println("Could not delete the  file, it may not exist.");
                    }

                }
            }
        }

        ftpClient.logout();
        ftpClient.disconnect();

        return listOfFiles;
    }

    private int checkAgeOfFile(FTPClient ftpClient, FTPFile file) throws IOException {
        String time = ftpClient.getModificationTime(file.getName());
        String year = time.substring(0, 4);
        String month = time.substring(4, 6);
        String day = time.substring(6, 8);
        LocalDate fileDate = LocalDate.of(2015, 12, 31);
        //                System.out.println(year + " , " + month + " , " + day);
        LocalDate modificationDate = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(modificationDate, currentDate);
        int age = period.getDays();
        return age;
    }

    String printTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            String timePart = time.split(" ")[1];
            Date modificationTime = dateFormat.parse(timePart);
            return modificationTime.toString();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return "bla";
    }

    public String downloadFile(String remoteFilePath, String localFilePath) {
        String parentDirPath = Paths.get(remoteFilePath).getParent().toString();
        String fileName = Paths.get(remoteFilePath).getFileName().toString();
        parentDirPath = parentDirPath.replace("\\", "/");
        String timeStamp = new SimpleDateFormat("YYYYMMdd-hhmmss_").format(new Date());
        String processedPath = parentDirPath + "/processed/" + timeStamp + fileName;
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            this.ftp.retrieveFile(remoteFilePath, fos);
            this.ftp.rename(remoteFilePath, processedPath);

            return "OK: " + localFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "ERR: " + localFilePath + " -> " + e.getMessage();
        }
    }

    public void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already downloaded from FTP server
            }
        }
    }

    public static int deleteOldFiles(int days) {
        int numberOfDeletedFiles = 0;

        return numberOfDeletedFiles;
    }

    public void sayHello() {
        System.out.println("FTP monitoring active");
    }

    public static void main(String[] args) {
        try {
            WatchDogFTPClient ftpDownloader = new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer,
                    WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
            ftpDownloader.downloadFile("/testdata/users.xml", "./downloads/users.xml");
            System.out.println("FTP directory checked.");
            ftpDownloader.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
