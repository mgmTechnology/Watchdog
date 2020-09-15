/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Auftrag")
@XmlAccessorType(XmlAccessType.FIELD)
class Auftrag {
    Bestellung bestellungObject;
    Kunde kundeObject;
    Lieferadresse lieferadresseObject;
    Zahlung zahlungObject;
    OrderList orderListObject;

    public Bestellung getBestellungObject() {
        return bestellungObject;
    }

    public void setBestellungObject(Bestellung bestellungObject) {
        this.bestellungObject = bestellungObject;
    }

    public Kunde getKundeObject() {
        return kundeObject;
    }

    public void setKundeObject(Kunde kundeObject) {
        this.kundeObject = kundeObject;
    }

    public Lieferadresse getLieferadresseObject() {
        return lieferadresseObject;
    }

    public void setLieferadresseObject(Lieferadresse lieferadresseObject) {
        this.lieferadresseObject = lieferadresseObject;
    }

    public Zahlung getZahlungObject() {
        return zahlungObject;
    }

    public void setZahlungObject(Zahlung zahlungObject) {
        this.zahlungObject = zahlungObject;
    }

    public OrderList getOrderListObject() {
        return orderListObject;
    }

    public void setOrderListObject(OrderList orderListObject) {
        this.orderListObject = orderListObject;
    }

    @Override
    public String toString() {
        return "Auftrag{" + "bestellungObject=" + bestellungObject.toString() + ", kundeObject=" + kundeObject.toString() + ", " + "lieferadresseObject=" + lieferadresseObject.toString() + ", zahlungObject=" + zahlungObject.toString() + ", " + "orderListObject=" + orderListObject.toString() + '}';
    }

    public Auftrag(Bestellung bestellungObject, Kunde kundeObject, Lieferadresse lieferadresseObject,
                   Zahlung zahlungObject, OrderList orderListObject) {
        this.bestellungObject = bestellungObject;
        this.kundeObject = kundeObject;
        this.lieferadresseObject = lieferadresseObject;
        this.zahlungObject = zahlungObject;
        this.orderListObject = orderListObject;
    }

    public Auftrag() {
    }
}
