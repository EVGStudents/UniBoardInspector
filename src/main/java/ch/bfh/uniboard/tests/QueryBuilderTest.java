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
import ch.bfh.uniboard.data.QueryDTO;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class QueryBuilderTest {

    QueryBuilder queryBuilder;

    @Before
    public void init() {
        queryBuilder = new QueryBuilder();
    }

    @Test
    public void testBuildquery() {

            QueryDTO query = queryBuilder.buildQuery("HESB", null, null, null, 10);
            assertNotNull(query);
            QueryDTO query1 = queryBuilder.buildQuery();
            assertNotNull(query);
       
    }
}
