/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package concepts.io.tools;

import org.junit.jupiter.api.Test;

import java.io.File;

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


}