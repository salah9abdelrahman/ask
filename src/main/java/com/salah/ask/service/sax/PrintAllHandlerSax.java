package com.salah.ask.service.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrintAllHandlerSax extends DefaultHandler {

    private static final String STAFF = "staff";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String SALARY = "salary";
    private static final String BIO = "bio";

    private StringBuilder currentValue = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);
        System.out.printf("Start Element : %s%n", qName);
        switch (qName) {
            case STAFF:
                // get tag's attribute by name
                String id = attributes.getValue("id");
                System.out.printf("Staff id : %s%n", id);
                break;
            case SALARY:
                // get tag's attribute by index, 0 = first attribute
                String currency = attributes.getValue(0);
                System.out.printf("Currency :%s%n", currency);
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // The characters() method can be called multiple times for a single text node.
        // Some values might be missing if assign to a new string

        // avoid doing this
        // value = new String(ch, start, length);

        // better append it, works for single or multiple calls
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("End Element : %s%n", qName);
        switch (qName) {
            case NAME:
                System.out.printf("Name : %s%n", currentValue.toString());
                break;
            case ROLE:
                System.out.printf("Role : %s%n", currentValue.toString());
                break;
            case SALARY:
                System.out.printf("Salary : %s%n", currentValue.toString());
                break;
            case BIO:
                System.out.printf("Bio : %s%n", currentValue.toString());
                break;
        }
    }


}
