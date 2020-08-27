/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.importer;

import java.util.logging.Logger;

public class Importer {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    public Importer() {
        log.info(String.format("%s instantiated.", this.getClass().getName()));
    }

    @Override
    public String toString() {
        return "Importer{}";
    }


}
