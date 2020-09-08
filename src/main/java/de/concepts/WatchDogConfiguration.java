package de.concepts;

import de.concepts.io.tools.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class WatchDogConfiguration {
    public static String watchdogLogfilePath = ""; // will be overwritten later
    public static String watchdogDirectoryEnabled = ""; // will be overwritten later
    public static String watchdogDirectoryMonitored = ""; // will be overwritten later
    public static String watchdogDirectoryProcessed = ""; // will be overwritten later
    public static String watchdogTimestampFormat = ""; // will be overwritten later
    public static String watchdogSleepMilliseconds = ""; // will be overwritten later
    public static String watchdogFTPEnabled = ""; // will be overwritten later
    public static String watchdogFTPProtocolCommandListenerEnabled = ""; // will be overwritten later
    public static String watchdogFTPPw = ""; // will be overwritten later
    public static String watchdogFTPServer = ""; // will be overwritten later
    public static String watchdogFTPUser = ""; // will be overwritten later
    public static String watchdogFTPMinutes = ""; // will be overwritten later
    public static String watchdogFTPKadisPathArticles = ""; // will be overwritten later
    public static String watchdogFTPKadisPathInventory = ""; // will be overwritten later
    public static String watchdogFTPKadisPathPrices = ""; // will be overwritten later
    public static String watchdogPiwikSite = ""; // will be overwritten later
    public static String watchdogPiwikId = ""; // will be overwritten later
    public static String watchdogPiwikScript = ""; // will be overwritten later
    public static String watchdogPiwikEnabled = ""; // will be overwritten later

    /**
     * configures watchdog using WATCHDOG_LOGGING_PROPERTIES
     */
    static void configureWatchDog() {
        Properties properties = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream(WatchDog.WATCHDOG_LOGGING_PROPERTIES);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        watchdogLogfilePath = properties.getProperty("watchdog.logfile.path");
        watchdogDirectoryEnabled = properties.getProperty("watchdog.directory.enabled");
        watchdogDirectoryMonitored = properties.getProperty("watchdog.directory.monitored");
        watchdogDirectoryProcessed = properties.getProperty("watchdog.directory.processed");
        watchdogTimestampFormat = properties.getProperty("watchdog.timestamp.format");
        watchdogSleepMilliseconds = properties.getProperty("watchdog.sleep.ms");
        watchdogFTPEnabled = properties.getProperty("watchdog.ftp.enabled");
        watchdogFTPProtocolCommandListenerEnabled = properties.getProperty("watchdog.ftp.protocolCommandListener.enabled");
        watchdogFTPServer = properties.getProperty("watchdog.ftp.server");
        watchdogFTPUser = properties.getProperty("watchdog.ftp.user");
        watchdogFTPPw = properties.getProperty("watchdog.ftp.pw");
        watchdogFTPMinutes = properties.getProperty("watchdog.ftp.minutes");
        watchdogFTPKadisPathArticles = properties.getProperty("watchdog.ftp.kadis.path.articles");
        watchdogFTPKadisPathInventory = properties.getProperty("watchdog.ftp.kadis.path.inventory");
        watchdogFTPKadisPathPrices = properties.getProperty("watchdog.ftp.kadis.path.prices");
        watchdogPiwikSite = properties.getProperty("watchdog.piwik.site");
        watchdogPiwikId = properties.getProperty("watchdog.piwik.id");
        watchdogPiwikScript = properties.getProperty("watchdog.piwik.script");
        watchdogPiwikEnabled = properties.getProperty("watchdog.piwik.enabled");
        // ensure the main directories exist
        File file = new File(watchdogLogfilePath); // create log dir
        file.getParentFile().mkdirs(); // create parent dirs
        try {Files.createDirectory(Paths.get(watchdogDirectoryMonitored)); } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {Files.createDirectory(Paths.get(watchdogDirectoryProcessed)); } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.printf("Java      :  %s%n", Helper.getServerEnvironmentVariables());
        System.out.printf("Monitoring:  %s%n", watchdogDirectoryMonitored);
        System.out.printf("Processed:   %s%n", watchdogDirectoryProcessed);
        System.out.printf("Logfile:     %s%n", watchdogLogfilePath);
    }
}
