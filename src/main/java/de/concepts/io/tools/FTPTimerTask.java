/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import de.concepts.WatchDogConfiguration;
import de.concepts.io.db.SQLiter;
import de.concepts.io.importer.ImporterArticles;
import de.concepts.io.importer.ImporterCSV;
import de.concepts.kadis.in.ImportArticle;
import de.concepts.kadis.out.Price;
import de.concepts.kadis.in.Stock;
import org.apache.http.HttpResponse;
import org.piwik.java.tracking.PiwikRequest;
import org.piwik.java.tracking.PiwikTracker;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FTPTimerTask extends TimerTask {
    public void run() {


        WatchDogFTPClient ftpDownloader = null;
        try {
            ftpDownloader = new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer,
                    WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date());
        if (WatchDogConfiguration.watchdogPiwikEnabled.equalsIgnoreCase("true")) performPiwikRequest();


        List<String> xmlFilesOnServer = null;
        List<String> csvFilesOnServer = null;
        try {
            ftpDownloader = null;
            ftpDownloader = new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer,
                    WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
            // get lists with all files on server
            System.out.println("Checking files on FTP server...");
            xmlFilesOnServer = ftpDownloader.listFiles(WatchDogConfiguration.watchdogFTPKadisPathRoot, "xml");
            csvFilesOnServer = ftpDownloader.listFiles(WatchDogConfiguration.watchdogFTPKadisPathRoot, "csv");
            // get all XML
            List<String> listOfArticles = xmlFilesOnServer.stream().collect(Collectors.toList());
            WatchDogFTPClient finalFtpDownloader = ftpDownloader;
            if (listOfArticles.size()<1) {
                System.out.println("No XML file found");
            } else {

                listOfArticles.forEach(articleFileName -> {
                    finalFtpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathRoot + "/" + articleFileName, "./downloads/" + ts + articleFileName);
                    SQLiter.logFtpAccess(articleFileName);
                    //                    System.out.println("./downloads/" + ts + articleFileName);
                    // process CSV
                    ImportArticle currentArticle = ImporterArticles.getArticleIfValidXml(ts, articleFileName);
                });
            }
            // get all CSV
            List<String> listOfCSV = csvFilesOnServer.stream().collect(Collectors.toList());
            if (listOfCSV.size()<1) {
                System.out.println("No CSV file found");
            } else {
                listOfCSV.forEach(csvFileName -> {
                    finalFtpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathRoot + "/"
                            + csvFileName, "./downloads/" + ts + csvFileName);
                    SQLiter.logFtpAccess(csvFileName);
                    System.out.println("./downloads/" + ts + csvFileName);
                    // process CSV

                    if (csvFileName.contains("bestand")) {
                        List<Stock> stockList = ImporterCSV.getInventoryFromCSV(csvFileName);
                    } else if (csvFileName.contains("preise")) {
                        List<Price> priceList = ImporterCSV.getPricesFromCSV(csvFileName,
                                WatchDogConfiguration.watchdogCSVPricegroup);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ftpDownloader.disconnect();

    }

    private void performPiwikRequest() {
        try {
            // log FTP access with piwik - start
            PiwikRequest request = new PiwikRequest(15, new URL(WatchDogConfiguration.watchdogPiwikSite));
            request.setActionName("watchdog");
            request.setCustomTrackingParameter("copyright", "mgm.technology");
            PiwikTracker tracker = new PiwikTracker(WatchDogConfiguration.watchdogPiwikScript);
            Future<HttpResponse> response = tracker.sendRequestAsync(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
