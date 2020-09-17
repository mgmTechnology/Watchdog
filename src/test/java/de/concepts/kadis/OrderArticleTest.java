/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import de.concepts.kadis.out.OrderArticle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class OrderArticleTest {

    @Test

    void testGetXML() {
        OrderArticle orderArticle = new OrderArticle("123456",2.0,"XXL", "Junit Testartikel",
                new BigDecimal("23.3"), 19.0, "I");


        System.out.println(orderArticle.getXML());
    }
}