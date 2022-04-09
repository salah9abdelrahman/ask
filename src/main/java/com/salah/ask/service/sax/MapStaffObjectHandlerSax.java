package com.salah.ask.service.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MapStaffObjectHandlerSax extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    private static final String STAFF = "staff";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String SALARY = "salary";
    private static final String BIO = "bio";
    List<Staff> result;
    Staff currentStaff;

    public List<Staff> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);
        switch (qName) {
            case STAFF:
                currentStaff = new Staff();
                String id = attributes.getValue("id");
                currentStaff.setId(Long.valueOf(id));
                break;
            case SALARY:
                String currency = attributes.getValue("currency");
                currentStaff.setCurrency(currency);
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
        switch (qName) {
            case NAME:
                currentStaff.setName(currentValue.toString());
                break;
            case ROLE:
                currentStaff.setRole(currentValue.toString());
                break;
            case SALARY:
                currentStaff.setSalary(new BigDecimal(currentValue.toString()));
                break;
            case BIO:
                currentStaff.setBio(currentValue.toString());
                break;
            case STAFF:
                result.add(currentStaff);
                break;
        }
    }

}
