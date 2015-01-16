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

import ch.bfh.uniboard.client.QueryBuilder;
import ch.bfh.uniboard.data.Groups;
import ch.bfh.uniboard.data.QueryDTO;
import ch.bfh.uniboard.data.Sections;
import ch.bfh.uniboard.service.SearchService;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class QueryBuilderTest {
    private static final Logger logger = Logger.getLogger(QueryBuilderTest.class.getName());

    private QueryBuilder queryBuilder;
    private XMLGregorianCalendar dateFrom;
    private XMLGregorianCalendar dateTo;

    @Before
    public void init() {
        queryBuilder = new QueryBuilder();
        Date date1 = new Date(2014, 1, 15);
        Date date2 = new Date(2015, 1, 1);
        try{
        dateFrom = SearchService.convertToXMLGregorianCalendar(date1);
        dateTo = SearchService.convertToXMLGregorianCalendar(date2);
        }catch(DatatypeConfigurationException exception){
         logger.log(Level.SEVERE, exception.getMessage());
        }
    }

    @Test
    public void testBuildQuery() {
             QueryDTO query1 = queryBuilder.buildQuery();
            assertNotNull(query1);
            QueryDTO query2 = queryBuilder.buildQuery("BFH","Voters", dateFrom, dateTo, 10);
            assertNotNull(query2);
            QueryDTO query3 = queryBuilder.buildQuery("RNf8/fJqfBlv27VGxWDxxzQhmlRAMaKumjBW7zlYnjcYJ5By3ptSLNQljctO00vh6b0sQ", dateFrom, dateTo,10);
            assertNotNull(query3);
            QueryDTO query4 = queryBuilder.buildQuery(Sections.getAllSections(), Groups.getAllGroups(), dateFrom, dateTo, 10, "Equal To", 1, 50, "afadkhgfjk");
            assertNotNull(query3);
             QueryDTO query5 = queryBuilder.buildQuery(Sections.getAllSections(), Groups.getAllGroups(), dateFrom, dateTo, 10, "Less Than", 1, 50, "afadkhgfjk");
            assertNotNull(query5);
             QueryDTO query6 = queryBuilder.buildQuery(Sections.getAllSections(), Groups.getAllGroups(), dateFrom, dateTo, 10, "More Than", 1, 50, "afadkhgfjk");
            assertNotNull(query6);
             QueryDTO query7 = queryBuilder.buildQuery(Sections.getAllSections(), Groups.getAllGroups(), dateFrom, dateTo, 10, "Between", 1, 50, "afadkhgfjk");
            assertNotNull(query7);

    }
}
