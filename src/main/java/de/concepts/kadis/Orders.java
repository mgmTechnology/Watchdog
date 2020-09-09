/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;


import java.util.ArrayList;

public class Orders {
    Auftrag auftragObject;
    private String xmlns;

    public Auftrag getAuftragObject() {
        return auftragObject;
    }

    public void setAuftragObject(Auftrag auftragObject) {
        this.auftragObject = auftragObject;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
}

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


}

class OrderList {
    ArrayList<Object> artikel = new ArrayList<>();
    // Getter Methods
    // Setter Methods
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

class Lieferadresse {
    private String anrede;
    private String vorname;
    private String nachname;
    private String firma;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String stadt;
    private String land;
    Telefon telefonObject;
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
}

class Kunde {
    private String shopwareKundennummer;
    private String kundenNr;
    private String anrede;

    public String getShopwareKundennummer() {
        return shopwareKundennummer;
    }

    public void setShopwareKundennummer(String shopwareKundennummer) {
        this.shopwareKundennummer = shopwareKundennummer;
    }

    public String getKundenNr() {
        return kundenNr;
    }

    public void setKundenNr(String kundenNr) {
        this.kundenNr = kundenNr;
    }

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

    private String vorname;
    private String nachname;
    private String firma;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String stadt;
    private String land;
    Telefon telefonObject;
    private String email;


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

class Bestellung {
    private String bestellNr;
    private String mandant;
    private String mediaplan;
    private String bestellzeitpunkt;
    private String werbekontakt;
    private String kommentarzeile;
    private String preisgruppe;
    private String wkz;
    private String mwstEinstellung;

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