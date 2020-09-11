/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts;

import de.concepts.io.tools.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class WatchDogConfiguration {
    public static String watchdogLogfilePath = ""; 
    public static String watchdogConfigurationPrintDetails = "";
    public static String watchdogDirectoryEnabled = "";
    public static String watchdogDirectoryMonitored = ""; 
    public static String watchdogDirectoryProcessed = ""; 
    public static String watchdogTimestampFormat = ""; 
    public static String watchdogSleepMilliseconds = ""; 
    public static String watchdogFTPEnabled = ""; 
    public static String watchdogFTPProtocolCommandListenerEnabled = ""; 
    public static String watchdogFTPPw = ""; 
    public static String watchdogFTPServer = ""; 
    public static String watchdogFTPUser = ""; 
    public static String watchdogFTPMinutes = ""; 
    public static String watchdogFTPKadisPathRoot = "";
    public static String watchdogFTPKadisPathArticles = "";
    public static String watchdogFTPKadisPathInventory = "";
    public static String watchdogFTPKadisPathPrices = ""; 
    public static String watchdogPiwikSite = ""; 
    public static String watchdogPiwikId = ""; 
    public static String watchdogPiwikScript = ""; 
    public static String watchdogPiwikEnabled = ""; 
    public static String watchdogXMLOrdersSchemaFile = ""; 

    /**
     * configures watchdog using WATCHDOG_LOGGING_PROPERTIES
     */
    public static void configure() {
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
        watchdogConfigurationPrintDetails = properties.getProperty("watchdog.configuration.printDetails");
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
        watchdogFTPKadisPathRoot = properties.getProperty("watchdog.ftp.kadis.path.root");
        watchdogFTPKadisPathArticles = properties.getProperty("watchdog.ftp.kadis.path.articles");
        watchdogFTPKadisPathInventory = properties.getProperty("watchdog.ftp.kadis.path.inventory");
        watchdogFTPKadisPathPrices = properties.getProperty("watchdog.ftp.kadis.path.prices");
        watchdogPiwikSite = properties.getProperty("watchdog.piwik.site");
        watchdogPiwikId = properties.getProperty("watchdog.piwik.id");
        watchdogPiwikScript = properties.getProperty("watchdog.piwik.script");
        watchdogPiwikEnabled = properties.getProperty("watchdog.piwik.enabled");
        watchdogXMLOrdersSchemaFile = properties.getProperty("watchdog.xml.schema.uri.orders");
        // ensure the main directories exist
        File file = new File(watchdogLogfilePath); // create log dir
        file.getParentFile().mkdirs(); // create parent dirs
        try {Files.createDirectory(Paths.get(watchdogDirectoryMonitored)); } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {Files.createDirectory(Paths.get(watchdogDirectoryProcessed)); } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (WatchDogConfiguration.watchdogConfigurationPrintDetails.equalsIgnoreCase("true")) {
            System.out.printf("Java      :  %s%n", Helper.getServerEnvironmentVariables());
        }
        System.out.printf("Monitoring:  %s%n", watchdogDirectoryMonitored);
        System.out.printf("Processed:   %s%n", watchdogDirectoryProcessed);
        System.out.printf("Logfile:     %s%n", watchdogLogfilePath);
    }
}
