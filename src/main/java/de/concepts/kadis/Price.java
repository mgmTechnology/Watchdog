package de.concepts.kadis;

import java.math.BigDecimal;

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
}
