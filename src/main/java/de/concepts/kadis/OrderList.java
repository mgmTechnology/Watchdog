/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import de.concepts.io.exporter.ExporterXML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name = "OrderList")
public class OrderList {
    @XmlElement(name="Artikel")
    ArrayList<OrderArticle> listArtikel = new ArrayList<>();

    public OrderList(ArrayList<OrderArticle> listArtikel) {
        this.listArtikel = listArtikel;
    }


    public OrderList() {
    }

    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }

}
