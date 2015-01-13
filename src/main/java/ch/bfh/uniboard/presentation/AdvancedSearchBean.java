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
import ch.bfh.uniboard.service.SearchService;
import java.io.Serializable;
import java.util.Arrays;
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
public class AdvancedSearchBean implements Serializable{

    private static final Logger logger = Logger.getLogger(AdvancedSearchBean.class.getName());

    private String sectionScope;
    private String[] sections;
    private String groupScope;
    private String[] groups;
    private Date dateFrom;
    private Date dateTo;
    private int limit = DefaultValues.LIMIT;
    private String rankScope;
    private int rank1;
    private int rank2;
    private String publicKey;
    private List<PostData> searchResults;

    public String getSectionScope() {
        return sectionScope;
    }

    public void setSectionScope(String sectionScope) {
        this.sectionScope = sectionScope;
    }

    public String getRankScope() {
        return rankScope;
    }

    public void setRankScope(String rankScope) {
        this.rankScope = rankScope;
    }

    public String[] getSections() {
        return sections;
    }

    public void setSections(String[] sections) {
        this.sections = sections;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getGroupScope() {
        return groupScope;
    }

    public void setGroupScope(String groupScope) {
        this.groupScope = groupScope;
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

    public int getRank1() {
        return rank1;
    }

    public void setRank1(int rank1) {
        this.rank1 = rank1;
    }

    public int getRank2() {
        return rank2;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setRank2(int rank2) {
        this.rank2 = rank2;
    }

    public List<PostData> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<PostData> searchResults) {
        this.searchResults = searchResults;
    }

    public String inspect() {
            logger.log(Level.INFO, "Executing inpect() method from Advanced Search page");

            List<String> groupList = Arrays.asList(groups);
            List<String> sectionList = Arrays.asList(sections);
            if (sectionList != null && !sectionList.isEmpty()) {
            searchResults = SearchService.getAdvancedSearchResults(sectionList, groupList, dateFrom, dateTo, limit, rankScope, rank1, rank2, publicKey);
        }
            return "advancedSearchResults";
    }

//    public void inspectAdvancedSearch() {
//
//            List<String> groupList = Arrays.asList(groups);
//            List<String> sectionList = Arrays.asList(sections);
//            searchResults = SearchService.getAdvancedSearchResults(sectionList, groupList, dateFrom, dateTo, limit,rankScope, rank1, rank2,publicKey);
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//           try {
//            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//        } catch (IOException exception) {
//            logger.log(Level.SEVERE, exception.getMessage());
//            MessageFactory.error("ch.bfh.UniBoard.PAGE_RELOAD_ERROR");
//        }
//    }
     public String home(){
        return "dashboard";
        }
}
