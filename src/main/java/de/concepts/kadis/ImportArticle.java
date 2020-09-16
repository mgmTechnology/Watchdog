/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import com.google.gson.Gson;
import de.concepts.io.exporter.ExporterXML;

import java.util.List;

/**
 * this article is an article to be imported into myCorazon
 */
public class ImportArticle {
    String active;
    Double tax;
    String number;
    List<Price> prices;
    Double weight;
    Double width;
    Double len;
    Double height;
    String name;
    String description;
    List<Translation> translations;

    @Override
    public String toString() {
        return "Article{" + "number='" + number + '\'' + ", prices=" + prices + ", name='" + name + '\'' + ", " +
                "description='" + description + '\'' + '}';
    }

    public String getJSON () {
         return new Gson().toJson(this);
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
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

    public ImportArticle() {
    }

    public ImportArticle(String active, double tax, String number, Double weight, Double width, Double len, Double height,
                         String name, String description) {
        this.active = active;
        this.tax = tax;
        this.number = number;
        this.weight = weight;
        this.width = width;
        this.len = len;
        this.height = height;
        this.name = name;
        this.description = description;
    }

    public String getXML() {
        return ExporterXML.jaxbObjectToXML(this);
    }
}