/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import de.concepts.io.exporter.ExporterXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "Artikel")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * this class is a article element within an orderList to be send to KaDIS
 */
public class OrderArticle {
    @XmlElement(name="ArtikelNr")
    String artikelNr;
    @XmlElement(name="Menge")
    Double menge;
    @XmlElement(name="Groesse")
    String groesse;
    @XmlElement(name="Name")
    String name;
    @XmlElement(name="Preis")
    BigDecimal preis;
    @XmlElement(name="Steuer")
    Double steuer;
    @XmlElement(name="SteuerEinstellung")
    String steuerEinstellung;

    public String getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(String artikelNr) {
        this.artikelNr = artikelNr;
    }

    public Double getMenge() {
        return menge;
    }

    public void setMenge(Double menge) {
        this.menge = menge;
    }

    public String getGroesse() {
        return groesse;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public Double getSteuer() {
        return steuer;
    }

    public void setSteuer(Double steuer) {
        this.steuer = steuer;
    }

    public String getSteuerEinstellung() {
        return steuerEinstellung;
    }

    public void setSteuerEinstellung(String steuerEinstellung) {
        this.steuerEinstellung = steuerEinstellung;
    }

    public OrderArticle(String artikelNr, Double menge, String groesse, String name, BigDecimal preis,
                        Double steuer, String steuerEinstellung) {
        this.artikelNr = artikelNr;
        this.menge = menge;
        this.groesse = groesse;
        this.name = name;
        this.preis = preis;
        this.steuer = steuer;
        this.steuerEinstellung = steuerEinstellung;
    }

    public OrderArticle() {
    }

    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }
}
