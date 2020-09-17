/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import de.concepts.io.tools.Helper;
import de.concepts.io.tools.validator.XMLValidator;
import de.concepts.kadis.out.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    private static final String SCHEMA_ARTICLE_URI = "http://mgm.technology:80/kadis/kadis_order.xsd";


    @Test
    void testCreateOrdersObject() {
        Orders auftraegeFuerKaDIS = new Orders();
        auftraegeFuerKaDIS.setXmlns("http://www.kadis.de/auftrag/V1.1");
        ArrayList<Auftrag> listAuftraege = new ArrayList<>();

        Auftrag auftrag = new Auftrag();
        Bestellung bestellung = new Bestellung("56610", "Internet", "2010-06-27 12:32:15", "I");
        bestellung.setMandant("placeholder");
        bestellung.setWkz("placeholder");
        bestellung.setPreisgruppe("placeholder");
        auftrag.setBestellungObject(bestellung);
        Kunde kunde = new Kunde("12345654321","4711","Susanne", "vom Stein",
                "Sonder KG", "Charlottenstraße", "23", "70182", "Stuttgart",
                "DE", "svs@sonder.management" );
        kunde.setAnrede("Frau");
        Telefon telefon = new Telefon("0202", "470282");
        kunde.setTelefonObject(telefon);
        auftrag.setKundeObject(kunde);
        Lieferadresse lieferAdresse = new Lieferadresse("Susanne", "vom Stein","Sonder KG",
                "Charlottenstrasse", "23", "70182", "Stuttgart", "DE", "svs@sonder.management"
        );
        lieferAdresse.setAnrede("Frau");
        lieferAdresse.setTelefonObject(telefon);
        auftrag.setLieferadresseObject(lieferAdresse);

        auftrag.setZahlungObject(new Zahlung("KK"));
        ArrayList<OrderArticle> listOrderItems = new ArrayList<>();

        OrderArticle art1 = new OrderArticle("107291", 1.0, "XXL", "T-Hemd, rot/weiss",
                new BigDecimal("16.90"), 19.0, "I");
        OrderArticle art2 = new OrderArticle("107292", 2.0, "XXL", "T-Hemd, blau/grün",
                new BigDecimal("14.90"), 19.0, "I");
        OrderArticle art3 = new OrderArticle("107293", 3.0, "XXL", "T-Hemd, schwarz/braun",
                new BigDecimal("17.90"), 19.0, "I");
        Collections.addAll(listOrderItems,art1,art2,art3);
        auftrag.setOrderListObject(new OrderList(listOrderItems));
        listAuftraege.add(auftrag);
        auftraegeFuerKaDIS.setAuftragObjects(listAuftraege);

//        System.out.println(ExporterXML.jaxbObjectToXML(bestellung)); // way 1
//        System.out.println(ExporterXML.jaxbObjectToXML(kunde)); // way 1
//        System.out.println(kunde.getXML()); // way 2
//        System.out.println(lieferAdresse.getXML()); // way 2
//        System.out.println(ExporterXML.jaxbObjectToXML(art1)); // way 1
        System.out.println(auftraegeFuerKaDIS.getXML());

        assertEquals(Helper.testXMLorJSON(lieferAdresse.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(lieferAdresse.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(bestellung.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(art1.getXML()), "XML");
        assertEquals(Helper.testXMLorJSON(kunde.getXML()), "XML");

        boolean validXML = false;
        try {
            validXML = XMLValidator.validateString(auftraegeFuerKaDIS.getXML(), SCHEMA_ARTICLE_URI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(validXML, true, "Article validation");

    }

}