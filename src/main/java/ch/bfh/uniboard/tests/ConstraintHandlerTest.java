/*
 * Copyright (c) 2013 Berner Fachhochschule, Switzerland.
 * Bern University of Applied Sciences, Engineering and Information Technology,
 * Research Institute for Security in the Information Society, E-Voting Group,
 * Biel, Switzerland.
 *
 * Project UniVote.
 *
 * Distributable under GPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.uniboard.tests;

import ch.bfh.uniboard.client.ConstraintHandler;
import ch.bfh.uniboard.data.ConstraintDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class ConstraintHandlerTest {

    @Test
    public void testHandleSectionConstraint() {
        assertNull(ConstraintHandler.handleSectionConstraint(""));
        ConstraintDTO constraint = ConstraintHandler.handleSectionConstraint("HESB");
        assertNotNull(constraint);
        List<String> emptyList = null;
        assertNull(ConstraintHandler.handleSectionConstraint(emptyList));
        List<String> sections = new ArrayList<>();
        sections.add("HESB");
        sections.add("EPFL");
        ConstraintDTO listConstraint = ConstraintHandler.handleSectionConstraint(sections);
        assertNotNull(listConstraint);
    }

    @Test
    public void testHandleGroupConstraint() {
        assertNull(ConstraintHandler.handleGroupConstraint(""));
        ConstraintDTO constraint = ConstraintHandler.handleGroupConstraint("student");
        assertNotNull(constraint);
        List<String> emptyList = null;
        assertNull(ConstraintHandler.handleGroupConstraint(emptyList));
        List<String> groups = new ArrayList<>();
        groups.add("student");
        groups.add("certgen");
        ConstraintDTO listConstraint = ConstraintHandler.handleSectionConstraint(groups);
        assertNotNull(listConstraint);
    }

    @Test
    public void testHandleFromDateTimeConstraint() throws DatatypeConfigurationException, ParseException {
        assertNull(ConstraintHandler.handleFromDateTimeConstraint(null));
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = (Date) formatter.parse("12-09-2014");
        calendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        ConstraintDTO constraint = ConstraintHandler.handleFromDateTimeConstraint(xmlCalendar);
        assertNotNull(constraint);
    }

    @Test
    public void testHandleToDateTimeConstraint() throws DatatypeConfigurationException, ParseException {
        assertNull(ConstraintHandler.handleFromDateTimeConstraint(null));
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = (Date) formatter.parse("12-09-2014");
        calendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        ConstraintDTO constraint = ConstraintHandler.handleToDateTimeConstraint(xmlCalendar);
        assertNotNull(constraint);
    }

}
