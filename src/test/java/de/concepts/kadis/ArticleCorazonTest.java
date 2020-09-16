package de.concepts.kadis;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ArticleCorazonTest {

    @Test

    void testGetXML() {
        ArticleCorazon article = new ArticleCorazon("123456",2.0,"XXL", "Junit Testartikel",
                new BigDecimal("23.3"), 19.0, "I");
        System.out.println(article.getXML());
    }
}