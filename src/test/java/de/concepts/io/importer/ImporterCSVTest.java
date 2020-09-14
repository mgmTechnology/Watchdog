package de.concepts.io.importer;

import de.concepts.WatchDogConfiguration;
import de.concepts.io.tools.validator.XMLValidator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImporterCSVTest {
    private static final String RESOURCES_TESTDATA_PRICES_CSV = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/b2c_shopware_artikel_preise.csv";
    private static final String RESOURCES_TESTDATA_INVENTORY_CSV = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/b2c_shopware_artikel_bestand.csv";
    private static final String WATCHDOG_CSV_PRICEGROUP = "H20";

    @Test
    void testGetPricesFromCSV() {
            boolean validCSV = false;
            try {
                validCSV= ImporterCSV.getPricesFromCSV(RESOURCES_TESTDATA_PRICES_CSV, WATCHDOG_CSV_PRICEGROUP);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assertEquals(validCSV, true);
    }

    @Test
    void testGetInventoryFromCSV() {
        boolean validCSV = false;
        try {
            validCSV =ImporterCSV.getInventoryFromCSV(RESOURCES_TESTDATA_PRICES_CSV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(validCSV, true);
    }
}