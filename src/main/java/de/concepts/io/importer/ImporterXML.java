/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.importer;

import com.google.gson.Gson;
import de.concepts.kadis.Article;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




/**
 * inherited class for importing XML files
 */
public class ImporterXML extends Importer {
    @Override
    public String toString() {
        return "ImporterXML{}";
    }

    public static Article createArticleFromXML(String resourcePath) throws IOException, XMLStreamException, ParserConfigurationException, XPathExpressionException, SAXException {
       Article article = new Article();
//        FileInputStream fileIS = new FileInputStream(resourcePath);
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = builderFactory.newDocumentBuilder();
//        XPath xPath = XPathFactory.newInstance().newXPath();
//        String expression = "//active";
//        Document xmlDocument = builder.parse(fileIS);
//        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);


        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        XMLStreamReader input = null;
        try (FileInputStream file = new FileInputStream(resourcePath)) {
            input = factory.createXMLStreamReader(file);

            Map<String, String> map = new HashMap<>();
            while (input.hasNext()) {
                input.next();
                if (input.isStartElement()) {
                    switch (input.getLocalName()){
                        case "active" : article.setActive(input.getElementText());
                        break;
                        case "number" : article.setNumber(input.getElementText());
                        break;
                        case "name" : article.setName(input.getElementText());
                            break;
                        case "tax" : article.setTax(input.getElementText());
                            break;
                        case "height" : article.setHeight(input.getElementText());
                            break;
                        case "len" : article.setLen(input.getElementText());
                            break;
                        case "weight" : article.setWeight(input.getElementText());
                            break;
                        case "width" : article.setWidth(input.getElementText());
                            break;
                        case "prices" :
                            System.out.println(input.getElementText());
                            break;
                    }
                }
            }
            System.out.println(article.toString());
            System.out.println(article.getJSON());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return article;
    }
}
