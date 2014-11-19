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
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
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
    public void testHandleSectionConstraint(){
        assertNull(ConstraintHandler.handleSectionConstraint(""));
        ConstraintDTO constraint = ConstraintHandler.handleSectionConstraint("HESB");
        assertNotNull(constraint);
    }

    @Test
    public void testHandleGroupConstraint(){
        assertNull(ConstraintHandler.handleGroupConstraint(""));
        ConstraintDTO constraint = ConstraintHandler.handleGroupConstraint("student");
        assertNotNull(constraint);
    }

     @Test
    public void testHandleFromDateTimeConstraint(){
        assertNull(ConstraintHandler.handleFromDateTimeConstraint(null));
         XMLGregorianCalendar fromDate = new XMLGregorianCalendarImpl();
         ConstraintDTO constraint = ConstraintHandler.handleFromDateTimeConstraint(fromDate);
         assertNotNull(constraint);
    }

    @Test
    public void testHandleToDateTimeConstraint(){
         assertNull(ConstraintHandler.handleFromDateTimeConstraint(null));
         XMLGregorianCalendar toDate = new XMLGregorianCalendarImpl();
         ConstraintDTO constraint = ConstraintHandler.handleFromDateTimeConstraint(toDate);
         assertNotNull(constraint);
    }


}
