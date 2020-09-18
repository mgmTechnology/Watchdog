/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.importer;

import de.concepts.kadis.out.Price;
import de.concepts.kadis.in.Stock;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * inherited class for importing CSV files
 */
public class ImporterCSV {
    /**
     *  get list of Prices to be imported
     * @param csvFileName
     * @param priceGroup
     * @return
     */
    public static List<Price> getPricesFromCSV(String csvFileName, String priceGroup) {
        System.out.println("Handling prices with pricegroup " + priceGroup);
        try {
            List<Price> priceList ;
            Stream<String> rows = Files.lines(Paths.get(csvFileName));
            priceList = rows
                    .skip(1)
                    .map(row -> row.split(";"))
                    .filter(row -> row[2].equals(priceGroup))
                    .map(row -> new Price(row[1],row[2],row[3], row[4], row[5], row[9], row[10]))
                    .collect(Collectors.toList());
            return priceList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * get stock numbers to be imported
     * @param csvFileName
     * @return
     */
    public static List<Stock> getInventoryFromCSV(String csvFileName) {
        System.out.println("Handling inventory");
        List<Stock> stockList ;
        try {
            Stream<String> rows = Files.lines(Paths.get(csvFileName));
            stockList = rows
                    .skip(1)
                    .map(row -> row.split(";"))
                    .map(row -> new Stock(row[0],Integer.parseInt(row[1]),Integer.parseInt(row[2])))
                    .collect(Collectors.toList());
            return stockList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "ImporterCSV{}";
    }
}
