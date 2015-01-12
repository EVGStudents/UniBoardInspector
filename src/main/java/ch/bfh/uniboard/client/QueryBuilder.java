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

import ch.bfh.uniboard.data.BetaIdentifierDTO;
import ch.bfh.uniboard.data.ConstraintDTO;
import ch.bfh.uniboard.data.DefaultValues;
import ch.bfh.uniboard.data.Groups;
import ch.bfh.uniboard.data.OrderDTO;
import ch.bfh.uniboard.data.QueryDTO;
import ch.bfh.uniboard.data.Sections;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class QueryBuilder {

    private static final Logger logger = Logger.getLogger(QueryBuilder.class.getName());

    public QueryDTO buildQuery() {

        logger.info("Build query to obtain Top 50 most recent posts");

        List<ConstraintDTO> constraintList = new ArrayList<>();

        List<String> sections = Sections.getAllSections();

        List<String> groups = Groups.getAllGroups();

        ConstraintDTO sectionConstraint = ConstraintHandler.handleSectionConstraint(sections);

        ConstraintDTO groupConstraint = ConstraintHandler.handleGroupConstraint(groups);

        constraintList.add(sectionConstraint);

        constraintList.add(groupConstraint);

        List<OrderDTO> orderList = orderByDate(false);

        QueryDTO query = new QueryDTO(constraintList, orderList, DefaultValues.LIMIT);

        return query;
    }

    public QueryDTO buildQuery(String section, String group, XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate, int limit) {

        logger.info("Build query for basic search");
        if (section == null || section.isEmpty()) {
            return null;
        }
        List<ConstraintDTO> constraintList = new ArrayList<>();

        ConstraintDTO sectionConstraint = ConstraintHandler.handleSectionConstraint(section);
        ConstraintDTO groupConstraint = ConstraintHandler.handleGroupConstraint(group);
        ConstraintDTO fromDateConstraint = ConstraintHandler.handleFromDateTimeConstraint(fromDate);
        ConstraintDTO toDateConstraint = ConstraintHandler.handleToDateTimeConstraint(toDate);

        constraintList.add(sectionConstraint);
        if (groupConstraint != null) {
            constraintList.add(groupConstraint);
        }
        if (fromDateConstraint != null) {
            constraintList.add(fromDateConstraint);
        }
        if (toDateConstraint != null) {
            constraintList.add(toDateConstraint);
        }
        List<OrderDTO> orderList = orderByDate(false);

        if (limit <= 0) {
            limit = DefaultValues.LIMIT;
        }

        QueryDTO query = new QueryDTO(constraintList, orderList, limit);

        return query;
    }

    public QueryDTO buildQuery(List<String> sections, List<String> groups, XMLGregorianCalendar fromDate,
            XMLGregorianCalendar toDate, int limit, String rankScope, int rank1, int rank2, String publicKey){

        logger.info("Build query for advancedSearch");

        if (sections == null || sections.isEmpty()) {
            return null;
        }
        List<ConstraintDTO> constraintList = new ArrayList<>();

        ConstraintDTO sectionConstraint = ConstraintHandler.handleSectionConstraint(sections);
        ConstraintDTO groupConstraint = ConstraintHandler.handleGroupConstraint(groups);
        ConstraintDTO fromDateConstraint = ConstraintHandler.handleFromDateTimeConstraint(fromDate);
        ConstraintDTO toDateConstraint = ConstraintHandler.handleToDateTimeConstraint(toDate);

        constraintList.add(sectionConstraint);
        if (groupConstraint != null) {
            constraintList.add(groupConstraint);
        }
        if (fromDateConstraint != null) {
            constraintList.add(fromDateConstraint);
        }
        if (toDateConstraint != null) {
            constraintList.add(toDateConstraint);
        }
        List<OrderDTO> orderList = orderByDate(false);

        if (rankScope != null && !rankScope.isEmpty()) {
            if (rankScope.equals("Equal To") && rank1 > 0) {
                ConstraintDTO rankConstraint = ConstraintHandler.handleRankConstraintEqual(rank1);
                constraintList.add(rankConstraint);
            }
            if (rankScope.equals("More Than") && rank1 > 0) {
                ConstraintDTO rankConstraint = ConstraintHandler.handleRankConstraintMoreThan(rank1);
                constraintList.add(rankConstraint);
            }
            if (rankScope.equals("Less Than") && rank1 > 0) {
                ConstraintDTO rankConstraint = ConstraintHandler.handleRankConstraintLessThan(rank1);
                constraintList.add(rankConstraint);
            }
            if (rankScope.equals("Between") && rank2 > 0 && rank1 >= 0) {
                ConstraintDTO rankConstraint1 = ConstraintHandler.handleRankConstraintMoreThan(rank1);
                ConstraintDTO rankConstraint2 = ConstraintHandler.handleRankConstraintLessThan(rank2);
                constraintList.add(rankConstraint1);
                constraintList.add(rankConstraint2);
            }
        }

        ConstraintDTO publicKeyConstraint = ConstraintHandler.handlePublicKeyConstraint(publicKey);

        if(publicKeyConstraint!=null){
            constraintList.add(publicKeyConstraint);
        }

        if (limit <= 0) {
            limit = DefaultValues.LIMIT;
        }

        QueryDTO query = new QueryDTO(constraintList, orderList, limit);

        return query;

    }

    public QueryDTO buildQuery(String publickey, XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate, int limit){
        logger.info("Build query for search by Public Key");

        if(publickey==null || publickey.isEmpty()){
            return null;
        }

        List<ConstraintDTO> constraintList = new ArrayList<>();
        List<String> sections = Sections.getAllSections();

        List<String> groups = Groups.getAllGroups();

        ConstraintDTO sectionConstraint = ConstraintHandler.handleSectionConstraint(sections);

        ConstraintDTO groupConstraint = ConstraintHandler.handleGroupConstraint(groups);

        constraintList.add(sectionConstraint);
        constraintList.add(groupConstraint);

        ConstraintDTO publickeyConstraint = ConstraintHandler.handlePublicKeyConstraint(publickey);
        ConstraintDTO fromDateConstraint = ConstraintHandler.handleFromDateTimeConstraint(fromDate);
        ConstraintDTO toDateConstraint = ConstraintHandler.handleToDateTimeConstraint(toDate);

        if (publickeyConstraint != null) {
            constraintList.add(publickeyConstraint);
        }
        if (fromDateConstraint != null) {
            constraintList.add(fromDateConstraint);
        }
        if (toDateConstraint != null) {
            constraintList.add(toDateConstraint);
        }
        List<OrderDTO> orderList = orderByDate(false);

        if (limit <= 0) {
            limit = DefaultValues.LIMIT;
        }
        QueryDTO query = new QueryDTO(constraintList, orderList, limit);

        return query;

    }
    private List<OrderDTO> orderByDate(boolean ascDesc) {
        logger.log(Level.INFO, "Ordering by date");

        BetaIdentifierDTO timestamp = IdentifierDTOHelper.getBetaIdentifier("timestamp");

        OrderDTO order = new OrderDTO(timestamp, ascDesc);

        List<OrderDTO> orderList = new ArrayList<>();

        orderList.add(order);

        return orderList;
    }

}
