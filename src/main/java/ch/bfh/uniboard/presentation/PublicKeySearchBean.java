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

import ch.bfh.uniboard.data.DefaultValues;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.exception.ServiceConnectionException;
import ch.bfh.uniboard.service.MessageFactory;
import ch.bfh.uniboard.service.SearchService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@Named
@SessionScoped
public class PublicKeySearchBean implements Serializable{

    private static final Logger logger = Logger.getLogger(PublicKeySearchBean.class.getName());

    private String publicKey;

    private Date dateFrom;

    private Date dateTo;

    private int limit = DefaultValues.LIMIT;

    private List<PostData> searchResults = new ArrayList<>();

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public List<PostData> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<PostData> searchResults) {
        this.searchResults = searchResults;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String inspect(){
           logger.log(Level.INFO, "Executing inpect() method from Search by Public Key page");
        if (publicKey != null && !publicKey.isEmpty()) {
            try {
                searchResults = SearchService.getPublickeySearchResults(publicKey, dateFrom, dateTo, limit);
            } catch (ServiceConnectionException exception) {
                logger.log(Level.SEVERE, exception.getMessage());
                MessageFactory.error("ch.bfh.UniBoardService_Exception");
                return "publicKeySearchResults";
            }
        }
        return "publicKeySearchResults";
    }
}
