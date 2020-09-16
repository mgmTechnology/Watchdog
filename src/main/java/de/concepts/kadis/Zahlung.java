/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Zahlung")
@XmlAccessorType(XmlAccessType.FIELD)
class Zahlung {
    public String getZahlungsart() {
        return zahlungsart;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }
    @XmlElement(name="Zahlungsart")
    private String zahlungsart;

    public Zahlung(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public Zahlung() {
    }
}
