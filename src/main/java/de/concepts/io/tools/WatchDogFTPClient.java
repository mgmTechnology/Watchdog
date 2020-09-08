/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.concepts.WatchDogConfiguration;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/** implements a ftp client with apache commons
*/
public class WatchDogFTPClient
 {

    FTPClient ftp = null;

    public WatchDogFTPClient(String host, String user, String pwd) throws Exception {
        ftp = new FTPClient();
        if (WatchDogConfiguration.watchdogFTPProtocolCommandListenerEnabled.equalsIgnoreCase("true")) {
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        } else {
            System.out.println("No protocolCommandListener added.");
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

    public String downloadFile(String remoteFilePath, String localFilePath) {
        String parentDirPath =Paths.get(remoteFilePath).getParent().toString();
        String fileName = Paths.get(remoteFilePath).getFileName().toString();
        parentDirPath = parentDirPath.replace("\\", "/");
        String timeStamp = new SimpleDateFormat("YYYYMMdd-hhmmss_").format(new Date());
        String processedPath = parentDirPath + "/processed/" + timeStamp + fileName;
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            this.ftp.retrieveFile(remoteFilePath, fos);
            this.ftp.rename( remoteFilePath, processedPath);
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

    public void sayHello() {
        System.out.println("FTP monitoring active");
    }

    public static void main(String[] args) {
        try {
            WatchDogFTPClient ftpDownloader =
                    new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer, WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
            ftpDownloader.downloadFile("/testdata/users.xml", "./downloads/users.xml");
            System.out.println("FTP directory checked.");
            ftpDownloader.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
