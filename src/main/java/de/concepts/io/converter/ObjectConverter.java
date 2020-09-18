package de.concepts.io.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.concepts.kadis.in.ImportArticle;
import de.concepts.kadis.out.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ObjectConverter {

    /**
     * converts object into JSON
     * @param currentObject
     * @return
     */
    public static String getJSONFromObject(Object currentObject) {
        String jsonContent = "{\"status\": \"unprocessed\"}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(currentObject);
    }

    /**
     * converts an POJO to xml
     * @param currentObject
     * @return
     */
    public static String getXMLFromObject(Object currentObject) {
        String xmlContent = "<result>undefined</result>";
        JAXBContext jaxbContext = null;

        try {
            String simpleName = currentObject.getClass().getSimpleName();
            switch (simpleName) {
                case "Lieferadresse":
                    jaxbContext = JAXBContext.newInstance(Lieferadresse.class);
                    break;
                case "OrderArticle":
                    jaxbContext = JAXBContext.newInstance(OrderArticle.class);
                    break;
                case "ImportArticle":
                    jaxbContext = JAXBContext.newInstance(ImportArticle.class);
                    break;
                case "Bestellung":
                    jaxbContext = JAXBContext.newInstance(Bestellung.class);
                    break;
                case "Kunde":
                    jaxbContext = JAXBContext.newInstance(Kunde.class);
                    break;
                case "Orders":
                    jaxbContext = JAXBContext.newInstance(Orders.class);
                    break;
                case "OrderList":
                    jaxbContext = JAXBContext.newInstance(OrderList.class);
                    break;
                case "Price":
                    jaxbContext = JAXBContext.newInstance(Price.class);
                    break;
                default:
                    System.out.println("could not create xml for " + simpleName);
            }
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); //Create Marshaller

            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false); // remove <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //Required formatting??

            StringWriter sw = new StringWriter(); //Print XML String to Console
            jaxbMarshaller.marshal(currentObject, sw); //Write XML to StringWriter
            xmlContent = sw.toString(); //Verify XML Content
        } catch (Exception e) {
            e.printStackTrace();
            xmlContent = "<result>" + e.getMessage() + "</result>";
        }
        return xmlContent;
    }
}
