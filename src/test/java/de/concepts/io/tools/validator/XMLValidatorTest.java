/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools.validator;

import org.junit.jupiter.api.Test;

import java.io.*;

class XMLValidatorTest {

    private static final String RESOURCES_TESTDATA_ARTICLES_XML = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/orders.xml";
    private static final String RESOURCES_XSD_ARTICLES_XSD = "D:/projects/Corazon/WatchDog/src/main/resources/xsd/orders.xsd";
    private static final String SCHEMA_ORDER_URI = "http://mgm.technology:80/kadis/orders.xsd";

    @Test
    void testValidateOrders() {
        try {
            boolean validXML = XMLValidator.validateOrders(RESOURCES_TESTDATA_ARTICLES_XML, SCHEMA_ORDER_URI);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}