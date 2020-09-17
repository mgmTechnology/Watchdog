package de.concepts.io.exporter;

import de.concepts.kadis.out.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ExporterXML {
    public static String jaxbObjectToXML(Object currentObject) {
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
