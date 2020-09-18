/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import de.concepts.WatchDogConfiguration;
import de.concepts.io.db.SQLiter;
import de.concepts.io.importer.ImporterCSV;
import de.concepts.kadis.in.Stock;
import de.concepts.kadis.out.Price;
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

    @Test
    void testDeleteOldFiles() {
        List<String> filesOnServer=null;
        try {
            WatchDogConfiguration.configure();
            WatchDogFTPClient ftpClient = null;
            ftpClient = new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer,
                    WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);

            filesOnServer= ftpClient.deleteOldFiles("/data/processed", "*", 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}