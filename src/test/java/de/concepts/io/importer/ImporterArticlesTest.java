package de.concepts.io.importer;

import de.concepts.kadis.ImportArticle;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImporterArticlesTest {
    private static final String RESOURCES_TESTDATA_ARTICLE_XML = "D:/projects/Corazon/WatchDog/src/main/resources/testdata/100116.xml";
    @Test
    void testCreateArticleFromXML() throws ParserConfigurationException, XMLStreamException, SAXException, XPathExpressionException, IOException {
        ImportArticle currentArticle = ImporterArticles.createArticleWithXpath( RESOURCES_TESTDATA_ARTICLE_XML);
        System.out.println(currentArticle.getName() + " has " + currentArticle.getPrices().size() + " prices.");
        assertEquals(currentArticle.getNumber(), "100116");
        assertEquals(currentArticle.getPrices().size(), 7); // 1 empty node
        System.out.println(currentArticle);
    }


}