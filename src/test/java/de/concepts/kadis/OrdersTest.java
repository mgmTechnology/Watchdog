/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import de.concepts.io.exporter.ExporterXML;
import de.concepts.io.tools.Helper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    @Test
    void testCreateOrdersObject() {
        String xmlString= "";
        Orders orders = new Orders();
        ArrayList<Auftrag> listAuftraege = new ArrayList<>();

        Auftrag auftrag = new Auftrag();
        Bestellung bestellung = new Bestellung("56610", "Internet", "2010-06-27 12:32:15", "I");
        auftrag.setBestellungObject(bestellung);
        System.out.println(auftrag.getBestellungObject());
        Kunde kunde = new Kunde("12345654321","4711","Susanne", "vom Stein",
                "Sonder KG", "Charlottenstraße", "23", "70182", "Stuttgart",
                "DE", "svs@sonder.management" );
        auftrag.setKundeObject(kunde);
        System.out.println(auftrag.getKundeObject());
        Lieferadresse lieferAdresse = new Lieferadresse("Susanne", "vom Stein","Sonder KG",
                "Charlottenstrasse", "23", "70182", "Stuttgart", "DE", "svs@sonder.management"
        );
        auftrag.setLieferadresseObject(lieferAdresse);
        System.out.println(auftrag.getLieferadresseObject());
        ArrayList<ArticleCorazon> listOrderItems = new ArrayList<>();

        ArticleCorazon art1 = new ArticleCorazon("107291", 1, "XXL", "T-Hemd, rot/weiss",
                new BigDecimal("16.90"), 19, "I");
        ArticleCorazon art2 = new ArticleCorazon("107292", 2, "XXL", "T-Hemd, blau/grün",
                new BigDecimal("14.90"), 19, "I");
        ArticleCorazon art3 = new ArticleCorazon("107293", 3, "XXL", "T-Hemd, schwarz/braun",
                new BigDecimal("17.90"), 19, "I");
        Collections.addAll(listOrderItems,art1,art2,art3);
        auftrag.setOrderListObject(new OrderList(listOrderItems));

        orders.setAuftragObjects(listAuftraege);

        System.out.println(ExporterXML.jaxbObjectToXML(bestellung)); // way 1

        System.out.println(ExporterXML.jaxbObjectToXML(kunde)); // way 1
        System.out.println(kunde.getXML()); // way 2

        System.out.println(lieferAdresse.getXML()); // way 2
        System.out.println(ExporterXML.jaxbObjectToXML(art1)); // way 1

        assertEquals(Helper.testXMLorJSON(lieferAdresse.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(lieferAdresse.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(bestellung.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(art1.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(kunde.getXML()), "XML");
    }

}