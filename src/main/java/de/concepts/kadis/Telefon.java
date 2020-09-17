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

@XmlRootElement(name = "Telefon")
@XmlAccessorType(XmlAccessType.FIELD)
class Telefon {
    @XmlElement(name="Vorwahl")
    private String vorwahl;
    @XmlElement(name="Rufnummer")
    private String rufnummer;

    public String getVorwahl() {
        return vorwahl;
    }

    public void setVorwahl(String vorwahl) {
        this.vorwahl = vorwahl;
    }

    public String getRufnummer() {
        return rufnummer;
    }

    public void setRufnummer(String rufnummer) {
        this.rufnummer = rufnummer;
    }
    public Telefon() {
    }

    public Telefon(String vorwahl, String rufnummer) {
        this.vorwahl = vorwahl;
        this.rufnummer = rufnummer;
    }

    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }
}