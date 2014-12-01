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

import ch.bfh.uniboard.presentation.BasicSearchBean;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class BasicSearchBeanTest {

    BasicSearchBean bean;

    @Before
    public void init() {
        bean = new BasicSearchBean();
    }

    @Test
    public void testInspect() {

        bean.setSection("HESB");
        bean.setGroup("student");
        String expected = "basicSearch";
        String actual = bean.inspect();
        assertEquals(expected, actual);
    }

    @Test
    public void testViewPost() {

//        PostDTO post = new PostDTO(null, null, null);
//        PostData postData = new PostData(post);
//        bean.setSelectedPost(postData);
//        String expected = "postdetails";
//        String actual = bean.viewPost(postData);
//        assertEquals(expected, actual);

    }
}
