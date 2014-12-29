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
import ch.bfh.uniboard.service.MessageFactory;
import ch.bfh.uniboard.service.SearchService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
public class AdvancedSearchBean implements Serializable{

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
        try {
            System.out.println("********************Rank scope:**************************"+rankScope);
            List<String> groupList = Arrays.asList(groups);
            List<String> sectionList = Arrays.asList(sections);
            searchResults = SearchService.getAdvancedSearchResults(sectionList, groupList, dateFrom, dateTo, limit,rankScope, rank1, rank2);
            return "advancedSearchResults";
        } catch (DatatypeConfigurationException exception) {
            System.out.println("Data type configuration error!");
        } catch (Exception exception) {
            System.out.println("Exception Thrown inspect()");
            MessageFactory.error("ch.bfh.UniBoard.No_SECTION_FOUND");
        }
        return "null";
    }

    public void inspectAdvancedSearch() {

        try {
            List<String> groupList = Arrays.asList(groups);
            List<String> sectionList = Arrays.asList(sections);
            searchResults = SearchService.getAdvancedSearchResults(sectionList, groupList, dateFrom, dateTo, limit,rankScope, rank1, rank2);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

        } catch (DatatypeConfigurationException exception) {
            System.out.println("Data type configuration error!");
        } catch (Exception exception) {
            System.out.println("Exception Thrown inspect()");
            MessageFactory.error("ch.bfh.UniBoard.No_SECTION_FOUND");
        }
    }
     public String home(){
        return "dashboard";
        }
}
