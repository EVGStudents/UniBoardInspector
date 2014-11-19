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
import ch.bfh.uniboard.exception.MissingQueryParametersException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class QueryBuilder implements IQueryBuilder {

    private static final Logger logger = Logger.getLogger(QueryBuilder.class.getName());

    public QueryDTO buildQuery() {

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

    @Override
    public QueryDTO buildQuery(String section, String group, XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate, int limit) throws MissingQueryParametersException {
        logger.info("Build Query");
        if (section == null || section.isEmpty()) {
            throw new MissingQueryParametersException();
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

        if(limit<=0){
            limit=DefaultValues.LIMIT;
        }

        QueryDTO query = new QueryDTO(constraintList, orderList, limit);

        return query;
    }

    private List<OrderDTO> orderByDate(boolean ascDesc) {

        BetaIdentifierDTO timestamp = IdentifierDTOHelper.getBetaIdentifier("timestamp");

        OrderDTO order = new OrderDTO(timestamp, ascDesc);

        List<OrderDTO> orderList = new ArrayList<>();
        
        orderList.add(order);

        return orderList;
    }

}
