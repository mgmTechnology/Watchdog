package de.concepts.io.tools;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class FTPTimerTask extends TimerTask {
    public void run() {
        WatchDogFTPClient ftpDownloader = null;
        try {
            ftpDownloader = new WatchDogFTPClient("mgm.technology", "developer", "ftp4Developers!Now");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date());

        ftpDownloader.downloadFile("/testdata/users.xml", "./downloads/" + ts + "users.xml");
        System.out.println("FTP File downloaded successfully");
        ftpDownloader.disconnect();

    }
}
