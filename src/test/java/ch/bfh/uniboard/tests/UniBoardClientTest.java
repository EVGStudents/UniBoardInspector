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
import ch.bfh.uniboard.client.UniBoardClient;
import ch.bfh.uniboard.data.PostDTO;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.data.QueryDTO;
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
public class UniBoardClientTest {

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
     public void testgetTop50MostRecentPosts() {
     List<PostData> posts=UniBoardClient.getTop50MostRecentPosts();
     assertNotNull(posts);
}
     @Test
     public void testBuidQuery(){
         QueryDTO query = queryBuilder.buildQuery();
         List<PostDTO> posts=UniBoardClient.sendQuery(query);
         assertNotNull(posts);
         assertNull(UniBoardClient.sendQuery(null));
     }
}