/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class XMLValidatorTest {

    private static final String RESOURCES_TESTDATA_ARTICLES_XML = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/100116.xml";
    private static final String RESOURCES_TESTDATA_ORDER_XML = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/orders.xml";
    private static final String RESOURCES_XSD_ARTICLES_XSD = "D:/projects/Corazon/WatchDog/src/main/resources/xsd/inferred_article.xsd";
    private static final String SCHEMA_ORDER_URI = "http://mgm.technology:80/kadis/orders.xsd";
    private static final String SCHEMA_ARTICLE_URI = "http://mgm.technology:80/kadis/inferred_article.xsd";

    @Test
    void testValidateOrders() {
        boolean validXML = false;
        try {
            validXML = XMLValidator.validate(RESOURCES_TESTDATA_ORDER_XML, SCHEMA_ORDER_URI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(validXML, true);
    }
    @Test
    void testValidateArticle() {
        boolean validXML = false;
        try {
            validXML = XMLValidator.validate(RESOURCES_TESTDATA_ARTICLES_XML, SCHEMA_ARTICLE_URI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(validXML, true, "Article validation");
    }
}