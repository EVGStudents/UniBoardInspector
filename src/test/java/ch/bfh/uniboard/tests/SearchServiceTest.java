package ch.bfh.uniboard.tests;

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

import ch.bfh.uniboard.client.QueryBuilder;
import ch.bfh.uniboard.data.Groups;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.data.Sections;
import ch.bfh.uniboard.service.SearchService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class SearchServiceTest {

    private static final Logger logger = Logger.getLogger(SearchServiceTest.class.getName());

    private QueryBuilder queryBuilder;
    private XMLGregorianCalendar dateFrom;
    private XMLGregorianCalendar dateTo;
    String publickey;
    @Before
    public void init() {
        queryBuilder = new QueryBuilder();
        Date date1 = new Date(2014, 1, 1);
        Date date2 = new Date(2015, 1, 1);

        try{
        dateFrom = SearchService.convertToXMLGregorianCalendar(date1);
        dateTo = SearchService.convertToXMLGregorianCalendar(date2);
        publickey="RNf8/fJqfBlv27VGxWDxxzQhmlRAMaKumjBW7zlYnjcYJ5By3ptSLNQljctO00vh6b0sQ";
        }catch(DatatypeConfigurationException exception){
         logger.log(Level.SEVERE, exception.getMessage());
        }

    }

    @Test
    public void testConvertToXMLGregorianCalendar() throws Exception{
        Date date1 = new Date(2014, 1, 1);
        XMLGregorianCalendar date2 = SearchService.convertToXMLGregorianCalendar(date1);
        assertNotNull(date2);
        XMLGregorianCalendar date3 = SearchService.convertToXMLGregorianCalendar(null);
        assertNull(date3);
    }

    @Test
    public void testgetBasicSearchResults() throws Exception{
        Date date1 = new Date(2014, 1, 1);
        Date date2 = new Date(2015, 1, 1);
        List<PostData> results=SearchService.getBasicSearchResults("BFH", "ElectionData", date1, date2, 50);
        assertNotNull(results);
    }

     @Test
    public void testgetAdvancedSearchResults() throws Exception{
        Date date1 = new Date(2014, 1, 1);
        Date date2 = new Date(2015, 1, 1);
        List<PostData> results=SearchService.getAdvancedSearchResults(Sections.getAllSections(), Groups.getAllGroups(), date1, date2, 50, "Equal To", 1, 50,publickey);
        assertNotNull(results);
    }
    @Test
    public void testgetPublicKeySearchResults() throws Exception{
        Date date1 = new Date(2014, 1, 1);
        Date date2 = new Date(2015, 1, 1);
        List<PostData> results=SearchService.getPublickeySearchResults(publickey, date1, date2, 50);
        assertNotNull(results);
    }
}
