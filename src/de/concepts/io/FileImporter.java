package de.concepts.io;

import java.util.Queue;
import java.util.logging.Logger;

public class FileImporter implements Runnable {
    private Thread t;
    private String threadName;
    private static final Logger log = Logger.getLogger(FileImporter.class.getName());
    FileImporter(String name) {
        threadName = name;
        //System.out.println("Creating " + threadName);
    }

    public void run() {
        // log.info("Running " + threadName);
        try {
                // System.out.println("Thread: " + threadName );
                // Let the thread sleep for a while.
                Thread.sleep(50);

        } catch (InterruptedException e) {
            log.warning("Thread " + threadName + " interrupted.");
        }
        // log.info("Thread " + threadName + " exiting.");
    }

    public void start(Queue<String> queue) {
        log.info("[Thread " + threadName + "] processes file " + queue.poll());
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}