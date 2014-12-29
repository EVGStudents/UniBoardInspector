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
package ch.bfh.uniboard.service;

import ch.bfh.uniboard.client.QueryBuilder;
import ch.bfh.uniboard.client.UniBoardClient;
import ch.bfh.uniboard.data.PostDTO;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.data.QueryDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class SearchService {

    public static boolean validateTime(String time) {
        Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) throws Exception{
        if(date!=null){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        return xmlCalendar;
        }
        return null;
    }

    public static List<PostData> getBasicSearchResults(String section, String group, Date dateFrom, Date dateTo, int limit) throws Exception{

        QueryBuilder builder = new QueryBuilder();
        List<PostData> searchResults = new ArrayList<>();
        XMLGregorianCalendar dateBegin = convertToXMLGregorianCalendar(dateFrom);
        XMLGregorianCalendar dateEnd = convertToXMLGregorianCalendar(dateTo);
        QueryDTO query = builder.buildQuery(section, group, dateBegin, dateEnd, limit);

        List<PostDTO> posts = UniBoardClient.sendQuery(query);
        if (posts != null && !posts.isEmpty()) {
            for (PostDTO post : posts) {
                PostData data = new PostData(post);
                searchResults.add(data);
            }
            return searchResults;
        }
          return null;
    }

    public static List<PostData> getAdvancedSearchResults(List<String> sections, List<String> groups,
            Date dateFrom, Date dateTo, int limit, String rankScope, int rank1, int rank2) throws Exception{

        QueryBuilder builder = new QueryBuilder();
        List<PostData> searchResults = new ArrayList<>();
        XMLGregorianCalendar dateBegin= null;
        XMLGregorianCalendar dateEnd= null;
        if (dateFrom != null) {
            dateBegin = convertToXMLGregorianCalendar(dateFrom);
        }

        if (dateTo != null) {
            dateEnd = convertToXMLGregorianCalendar(dateTo);
        }
            QueryDTO query = builder.buildQuery(sections, groups, dateBegin, dateEnd, limit,rankScope,rank1, rank2);

            List<PostDTO> posts = UniBoardClient.sendQuery(query);
            if(posts!=null && !posts.isEmpty()){
            for (PostDTO post : posts) {
                PostData data = new PostData(post);
                searchResults.add(data);
            }
            return searchResults;
            }
          return null;
    }

    public static List<PostData> getPublickeySearchResults(String publickey,Date dateFrom, Date dateTo, int limit) throws Exception{
        QueryBuilder builder = new QueryBuilder();
        List<PostData> searchResults = new ArrayList<>();
        XMLGregorianCalendar dateBegin = null;
        XMLGregorianCalendar dateEnd = null;
        if (dateFrom != null) {
            dateBegin = convertToXMLGregorianCalendar(dateFrom);
        }
        if (dateTo != null) {
            dateEnd = convertToXMLGregorianCalendar(dateTo);
        }
        QueryDTO query = builder.buildQuery(publickey, dateBegin, dateEnd, limit);
        List<PostDTO> posts = UniBoardClient.sendQuery(query);
        System.out.println("Post size:"+posts.size());
        if (posts != null && !posts.isEmpty()) {
            for (PostDTO post : posts) {
                PostData data = new PostData(post);
                searchResults.add(data);
            }
            System.out.println("Search Results size:"+searchResults.size());
            return searchResults;
        }
       return null;
    }
}
