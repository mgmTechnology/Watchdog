/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import de.concepts.WatchDogConfiguration;
import org.apache.http.HttpResponse;
import org.piwik.java.tracking.PiwikRequest;
import org.piwik.java.tracking.PiwikTracker;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Future;

public class FTPTimerTask extends TimerTask {
    public void run() {


        WatchDogFTPClient ftpDownloader = null;
        try {
            ftpDownloader = new WatchDogFTPClient(WatchDogConfiguration.watchdogFTPServer, WatchDogConfiguration.watchdogFTPUser, WatchDogConfiguration.watchdogFTPPw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date());
        if(WatchDogConfiguration.watchdogPiwikEnabled.equalsIgnoreCase("true") ) performPiwikRequest();

        String resultMessage = ftpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathArticles,
                "./downloads/" + ts + "articles.xml");
        System.out.println(resultMessage);
        ftpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathInventory, "./downloads/" + ts + "article_inventory.csv");
        System.out.println(resultMessage);
        resultMessage = ftpDownloader.downloadFile(WatchDogConfiguration.watchdogFTPKadisPathPrices, "./downloads/" + ts + "article_prices.csv");
        System.out.println(resultMessage);

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
