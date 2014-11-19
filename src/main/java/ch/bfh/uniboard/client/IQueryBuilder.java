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

package ch.bfh.uniboard.client;

import ch.bfh.uniboard.data.QueryDTO;
import ch.bfh.uniboard.exception.MissingQueryParametersException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public interface IQueryBuilder {
    
    public QueryDTO buildQuery(String section, String group, XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate, int limit )throws MissingQueryParametersException;


}
