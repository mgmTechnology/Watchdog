/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.importer;

import de.concepts.io.tools.validator.XMLValidator;
import de.concepts.kadis.Article;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImporterArticles extends ImporterXML{
    public static final String SCHEMA_ORDER_URI = "http://mgm.technology:80/kadis/orders.xsd";
    public static final String SCHEMA_ARTICLE_URI = "http://mgm.technology:80/kadis/inferred_article.xsd";

    public static Article getArticleIfValidXml(String ts, String articleFileName) {
        boolean validXML = false;
        try {
            validXML = XMLValidator.validate("./downloads/" + ts + articleFileName, SCHEMA_ARTICLE_URI);

            if (validXML== true) {
                System.out.println( "Valid article: " + "./downloads/" + ts + articleFileName);
                Article currentArticle = createArticleFromXML("./downloads/" + ts + articleFileName);
                return currentArticle;
            } else {
                System.out.println( "Invalid article: " + "./downloads/" + ts + articleFileName);
                return null;
            }
        } catch (IOException | XMLStreamException | ParserConfigurationException | XPathExpressionException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Article createArticleFromXML(String resourcePath) throws IOException, XMLStreamException, ParserConfigurationException, XPathExpressionException, SAXException {
       Article article = new Article();
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
