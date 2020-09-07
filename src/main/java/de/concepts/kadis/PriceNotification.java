package de.concepts.kadis;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class PriceNotification {

    int id;
    String ordernumber;
    String pricegroup;
    String von;
    String zu;
    BigDecimal price;
    BigDecimal pseudoprice;
    BigDecimal baseprice;
    String bulkgroup;
    String taxgroup;
    BigDecimal taxvalue;
    String comment;

    public   String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PriceNotification{" + "id=" + id + ", ordernumber='" + ordernumber + '\'' + ", pricegroup='" + pricegroup
                + '\'' + ", price=" + price + ", comment='" + this.getComment() + '\'' + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getPricegroup() {
        return pricegroup;
    }

    public void setPricegroup(String pricegroup) {
        this.pricegroup = pricegroup;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public String getZu() {
        return zu;
    }

    public void setZu(String zu) {
        this.zu = zu;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPseudoprice() {
        return pseudoprice;
    }

    public void setPseudoprice(BigDecimal pseudoprice) {
        this.pseudoprice = pseudoprice;
    }

    public BigDecimal getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(BigDecimal baseprice) {
        this.baseprice = baseprice;
    }

    public String getBulkgroup() {
        return bulkgroup;
    }

    public void setBulkgroup(String bulkgroup) {
        this.bulkgroup = bulkgroup;
    }

    public String getTaxgroup() {
        return taxgroup;
    }

    public void setTaxgroup(String taxgroup) {
        this.taxgroup = taxgroup;
    }

    public BigDecimal getTaxvalue() {
        return taxvalue;
    }

    public void setTaxvalue(BigDecimal taxvalue) {
        this.taxvalue = taxvalue;
    }
}
