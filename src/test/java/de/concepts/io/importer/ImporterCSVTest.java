package de.concepts.io.importer;

import de.concepts.kadis.out.Price;
import de.concepts.kadis.in.Stock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImporterCSVTest {
    private static final String RESOURCES_TESTDATA_PRICES_CSV = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/b2c_shopware_artikel_preise.csv";
    private static final String RESOURCES_TESTDATA_INVENTORY_CSV = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/b2c_shopware_artikel_bestand.csv";
    private static final String WATCHDOG_CSV_PRICEGROUP = "H20";

    @Test
    void testGetPricesFromCSV() {
            List<Price> priceList = null;
            try {
                priceList = ImporterCSV.getPricesFromCSV(RESOURCES_TESTDATA_PRICES_CSV, WATCHDOG_CSV_PRICEGROUP);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assertEquals(priceList.size(), 6614);
    }

    @Test
    void testGetInventoryFromCSV() {
        List<Stock> stockList = null;
        try {
            stockList =ImporterCSV.getInventoryFromCSV(RESOURCES_TESTDATA_INVENTORY_CSV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(stockList.size(), 5614);
    }
}