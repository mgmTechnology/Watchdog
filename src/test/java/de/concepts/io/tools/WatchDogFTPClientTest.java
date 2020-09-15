package de.concepts.io.tools;

import de.concepts.WatchDogConfiguration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WatchDogFTPClientTest {

    @Test
    void testListFiles() {
        boolean validXML = false;
        List<String> xmlFilesOnServer=null;
        List<String> csvFilesOnServer=null;
        try {
            WatchDogConfiguration.configure();
            WatchDogFTPClient ftpDownloader = null;
            ftpDownloader = new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer,
                    WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
            xmlFilesOnServer= ftpDownloader.listFiles(WatchDogConfiguration.watchdogFTPKadisPathRoot, "xml");
            csvFilesOnServer= ftpDownloader.listFiles(WatchDogConfiguration.watchdogFTPKadisPathRoot, "csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("XML:");
        xmlFilesOnServer.stream().forEach(System.out::println);
        System.out.println("CSV:");
        csvFilesOnServer.stream().forEach(System.out::println);
        assertTrue(xmlFilesOnServer.size()  > 0, "no XML files found" );
        assertTrue(csvFilesOnServer.size()  > 0, "no CSV files found" );
    }
}