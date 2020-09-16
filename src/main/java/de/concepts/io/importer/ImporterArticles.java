/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.importer;

import de.concepts.WatchDogConfiguration;
import de.concepts.io.tools.validator.XMLValidator;
import de.concepts.kadis.ImportArticle;
import de.concepts.kadis.Price;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImporterArticles extends ImporterXML {
    public static final String SCHEMA_ORDER_URI = WatchDogConfiguration.watchdogXMLOrdersSchemaFile;
    public static final String SCHEMA_ARTICLE_URI = WatchDogConfiguration.watchdogXMLArticleSchemaFile;

    /**
     * create article if xml file validates against xsd
     * @param ts
     * @param articleFileName
     * @return
     */
    public static ImportArticle getArticleIfValidXml(String ts, String articleFileName) {
        boolean validXML = false;
        try {
            validXML = XMLValidator.validate("./downloads/" + ts + articleFileName, SCHEMA_ARTICLE_URI);

            if (validXML == true) {
                System.out.println("Valid article: " + "./downloads/" + ts + articleFileName);
                ImportArticle currentArticle = createArticleWithXpath("./downloads/" + ts + articleFileName);
                return currentArticle;
            } else {
                System.out.println("Invalid article: " + "./downloads/" + ts + articleFileName);
                return null;
            }
        } catch (IOException | ParserConfigurationException | XPathExpressionException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * import article data using xpath
     * @param resourcePath
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    public static ImportArticle createArticleWithXpath(String resourcePath) throws ParserConfigurationException, IOException
            , SAXException, XPathExpressionException {
        ImportArticle article = new ImportArticle();
        Price price = new Price();
        List<Price> priceList = new ArrayList<>();
        final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        final Document doc = docBuilder.parse(new File(resourcePath));
        final XPathFactory factory = XPathFactory.newInstance();
        final XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile("/Artikel/mainDetail/number");
        String elementData = (String) expr.evaluate(doc, XPathConstants.STRING);
        article.setNumber(elementData);
        price.setKadisArticleNumber(elementData);
        expr = xpath.compile("/Artikel/name");
        article.setName((String) expr.evaluate(doc, XPathConstants.STRING));
        expr = xpath.compile("/Artikel/description");
        article.setDescription((String) expr.evaluate(doc, XPathConstants.STRING));

        expr = xpath.compile("/Artikel/active");
        article.setActive((String) expr.evaluate(doc, XPathConstants.STRING));
        expr = xpath.compile("/Artikel/tax");
        article.setTax(Double.parseDouble((String) expr.evaluate(doc, XPathConstants.STRING)));
        expr = xpath.compile("/Artikel/mainDetail/height");
        article.setHeight(Double.parseDouble((String) expr.evaluate(doc, XPathConstants.STRING)));
        expr = xpath.compile("/Artikel/mainDetail/weight");
        article.setWeight(Double.parseDouble((String) expr.evaluate(doc, XPathConstants.STRING)));
        expr = xpath.compile("/Artikel/mainDetail/len");
        article.setLen(Double.parseDouble((String) expr.evaluate(doc, XPathConstants.STRING)));
        expr = xpath.compile("/Artikel/mainDetail/width");
        article.setWidth(Double.parseDouble((String) expr.evaluate(doc, XPathConstants.STRING)));
        // check prices
        expr = xpath.compile("//prices");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;

        expr = xpath.compile("//prices");
        result = expr.evaluate(doc, XPathConstants.NODESET);
        nodes = (NodeList) result;
        System.out.println("Article " + article.getNumber() + " --> " + nodes.getLength() + " prices");
        for (int i = 0; i < nodes.getLength(); i++) {
            price = new Price();
            Element el = (Element) nodes.item(i);
             if (!el.hasChildNodes()) break; // skip empty price nodes
//            System.out.println("tag: " + el.getNodeName());
            String customerGroupKey = el.getElementsByTagName("customerGroupKey").item(0).getTextContent();
            String currentPrice = el.getElementsByTagName("price").item(0).getTextContent();
            String to = el.getElementsByTagName("to").item(0).getTextContent();
            String from = el.getElementsByTagName("from").item(0).getTextContent();
            price.setKadisArticleNumber(article.getNumber());
            price.setPriceGroup(customerGroupKey);
            price.setFromQuantity(from);
            price.setToQuantity(to);
            price.setPrice(new BigDecimal(currentPrice));
//            System.out.println(price);
            priceList.add(price);
        }
        article.setPrices(priceList);
        return article;
    }


}
