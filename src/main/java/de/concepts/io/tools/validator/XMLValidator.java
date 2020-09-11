/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools.validator;

import java.io.*;
import java.net.URL;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

public class XMLValidator {
    /**
     * Validates the specified {@link InputStream} against the embedded XML schema.
     * Verifies that {@code stream} contains XML data that conforms to the
     * embedded "schema.xsd". Does nothing on success.
     *
     * @param pathXml an filepath to articles.xml
     * @param schemaFileUrl URI of schema file
     * @throws IOException if an error occurred while reading {@code stream}
     * @throws NullPointerException if {@code stream} is {@code null}
     */
    public static boolean validate(String pathXml, String schemaFileUrl) throws IOException {
        InputStream fisArticles = null;

        try {
            fisArticles =
                    new DataInputStream(new FileInputStream(pathXml));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fisArticles == null) {throw new NullPointerException("stream");}

//        final URL schemaUrl = ArticleValidator.class.getResource(pathXsd);
        URL schemaFile = new URL(schemaFileUrl);
        // local file example:
        // File schemaFile = new File("/location/to/localfile.xsd"); // etc.

        Source xmlFile = new StreamSource(new File(pathXml));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            // System.out.println(xmlFile.getSystemId() + " is valid ");
            return true;
        } catch (SAXException e) {
            //System.out.println(xmlFile.getSystemId() + " is NOT valid :" + e);
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
            return false;}
    }
}