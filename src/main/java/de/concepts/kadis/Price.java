/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Price {
    String currentId;
    String kadisArticleNumber;
    String priceGroup;
    String fromQuantity;
    String toQuantity; // can be beliebig
    BigDecimal price;
    String taxGroup;
    BigDecimal taxValue;

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getKadisArticleNumber() {
        return kadisArticleNumber;
    }

    public void setKadisArticleNumber(String kadisArticleNumber) {
        this.kadisArticleNumber = kadisArticleNumber;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup;
    }

    public String getFromQuantity() {
        return fromQuantity;
    }

    public void setFromQuantity(String fromQuantity) {
        this.fromQuantity = fromQuantity;
    }

    public String getToQuantity() {
        return toQuantity;
    }

    public void setToQuantity(String toQuantity) {
        this.toQuantity = toQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(String taxGroup) {
        this.taxGroup = taxGroup;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

    @Override
    public String toString() {
        return "Price{" + "kadisArticleNumber='" + kadisArticleNumber + '\'' + ", priceGroup='" + priceGroup + '\'' + ", price=" + price + '}';
    }

    public Price() {
    }

    public Price(String kadisArticleNumber, String priceGroup, String fromQuantity, String toQuantity,
                 String price, String taxGroup, String taxValue) {
        this.kadisArticleNumber = kadisArticleNumber;
        this.priceGroup = priceGroup;
        this.fromQuantity = fromQuantity;
        this.toQuantity = toQuantity;
        try {
            Number aNumber = NumberFormat.getNumberInstance(Locale.GERMANY).parse(price);
            this.price=  new BigDecimal(aNumber.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.taxGroup = taxGroup;
        try {
            Number aNumber = NumberFormat.getNumberInstance(Locale.GERMANY).parse(taxValue);
            this.taxValue=  new BigDecimal(aNumber.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
