/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis.out;

import de.concepts.io.exporter.ExporterXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Lieferadresse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lieferadresse {
    @XmlElement(name="Anrede")
    private String anrede;
    @XmlElement(name="Vorname")
    private String vorname;
    @XmlElement(name="Nachname")
    private String nachname;
    @XmlElement(name="Firma")
    private String firma;
    @XmlElement(name="Strasse")
    private String strasse;
    @XmlElement(name="Hausnummer")
    private String hausnummer;
    @XmlElement(name="PLZ")
    private String plz;
    @XmlElement(name="Stadt")
    private String stadt;
    @XmlElement(name="Land")
    private String land;
    @XmlElement(name = "Telefon")
    Telefon telefonObject;
    @XmlElement(name="Email")
    private String email;

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Telefon getTelefonObject() {
        return telefonObject;
    }

    public void setTelefonObject(Telefon telefonObject) {
        this.telefonObject = telefonObject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Lieferadresse(String vorname, String nachname, String firma, String strasse, String hausnummer, String plz
            , String stadt, String land, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.firma = firma;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.stadt = stadt;
        this.land = land;
        this.email = email;
    }

    public Lieferadresse() {
    }

    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }

}
