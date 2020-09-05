package de.concepts.io.interfaces;

import java.util.logging.Logger;

interface ILogger {
    static Logger log = Logger.getAnonymousLogger();


    default void action() {
        log.info("TEST");
    }
}