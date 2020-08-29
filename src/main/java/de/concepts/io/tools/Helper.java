/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Helper class
 */
public class Helper {

    /**
     * selftest for unirest framework
     *
     * @return should return status 200
     */
    public static String checkUnirest() {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154").header(
                "accept", "application/json").queryString("apiKey", "123").asJson();
        if (String.valueOf(jsonResponse.getStatus()).equals("200")) return "Unirest Java working fine.";
        else return "Unireset Java not working as expected.";
    }

    /** simple selftest for GSON library
     *
     * @return
     */
    public static String checkJsonHandling() {
        String result = "GSON test failed";
        // use unirest for getting test data
        HttpResponse<String> jsonResponse = Unirest.get("https://run.mocky.io/v3/37b6f667-54a9-4440-bc0a-681d8e1da8a3").header(
                "accept", "application/json").asString();

        // start with GSON
        JsonParser parser = new JsonParser();
        JsonObject rootObj = parser.parse(jsonResponse.getBody()).getAsJsonObject();
        JsonArray usersArray = rootObj.getAsJsonArray("users");

        for (JsonElement pa : usersArray) {
            JsonObject userObject = pa.getAsJsonObject();
            String prename = userObject.get("prename").getAsString();
            String surname = userObject.get("surname").getAsString();
            if ("Kornelia".equalsIgnoreCase(prename)) result = "GSON test passed.";
//            System.out.println(prename + " " + surname);
        }
    return result;
    }

    public static String checkXmlHandling() {
        String result = "GSON test failed";
        HttpResponse<String> response = Unirest.get("https://run.mocky.io/v3/f593b9cb-665f-43e2-99f6-89b1334c5c1d").header(
                "accept", "application/xml").asString();

        String plainXml = response.getBody().replace(System.getProperty("line.separator"), "");
//        System.out.println(plainXml);
        if (plainXml.contains("<surname>Pticar</surname>")) result = "JaxB works fine.";
        result = plainXml;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse( new ByteArrayInputStream(plainXml.getBytes()));
            doc.getDocumentElement().normalize();
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            result = "XML root element: "+ doc.getDocumentElement().getNodeName();

            NodeList nList = doc.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                result = "XML processed: ";
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
//                    System.out.println("First Name : " + eElement.getElementsByTagName("prename").item(0).getTextContent());
//                    System.out.println("Last Name : " + eElement.getElementsByTagName("surname").item(0).getTextContent());
                }
            }
            result = "XMl processed.";
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * enlist environment variables to single string
     *
     * @return
     */
    public static String getServerEnvironmentVariables() {
        StringBuilder retVal = new StringBuilder();
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) p.get(key);
//            if ("key".contains("runtime.version")) {
                retVal.append(key + ": " + value + "\n");
//            }
        }
        return retVal.toString();
    }

    /**
     * get the extension of a file
     *
     * @param file
     * @return
     */
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".") + 1;
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /**
     * get extension of a given absolut path
     *
     * @param fullPath
     * @return
     */
    public static String getPathExtension(String fullPath) {
        String extension = "";

        if (fullPath.contains(".")) {
            extension = fullPath.substring(fullPath.lastIndexOf(".") + 1);
        }
        return extension;
    }

    /**
     * print the watchdog ascii image
     */
    public static void printAsciiWatchDog() {
        // BuildMyString.com generated code. Please enjoy your string responsibly.


        String theDog =
                "\n........................................................ .. ..... ... ... ..  .." + "\n.." + "." + "....................................................       .     .      .    " + "\n.........." + "......" + "............................ Z..M..............................." + "\n.................." + "..........." + "...............MM..M..............................." + "\n.........................." + "................" + ".?MMM.M .............................." + "\n.................................." + ".........MMMM7MI....." + "........................." + "\n.........................................." + ".7MMMMMMMMMM.............." + "............" + "\n..........................................." + ".MMMMMMMMMMM........................." + "\n..........................................." + ".MMMMMMMMMMMMMMM ...................." + "\n.......................................... " + "MMMMMMMMMMMMMMMM. .... ... . . . ...." + "\n......................................... " + "MMMMMMMMMMMMMMMMM   . .     . . . . . " + "\n.................................... ..." + ".MMMMMMMMMMMMMMMMMM.  . ....  . .  . . ." + "\n......................................." + ".MMMMMMMMMMMMMMMMMM.... ......  .. ....  " + "\n ..................................... MMMMMMMMMMM.." + "..~......... .. ..... .... ." + "\n............................. ....... MMMMMMMMMMM....... ..  .. ." + ".. . ..  ... .." + "\n.....................................MMMMMMMMMMMM....... ......   ..   .   ..." + " ." + "\n....................................MMMMMMMMMMMMMM...... . . . .   .......... .." + "\n...." + " .... ....  ... .. ... ......OMMMMMMMMMMMMMMMM.............................." + "\n..... ...... ..  " + ".. ..  .. .....DMMMMMMMMMMMMMMMMM .......... . ................" + "\n....................... ......" + ".MMMMMMMMMMMMMMMMMMMO........ ...................." + "\n.................... ........" + ".MMMMMMMMMMMMMMMMMMMMZ............................." + "\n....  .. .... . ..  .. ..  " + ".$MMMMMMMMMMMMMMMMMMMMM.............................." + "\n. .. . .... ..... . .    ." + ".MMMMMMMMMMMMMMMMMMMMMM:.............................." + "\n ........................" + ".MMMMMMMMMMMMMMMMMMMMMM+..................... ........." + "\n...............   ....." + ".MMMMMMMMMMMMMMMMMMMMMMMM................................" + "\n .... .......... .. .  " + "MMMMMMMMMMMMMMMMMMMMMMMM8................................" + "\n................. ... " + "MMMMMMMMMMMMMMMMMMMMMMMMM+................................" + "\n...... ............  " + "MMMMMMMMMMMMMMMMMMM: MMMMM:................................" + "\n.............. .  .. " + "MMMMMMMMMMMMMMMMMZ ..MMMMM,................................" + "\n....... ...... . ... " + "MMMMMMMMMMMMMMMMM ...MMMMM ................................" + "\n................. . " + "MMMMMMMMMMMMMMMMMM....MMMMM ........... . ... . ........  . " + "\n.................." + ".8MMMMMMMMMMMMMMMMM.....MMMMM ................................" + "\n.................." + ".NMMMMMMMMMMMMMMM ......MMMMM ............... . ...    ... .. " + "\n.... .........   ." + ".MMMMMMMMMMMMMM.........MMMMM........... .....  . ... . . . .." + "\n.... . .. ... ..  " + "MMMMMMMMMMMMMI..   .....MMM,MM.. .....  .  .. . .  .    ...  ." + "\n ... ....      ..." + ".MMMDMMMMMMMMMMMMMMMM..OMM.MMM....... .. ..... ............ ." + "\n.......... ........." + ".OMMMMMMMMMMMMMMM ... MMMMM....... ..    . ... ... .....  ." + "\n .    .   .  .      " + "::::::::::::::::,......:::::.......                         " + "                                   " + "                    ";
        System.out.println(theDog);
        //logger.info(theDog);
    }


}
