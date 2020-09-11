package de.concepts.kadis;

public class Price {
    String customerGroupKey;
    String price;
    String from;
    String to;

    public String getCustomerGroupKey() {
        return customerGroupKey;
    }

    public void setCustomerGroupKey(String customerGroupKey) {
        this.customerGroupKey = customerGroupKey;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
