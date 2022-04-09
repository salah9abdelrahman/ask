package com.salah.ask.service.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReadXmlSaxParser2 {

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream()) {

            SAXParser saxParser = factory.newSAXParser();

            // parse XML and map to object, it works, but not recommend, try JAXB
            MapStaffObjectHandlerSax handler = new MapStaffObjectHandlerSax();

            saxParser.parse(is, handler);

            // print all
            List<Staff> result = handler.getResult();
            result.forEach(System.out::println);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    // get XML file from resources folder.
    private static InputStream getXMLFileAsStream() {
        return ReadXmlSaxParser2.class.getClassLoader().getResourceAsStream("staff.xml");
    }
}