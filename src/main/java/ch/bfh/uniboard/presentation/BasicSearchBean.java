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
package ch.bfh.uniboard.presentation;

import ch.bfh.uniboard.client.QueryBuilder;
import ch.bfh.uniboard.client.UniBoardClient;
import ch.bfh.uniboard.data.DefaultValues;
import ch.bfh.uniboard.data.PostDTO;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.data.QueryDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@ManagedBean
@SessionScoped
public class BasicSearchBean {

    private String section = "";

    private String group = "";

    private Date dateFrom;

    private Date dateTo;

    private String timeFrom = DefaultValues.TIME_FROM;

    private String timeTo = DefaultValues.TIME_TO;

    private int limit = DefaultValues.LIMIT;

    private List<PostData> postData = new ArrayList<>();

    private List<PostData> searchResults = new ArrayList<>();

    private List<String> messageKeys = new ArrayList<>();

    @PostConstruct
    public void init() {
        postData = UniBoardClient.getTop50MostRecentPosts();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public List<PostData> getPostData() {
        return postData;
    }

    public List<PostData> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<PostData> searchResults) {
        this.searchResults = searchResults;
    }

    public List<String> getMessageKeys() {
        return messageKeys;
    }

    public int getMessageSize() {
        return messageKeys.size();
    }
public String home(){
    return "dashboard";
}

    public String inspect() {

        QueryBuilder builder = new QueryBuilder();
        searchResults = new ArrayList<>();
        try {
            XMLGregorianCalendar dateBegin = SearchService.convertToXMLGregorianCalendar(dateFrom);
            XMLGregorianCalendar dateEnd=SearchService.convertToXMLGregorianCalendar(dateTo);
            QueryDTO query = builder.buildQuery(section, group, dateBegin, dateEnd, limit);

            List<PostDTO> posts = UniBoardClient.sendQuery(query);
            boolean found = false;
            for (PostDTO post : posts) {
                PostData data = new PostData(post);
                if (!found) {
                    messageKeys = data.getMessageKeys();
                    found = true;
                }
                searchResults.add(data);

            }
            return "basicSearchResults";

        }
        catch(DatatypeConfigurationException exception){
            System.out.println("Data type configuration error!");
        }catch (Exception exception) {
            MessageFactory.error("ch.bfh.UniBoard.No_SECTION_FOUND");
        }

        return "null";
    }
}
