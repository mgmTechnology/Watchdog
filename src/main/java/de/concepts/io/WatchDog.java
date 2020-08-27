/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io;

import de.concepts.io.importer.ImporterCSV;
import de.concepts.io.importer.ImporterJSON;
import de.concepts.io.importer.ImporterXML;
import de.concepts.io.tools.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.logging.*;


public class WatchDog {
    static String watchdogLogfilePath = ""; // will be overwritten later
    static String watchdogDirectoryMonitored = ""; // will be overwritten later
    static String watchdogDirectoryProcessed = ""; // will be overwritten later
    static String watchdogTimestampFormat = ""; // will be overwritten later
    static String watchdogSleepMilliseconds = ""; // will be overwritten later
    static final String WATCHDOG_LOGGING_PROPERTIES = "watchdog.properties";
    static Logger logger = Logger.getAnonymousLogger();
    static Queue<String> queueWithFilenames = new LinkedList<>();
    static Handler fileHandler = null;

    public static void main(String... args) {
        String currentDirectory;
        File file = new File(".");
        currentDirectory = file.getAbsolutePath();
        Helper.printAsciiWatchDog();
        configureWatchDog();
        configureLogging(currentDirectory);
        logger.info("Selftest Unirest : " + Helper.checkUnirest());
        logger.info("Selftest XML     : " + Helper.checkXmlHandling());
        logger.info("Selftest JSON    : " + Helper.checkJsonHandling());
        Path path = Paths.get(watchdogDirectoryMonitored);
        logger.info("Watchdog started monitoring");

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            startListening(watchService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Watchdog stopped monitoring.");
    }

    /**
     * configures watchdog using WATCHDOG_LOGGING_PROPERTIES
     */
    private static void configureWatchDog() {
        Properties properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(WATCHDOG_LOGGING_PROPERTIES);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        watchdogLogfilePath = properties.getProperty("watchdog.logfile.path");
        watchdogDirectoryMonitored = properties.getProperty("watchdog.directory.monitored");
        watchdogDirectoryProcessed = properties.getProperty("watchdog.directory.processed");
        watchdogTimestampFormat = properties.getProperty("watchdog.timestamp.format");
        watchdogSleepMilliseconds = properties.getProperty("watchdog.sleep.ms");
        // ensure the main directories exist
        File file = new File(watchdogLogfilePath); // create log dir
        file.getParentFile().mkdirs(); // create parent dirs
        try {Path newDir = Files.createDirectory(Paths.get(watchdogDirectoryMonitored)); } catch (Exception e) { }
        try {Path newDir = Files.createDirectory(Paths.get(watchdogDirectoryProcessed)); } catch (Exception e) { }
        System.out.printf("Environment:  %s%n", Helper.getServerEnvironmentVariables());
        System.out.printf("Monitoring:  %s%n", watchdogDirectoryMonitored);
        System.out.printf("Processed:   %s%n", watchdogDirectoryProcessed);
        System.out.printf("Logfile:     %s%n", watchdogLogfilePath);
    }


    /**
     * configure logging for the application
     * @param currentDirectory
     */
    private static void configureLogging(String currentDirectory) {
        System.out.println("Looking for watchdog.properties within current working directory : " + currentDirectory);
        try {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(new FileInputStream(WATCHDOG_LOGGING_PROPERTIES));
            try {
                fileHandler = new FileHandler(watchdogLogfilePath, true); //file
                SimpleFormatter simple = new SimpleFormatter();
                fileHandler.setFormatter(simple);
                logger.addHandler(fileHandler);//adding Handler for file
            } catch (IOException e) {
                System.err.println("Exception during configuration of logging.");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        logger.info(String.format("Monitoring:  %s", watchdogDirectoryMonitored));
        logger.info(String.format("Processed:   %s", watchdogDirectoryProcessed));
        logger.info(String.format("Logfile:     %s", watchdogLogfilePath));
    }

    /**
     * move a processed file into a separate processed folder, adding a timestamp prefix to it
     *
     * @param queue
     * @return true or false signale move result
     */
    public static boolean moveFile(Queue<String> queue) {
        boolean fileMoved = true;
        String importFilePath = queue.poll();
        Path fileToMovePath;
        Path targetPath;
        assert importFilePath != null;
        fileToMovePath = Paths.get(importFilePath);
        String timeStamp = new SimpleDateFormat(watchdogTimestampFormat).format(new Date());
        targetPath = Paths.get(watchdogDirectoryProcessed + timeStamp + fileToMovePath.getFileName());
        try {
            File f = new File(String.valueOf(fileToMovePath));
            while (!f.canWrite()) { // file is locked, let's wait until is is completely written
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("File can be moved: " + f.canWrite()); // -> true
            Files.move(fileToMovePath, targetPath);
            logger.info("Moved processed file to " + targetPath.getFileName());
        } catch (FileAlreadyExistsException e) {
            //destination file already exists. something really bad happens, as we included timestamp. try
            // nevertheless again
            logger.info("File already processed: " + targetPath.getFileName());
            try {
                Files.delete(targetPath);
                Files.copy(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                logger.warning("File nevertheless moved: " + targetPath.getFileName());
            } catch (IOException ioException) {
                logger.severe("IO Exception trying to write file: " + targetPath.getFileName());
                ioException.printStackTrace();
                fileMoved = false;
            }

        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
            fileMoved = false;
        }
        return fileMoved;
    }

    /**
     * @param watchService watchService for file / directory events
     * @throws InterruptedException exception when interrupted
     */
    private static void startListening(WatchService watchService) throws InterruptedException {
        boolean poll = true;
        while (poll) {
            WatchKey queuedKey = watchService.take();
            /*
             * Prevent receiving two separate ENTRY_MODIFY events: file modified and timestamp updated.
             * Instead, receive one ENTRY_MODIFY event with two counts. Do this by sleeping
             * In two words, the events are generated by your operation system. You've got two events
             * which means your OS performs copy in non-atomic way. If you try to read the file somewhere
             * between ENTRY_CREATE and ENTRY_MODIFY, it'll be inconsistent. But again, it depends on your OS.
             * For instance on Windows 7 I'm getting ENTRY_CREATE one time and ENTRY_MODIFY two times
             * for large files. So you actually cannot be sure whether or not the file is successfully
             * copied, you have to come up with application/OS dependent metrics.
             */
            Thread.sleep(Integer.parseInt(watchdogSleepMilliseconds));
            for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {
                switch (watchEvent.kind().name()) {
                    case "ENTRY_CREATE":
                        // log.fine("New file: " watchEvent.context());
                        break;
                    case "ENTRY_MODIFY":
                        String modifiedFilePath = watchdogDirectoryMonitored + watchEvent.context().toString();
                        queueWithFilenames.offer(modifiedFilePath); // add to queue to handle this file later
                        String timeStamp = new SimpleDateFormat("MM-dd hh:mm:ss ").format(new Date());
                        logger.info(timeStamp + modifiedFilePath);

                        String fileTypeExtension = Helper.getPathExtension(modifiedFilePath).toLowerCase();
                        switch (fileTypeExtension) {
                            case "xml":
                                // new ImporterXML();
                                ImporterXML xmlImporter = new ImporterXML();
                                break;
                            case "json":
                                ImporterJSON jsonImporter = new ImporterJSON();
                                break;
                            case "csv":
                                ImporterCSV csvImporter = new ImporterCSV();
                                break;
                            default:
                                logger.info(String.format("file type %s not supported.", fileTypeExtension));
                        }
                        moveFile(queueWithFilenames);
                        break;
                    case "ENTRY_DELETE":
                        // log.info("File deleted: " + watchEvent.context());
                        break;
                    default:
                        logger.info("Unknown event for file. ");
                        break;
                }
                if (!queuedKey.reset()) {
                    break;
                }
            }
            poll = queuedKey.reset();
        }
    }
}