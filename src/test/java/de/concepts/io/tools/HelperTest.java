package de.concepts.io.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void testXML() {
        assertEquals(Helper.testXMLorJSON("<article><no>3434</no><name>Akku</name></article>"), "XML");
    }
    @Test
    void testJSON() {
        assertEquals(Helper.testXMLorJSON("{\"createdBy\":2,\"status\":\"OPEN\",\"totalMargin\":0,\"partialShipment\":false," +
                "\"fresh\":true,\"inquiryDate\":null}"), "JSON");
    }
}