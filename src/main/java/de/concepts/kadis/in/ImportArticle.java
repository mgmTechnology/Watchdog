/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis.in;

import de.concepts.io.converter.ObjectConverter;
import de.concepts.kadis.out.Price;
import de.concepts.kadis.out.Translation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * this article is an article to be imported into myCorazon
 */
@XmlRootElement(name = "Artikel")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportArticle {
    @XmlElement
    String active;
    @XmlElement
    Double tax;
    @XmlElement(name="number")
    String kadisNumber;
    @XmlElement
    List<Price> prices;
    @XmlElement
    Double weight;
    @XmlElement
    Double width;
    @XmlElement
    Double len;
    @XmlElement
    Double height;
    @XmlElement
    String name;
    @XmlElement
    String description;
    @XmlElement
    List<Translation> translations;
    @XmlElement
    Boolean isKadisArticle;
    @XmlElement(name="kadisPurchasePrice")
    BigDecimal purchasePrice;

    @Override
    public String toString() {
        return "ImportArticle{" + "active='" + active + '\'' + ", kadisNumber='" + kadisNumber
                + '\'' + ", name='" + name + '\'' + ", isKadisArticle="+ '\'' + isKadisArticle
                + '\'' + ", prices='" + prices.size() + '\''
                + ", purchasePrice="+ '\'' + purchasePrice + '\'' + '}';
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getKadisNumber() {
        return kadisNumber;
    }

    public void setKadisNumber(String kadisNumber) {
        this.kadisNumber = kadisNumber;
    }

    public List<Price> getPrices() {
        return prices;
    }

    /**
     * set prices and also kadisprice information
     * @param prices
     */
    public void setPrices(List<Price> prices) {
        this.prices = prices;
        setKadisPriceInformation();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLen() {
        return len;
    }

    public void setLen(Double len) {
        this.len = len;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
    public Boolean getKadisArticle() {
        return isKadisArticle;
    }

    public void setKadisArticle(Boolean kadisArticle) {
        isKadisArticle = kadisArticle;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public ImportArticle() {
    }

    public void setKadisPriceInformation() {
//        Price priceVKDP = this.prices.stream().filter(p -> "VK_DP".equals(p.getPriceGroup())).findFirst().orElse(null);
        Price priceVKDP = prices.stream().filter(p -> "VK_DE".equals(p.getPriceGroup())).findFirst().orElse(null);
        boolean isKadisArticle = (priceVKDP!=null) ? true: false;
        this.setKadisArticle(isKadisArticle);
        if (isKadisArticle) { setPurchasePrice(new BigDecimal( priceVKDP.getPrice().toString()));
        } else { setPurchasePrice(new BigDecimal( "0"));
        }
    }

    public ImportArticle(String active, double tax, String kadisNumber, Double weight, Double width, Double len, Double height,
                         String name, String description) {
        this.active = active;
        this.tax = tax;
        this.kadisNumber = kadisNumber;
        this.weight = weight;
        this.width = width;
        this.len = len;
        this.height = height;
        this.name = name;
        this.description = description;
    }

    public String getXML() {
        return ObjectConverter.getXMLFromObject(this);
    }
    public String getJSON() {
        return ObjectConverter.getJSONFromObject(this);
    }

}