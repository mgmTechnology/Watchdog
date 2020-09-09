/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import java.util.ArrayList;

public class Article {
    private String artikelsteuerung;
    private String verkaufsplattform;
    private String supplierName;
    private String active;
    private String tax;
    MainDetail mainDetailObject;
    private String name;
    private String description;
    private String descriptionLong;
    private String keywords;
    private String lastStock;
    private String notification;
    ArrayList<Object> categories = new ArrayList<>();
    Translations translationsObject;

    // Getter Methods
    public String getArtikelsteuerung() { return artikelsteuerung; }

    public void setArtikelsteuerung(String artikelsteuerung) { this.artikelsteuerung = artikelsteuerung; }

    public String getActive() { return active; }

    public String getTax() { return tax; }

    public MainDetail getMainDetail() { return mainDetailObject; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getDescriptionLong() { return descriptionLong; }

    public String getKeywords() { return keywords; }

    public String getLastStock() { return lastStock; }

    public String getNotification() { return notification; }

    public Translations getTranslations() { return translationsObject; }

    public String getVerkaufsplattform() { return verkaufsplattform; }

    public String getSupplierName() { return supplierName; }


    // Setter Methods

    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public void setVerkaufsplattform(String Verkaufsplattform) { this.verkaufsplattform = Verkaufsplattform; }

    public void setActive(String active) { this.active = active; }

    public void setTax(String tax) { this.tax = tax; }

    public void setMainDetail(MainDetail mainDetailObject) { this.mainDetailObject = mainDetailObject; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setDescriptionLong(String descriptionLong) { this.descriptionLong = descriptionLong; }

    public void setKeywords(String keywords) { this.keywords = keywords; }

    public void setLastStock(String lastStock) { this.lastStock = lastStock; }

    public void setNotification(String notification) { this.notification = notification; }

    public void setTranslations(Translations translationsObject) { this.translationsObject = translationsObject; }
}

class Translations {
    Englisch englischObject;

    // Getter Methods
    public Englisch getEnglisch() { return englischObject; }

    // Setter Methods
    public void setEnglisch(Englisch EnglischObject) { this.englischObject = EnglischObject; }
}

class Englisch {
    private String name;
    private String description;
    private String descriptionLong;
    private String keywords;

    // Getter Methods
    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getDescriptionLong() { return descriptionLong; }

    public String getKeywords() { return keywords; }

    // Setter Methods

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setDescriptionLong(String descriptionLong) { this.descriptionLong = descriptionLong; }

    public void setKeywords(String keywords) { this.keywords = keywords; }
}

class MainDetail {
    private String number;
    ArrayList<Object> prices = new ArrayList<>();
    private String supplierNumber;
    private String kind;
    private String active;
    private String inStock;
    private String stockMin;
    private String weight;
    private String width;
    private String len;
    private String height;
    private String position;
    private String minPurchase;
    private String maxPurchase;
    private String purchaseUnit;
    private String referenceUnit;
    Unit unitObject;
    private String packUnit;
    private String shippingFree;
    private String shippingTime;
    Attribute attributeObject;
    ImageAttribute imageAttributeObject;

    // Getter Methods
    public String getNumber() { return number; }

    public String getSupplierNumber() { return supplierNumber; }

    public String getKind() { return kind; }

    public String getActive() { return active; }

    public String getInStock() { return inStock; }

    public String getStockMin() { return stockMin; }

    public String getWeight() { return weight; }

    public String getWidth() { return width; }

    public String getLen() { return len; }

    public String getHeight() { return height; }

    public String getPosition() { return position; }

    public String getMinPurchase() { return minPurchase; }

    public String getMaxPurchase() { return maxPurchase; }

    public String getPurchaseUnit() { return purchaseUnit; }

    public String getReferenceUnit() { return referenceUnit; }

    public Unit getUnit() { return unitObject; }

    public String getPackUnit() { return packUnit; }

    public String getShippingFree() { return shippingFree; }

    public String getShippingTime() { return shippingTime; }

    public Attribute getAttribute() { return attributeObject; }

    public ImageAttribute getImageAttribute() { return imageAttributeObject; }

    // Setter Methods
    public void setNumber(String number) { this.number = number; }

    public void setSupplierNumber(String supplierNumber) { this.supplierNumber = supplierNumber; }

    public void setKind(String kind) { this.kind = kind; }

    public void setActive(String active) { this.active = active; }

    public void setInStock(String inStock) { this.inStock = inStock; }

    public void setStockMin(String stockMin) { this.stockMin = stockMin; }

    public void setWeight(String weight) { this.weight = weight; }

    public void setWidth(String width) { this.width = width; }

    public void setLen(String len) { this.len = len; }

    public void setHeight(String height) { this.height = height; }

    public void setPosition(String position) { this.position = position; }

    public void setMinPurchase(String minPurchase) { this.minPurchase = minPurchase; }

    public void setMaxPurchase(String maxPurchase) { this.maxPurchase = maxPurchase; }

    public void setPurchaseUnit(String purchaseUnit) { this.purchaseUnit = purchaseUnit; }

    public void setReferenceUnit(String referenceUnit) { this.referenceUnit = referenceUnit; }

    public void setUnit(Unit unitObject) { this.unitObject = unitObject; }

    public void setPackUnit(String packUnit) { this.packUnit = packUnit; }

    public void setShippingFree(String shippingFree) { this.shippingFree = shippingFree; }

    public void setShippingTime(String shippingTime) { this.shippingTime = shippingTime; }

    public void setAttribute(Attribute attributeObject) { this.attributeObject = attributeObject; }

    public void setImageAttribute(ImageAttribute ImageAttributeObject) {
        this.imageAttributeObject = ImageAttributeObject;
    }
}

class ImageAttribute {
    ArrayList<Object> image = new ArrayList<>();
    // Getter Methods
    // Setter Methods
}

class Attribute {
    private String lighthouseAltOrdernumber;
    private String lighthouseCoolDeliveryArticle;
    private String lighthouseArticleDeposit;

    // Getter Methods
    public String getLighthouseAltOrdernumber() { return lighthouseAltOrdernumber; }

    public String getLighthouseCoolDeliveryArticle() { return lighthouseCoolDeliveryArticle; }

    public String getLighthouseArticleDeposit() { return lighthouseArticleDeposit; }

    // Setter Methods
    public void setLighthouseAltOrdernumber(String lighthouseAltOrdernumber) { this.lighthouseAltOrdernumber =
            lighthouseAltOrdernumber; }

    public void setLighthouseCoolDeliveryArticle(String lighthouseCoolDeliveryArticle) { this.lighthouseCoolDeliveryArticle = lighthouseCoolDeliveryArticle; }

    public void setLighthouseArticleDeposit(String lighthouseArticleDeposit) {
        this.lighthouseArticleDeposit = lighthouseArticleDeposit;
    }
}

class Unit {
    private String unit;
    private String name;

    // Getter Methods
    public String getUnit() { return unit; }

    public String getName() { return name; }

    // Setter Methods
    public void setUnit(String unit) { this.unit = unit; }

    public void setName(String name) { this.name = name; }
}