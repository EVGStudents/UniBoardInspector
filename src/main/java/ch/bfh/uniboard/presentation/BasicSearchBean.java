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
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.service.MessageFactory;
import ch.bfh.uniboard.service.SearchService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@Named
@SessionScoped
public class BasicSearchBean implements Serializable{

    private static final Logger logger = Logger.getLogger(BasicSearchBean.class.getName());

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

    private boolean hasGroup=true;

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

    public boolean isHasGroup() {
        return hasGroup;
    }

    public void setHasGroup(boolean hasGroup) {
        this.hasGroup = hasGroup;
    }

    public void clearDate(String name){
       if(name.equals("dateFrom")){
           dateFrom=null;
       }
       if(name.equals("dateTo")){
           dateTo=null;
       }
    }

    public String homeBasicSearch() {
        logger.log(Level.INFO, "Redirecting to homepage!");
        
        postDataList = UniBoardClient.getTop50MostRecentPosts();
        return "top50results";
    }

    public void home() {
        logger.log(Level.INFO, "Reloading homepage!");

        postDataList = UniBoardClient.getTop50MostRecentPosts();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            MessageFactory.error("ch.bfh.UniBoard.PAGE_RELOAD_ERROR");
        }
    }
    public String inspect() {
        logger.log(Level.INFO, "Executing inpect() method from homepage!");

        searchResults = SearchService.getBasicSearchResults(section, group, dateFrom, dateTo, limit);
        if (searchResults != null && !searchResults.isEmpty()) {
            PostData data = searchResults.get(0);
            messageKeys = data.getMessageKeys();
            if (group.isEmpty()) {
                hasGroup = false;
            } else {
                hasGroup = true;
            }
            return "basicSearchResults";
        }
        return null;
    }

    public void inspectBasicSearch() {
        logger.log(Level.INFO, "Executing inpect() method from Basic Search results page!");

        searchResults = SearchService.getBasicSearchResults(section, group, dateFrom, dateTo, limit);

        if (searchResults != null && !searchResults.isEmpty()) {
            PostData data = searchResults.get(0);
            messageKeys = data.getMessageKeys();
            // messageKeys.remove(Keys.MESSAGE_ID);
        }
        if (group.isEmpty()) {
            hasGroup = false;
        } else {
            hasGroup = true;
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            MessageFactory.error("ch.bfh.UniBoard.PAGE_RELOAD_ERROR");
        }
    }
}