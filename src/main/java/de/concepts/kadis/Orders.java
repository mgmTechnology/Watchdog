/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;


import de.concepts.io.exporter.ExporterXML;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@XmlRootElement(name="Auftraege")
@XmlAccessorType(XmlAccessType.FIELD)

public class Orders {
    @XmlElement(name="Auftrag")
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
    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }
}


