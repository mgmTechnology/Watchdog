/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package concepts.io.tools;

import de.concepts.io.tools.Helper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void testGetServerEnvironmentVariables() {
        assertTrue(de.concepts.io.tools.Helper.getServerEnvironmentVariables().contains("java.vm.version: 13"));
    }

    @Test
    void testGetPathExtension() {
        String pathXml = "c:\\test\\demo.xml";
        assertEquals("xml", de.concepts.io.tools.Helper.getPathExtension(pathXml));
    }

    @Test
    void testGetFileExtension() {
        File f = new File("c:\\test\\demo.xml");
        assertEquals("xml", de.concepts.io.tools.Helper.getFileExtension(f));
        
    }

    @Test
    void testReadCSVFromFile() {
        // test CSV
        List<String> csvLines = Helper.getLinesFromFile("D:\\projects\\Corazon\\WatchDog\\docs\\kadis\\Beispiel_u_Schemadateien" +
                "\\Preise_die_von_KaDIS_gemeldet_werden\\", "b2c_shopware_artikel_preise.csv");
        //assertNotNull(csvLines);
        assertEquals(39672, csvLines.size());
    }

}