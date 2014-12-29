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

import ch.bfh.uniboard.client.UniBoardClient;
import ch.bfh.uniboard.data.DefaultValues;
import ch.bfh.uniboard.data.Keys;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.service.MessageFactory;
import ch.bfh.uniboard.service.SearchService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@ManagedBean
@SessionScoped
public class BasicSearchBean implements Serializable{

    private String section = "";

    private String group = "";

    private Date dateFrom;

    private Date dateTo;

    private String timeFrom = DefaultValues.TIME_FROM;

    private String timeTo = DefaultValues.TIME_TO;

    private int limit = DefaultValues.LIMIT;

    private List<PostData> postDataList = new ArrayList<>();

    private List<PostData> searchResults = new ArrayList<>();

    private List<String> messageKeys = new ArrayList<>();

    @PostConstruct
    public void init() {
        postDataList = UniBoardClient.getTop50MostRecentPosts();
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

    public List<PostData> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<PostData> searchResults) {
        this.searchResults = searchResults;
    }

    public List<PostData> getPostDataList() {
        return postDataList;
    }

    public void setPostDataList(List<PostData> postDataList) {
        this.postDataList = postDataList;
    }

    public List<String> getMessageKeys() {
        return messageKeys;
    }

    public int getMessageSize() {
        return messageKeys.size();
    }

    public String home() {
        return "dashboard";
    }

    public void clearDate(String name){
       if(name.equals("dateFrom")){
           dateFrom=null;
       }
       if(name.equals("dateTo")){
           dateTo=null;
       }
    }

    public String inspect() {
        try {
            searchResults = SearchService.getBasicSearchResults(section, group, dateFrom, dateTo, limit);
            if (searchResults != null && !searchResults.isEmpty()) {
                PostData data = searchResults.get(0);
                messageKeys = data.getMessageKeys();
                messageKeys.remove(Keys.MESSAGE_ID);
            }
            if (!group.isEmpty()) {
                return "basicSearchResults";
            } else {
                postDataList=searchResults;
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            }
        } catch (DatatypeConfigurationException exception) {
            System.out.println("Data type configuration error!");
        } catch (Exception exception) {
            MessageFactory.error("ch.bfh.UniBoard.No_SECTION_FOUND");
        }
        return null;
    }

    public void inspectBasicSearch() {
        try {
            searchResults = SearchService.getBasicSearchResults(section, group, dateFrom, dateTo, limit);
            if (searchResults != null && !searchResults.isEmpty()) {
                PostData data = searchResults.get(0);
                messageKeys = data.getMessageKeys();
                messageKeys.remove(Keys.MESSAGE_ID);
            }
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (DatatypeConfigurationException exception) {
            System.out.println("Data type configuration error!");
        } catch (Exception exception) {
            MessageFactory.error("ch.bfh.UniBoard.No_SECTION_FOUND");
        }
    }
}