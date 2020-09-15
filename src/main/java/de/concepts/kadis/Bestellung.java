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

@XmlRootElement(name = "Bestellung")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bestellung {
    @XmlElement(name="BestellNr")
    private String bestellNr;
    @XmlElement(name="Mandant")
    private String mandant;
    @XmlElement(name="Mediaplan")
    private String mediaplan;
    @XmlElement(name="Bestellzeitpunkt")
    private String bestellzeitpunkt;
    @XmlElement(name="Werbekontakt")
    private String werbekontakt;
    @XmlElement(name="Kommentarzeile")
    private String kommentarzeile;
    @XmlElement(name="Preisgruppe")
    private String preisgruppe;
    @XmlElement(name="WKZ")
    private String wkz;
    @XmlElement(name="MwStEinstellung")
    private String mwstEinstellung;

    public Bestellung(String bestellNr, String mediaplan, String bestellzeitpunkt, String mwstEinstellung) {
        this.bestellNr = bestellNr;
        this.mediaplan = mediaplan;
        this.bestellzeitpunkt = bestellzeitpunkt;
        this.mwstEinstellung = mwstEinstellung;
    }

    public Bestellung() {
    }

    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }

    public String getBestellNr() {
        return bestellNr;
    }

    public void setBestellNr(String bestellNr) {
        this.bestellNr = bestellNr;
    }

    public String getMandant() {
        return mandant;
    }

    public void setMandant(String mandant) {
        this.mandant = mandant;
    }

    public String getMediaplan() {
        return mediaplan;
    }

    public void setMediaplan(String mediaplan) {
        this.mediaplan = mediaplan;
    }

    public String getBestellzeitpunkt() {
        return bestellzeitpunkt;
    }

    public void setBestellzeitpunkt(String bestellzeitpunkt) {
        this.bestellzeitpunkt = bestellzeitpunkt;
    }

    public String getWerbekontakt() {
        return werbekontakt;
    }

    public void setWerbekontakt(String werbekontakt) {
        this.werbekontakt = werbekontakt;
    }

    public String getKommentarzeile() {
        return kommentarzeile;
    }

    public void setKommentarzeile(String kommentarzeile) {
        this.kommentarzeile = kommentarzeile;
    }

    public String getPreisgruppe() {
        return preisgruppe;
    }

    public void setPreisgruppe(String preisgruppe) {
        this.preisgruppe = preisgruppe;
    }

    public String getWkz() {
        return wkz;
    }

    public void setWkz(String wkz) {
        this.wkz = wkz;
    }

    public String getMwstEinstellung() {
        return mwstEinstellung;
    }

    public void setMwstEinstellung(String mwstEinstellung) {
        this.mwstEinstellung = mwstEinstellung;
    }
}
