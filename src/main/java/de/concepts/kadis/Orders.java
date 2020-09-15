/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;


import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@XmlRootElement(name="orders")
@XmlAccessorType(XmlAccessType.FIELD)

public class Orders {


    private ArrayList<Auftrag> auftragObjects;

    private String xmlns;

    public String getXmlns() {
        return xmlns;
    }
    public ArrayList<Auftrag> getAuftragObjects() {
        return auftragObjects;
    }
    public void setAuftragObjects(ArrayList<Auftrag> auftragObjects) {
        this.auftragObjects = auftragObjects;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
}


class Zahlung {
    public String getZahlungsart() {
        return zahlungsart;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    private String zahlungsart;

}

class Telefon {
    private String vorwahl;
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
}

