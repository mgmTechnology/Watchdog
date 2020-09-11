/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import de.concepts.WatchDogConfiguration;
import de.concepts.io.db.SQLiter;
import de.concepts.io.importer.ImporterXML;
import de.concepts.io.tools.validator.XMLValidator;
import de.concepts.kadis.Article;
import org.apache.http.HttpResponse;
import org.piwik.java.tracking.PiwikRequest;
import org.piwik.java.tracking.PiwikTracker;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FTPTimerTask extends TimerTask {
    private static final String SCHEMA_ORDER_URI = "http://mgm.technology:80/kadis/orders.xsd";
    private static final String SCHEMA_ARTICLE_URI = "http://mgm.technology:80/kadis/inferred_article.xsd";
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

        //        String resultMessage = ftpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathRoot + "/"
        //                        + WatchDogConfiguration.watchdogFTPKadisPathArticles,
        //                "./downloads/" + ts + WatchDogConfiguration.watchdogFTPKadisPathArticles);
        //        System.out.println(resultMessage);
        //        SQLiter.logFtpAccess("articles.xml");
        //
        //        ftpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathRoot + "/"
        //                + WatchDogConfiguration.watchdogFTPKadisPathInventory, "./downloads/" + ts +
        //                WatchDogConfiguration.watchdogFTPKadisPathInventory);
        //        SQLiter.logFtpAccess("article_inventory.csv");
        //        System.out.println(resultMessage);
        //
        //        resultMessage = ftpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathRoot + "/"
        //                + WatchDogConfiguration.watchdogFTPKadisPathPrices, "./downloads/" + ts +
        //                WatchDogConfiguration.watchdogFTPKadisPathPrices);
        //        SQLiter.logFtpAccess("article_prices.csv");
        //        System.out.println(resultMessage);

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
                    finalFtpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathRoot + "/"
                            + articleFileName, "./downloads/" + ts + articleFileName);
                    SQLiter.logFtpAccess(articleFileName);
//                    System.out.println("./downloads/" + ts + articleFileName);
                    boolean validXML = false;
                    try {
                        validXML = XMLValidator.validate("./downloads/" + ts + articleFileName, SCHEMA_ARTICLE_URI);

                        if (validXML== true) {
                            System.out.println( "Valid article: " + "./downloads/" + ts + articleFileName);
                            Article currentArticle = ImporterXML.createArticleFromXML("./downloads/" + ts + articleFileName);
                        } else {
                            System.out.println( "Invalid article: " + "./downloads/" + ts + articleFileName);
                        }
                    } catch (IOException | XMLStreamException | ParserConfigurationException | XPathExpressionException | SAXException e) {
                        e.printStackTrace();
                    }
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
