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

import ch.bfh.uniboard.data.AlphaIdentifierDTO;
import ch.bfh.uniboard.data.BetaIdentifierDTO;
import ch.bfh.uniboard.data.ConstraintDTO;
import ch.bfh.uniboard.data.DateValueDTO;
import ch.bfh.uniboard.data.EqualDTO;
import ch.bfh.uniboard.data.GreaterDTO;
import ch.bfh.uniboard.data.GreaterEqualDTO;
import ch.bfh.uniboard.data.InDTO;
import ch.bfh.uniboard.data.IntegerValueDTO;
import ch.bfh.uniboard.data.LessDTO;
import ch.bfh.uniboard.data.LessEqualDTO;
import ch.bfh.uniboard.data.MessageIdentifierDTO;
import ch.bfh.uniboard.data.StringValueDTO;
import ch.bfh.uniboard.data.ValueDTO;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class ConstraintHandler {

    public static ConstraintDTO handleSectionConstraint(String section) {

        if (section != null && !section.isEmpty()) {
            AlphaIdentifierDTO alphaIdentifier = IdentifierDTOHelper.getAlphaIdentifier("section");
            ValueDTO value = new StringValueDTO(section);
            ConstraintDTO constraint = new EqualDTO(alphaIdentifier, value);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleGroupConstraint(String group) {

        if (group != null && !group.isEmpty()) {
            AlphaIdentifierDTO alphaIdentifier = IdentifierDTOHelper.getAlphaIdentifier("group");
            ValueDTO groupValue = new StringValueDTO(group);
            ConstraintDTO constraint = new EqualDTO(alphaIdentifier, groupValue);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleFromDateTimeConstraint(XMLGregorianCalendar fromDate) {

        if (fromDate != null) {
            BetaIdentifierDTO betaIdentifier = IdentifierDTOHelper.getBetaIdentifier("timestamp");
            ValueDTO fromDateValue = new DateValueDTO(fromDate);
            ConstraintDTO constraint = new GreaterEqualDTO(betaIdentifier, fromDateValue);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleToDateTimeConstraint(XMLGregorianCalendar toDate) {

        if (toDate != null) {
            BetaIdentifierDTO betaIdentifier = IdentifierDTOHelper.getBetaIdentifier("timestamp");
            ValueDTO toDateValue = new DateValueDTO(toDate);
            ConstraintDTO constraint = new LessEqualDTO(betaIdentifier, toDateValue);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleSectionConstraint(List<String> sections) {

        if (sections != null && !sections.isEmpty()) {
            List<ValueDTO> values = new ArrayList<>();
            for (String section : sections) {
                ValueDTO value = new StringValueDTO(section);
                values.add(value);
            }
            AlphaIdentifierDTO alphaIdentifier = IdentifierDTOHelper.getAlphaIdentifier("section");

            ConstraintDTO constraint = new InDTO(alphaIdentifier, values);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleGroupConstraint(List<String> groups) {

        if (groups != null && !groups.isEmpty()) {
            List<ValueDTO> values = new ArrayList<>();

            for (int i = 0; i < groups.size(); i++) {
                ValueDTO value = new StringValueDTO(groups.get(i));
                values.add(value);
            }
            AlphaIdentifierDTO alphaIdentifier = IdentifierDTOHelper.getAlphaIdentifier("group");

            ConstraintDTO constraint = new InDTO(alphaIdentifier, values);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleRankConstraintEqual(int rank) {
        if (rank > 0) {
            BetaIdentifierDTO betaIdentifier = IdentifierDTOHelper.getBetaIdentifier("rank");
            ValueDTO rankValue = new IntegerValueDTO(rank);
            ConstraintDTO constraint = new EqualDTO(betaIdentifier, rankValue);
            return constraint;
        }
        return null;
    }

    public static ConstraintDTO handleRankConstraintLessThan(int rank) {
        if (rank > 0 ) {
            BetaIdentifierDTO betaIdentifier = IdentifierDTOHelper.getBetaIdentifier("rank");
            ValueDTO rankValue = new IntegerValueDTO(rank);
            ConstraintDTO constraint = new LessDTO(betaIdentifier, rankValue);
            return constraint;
        }
        return null;
    }
     public static ConstraintDTO handleRankConstraintMoreThan(int rank) {
        if (rank > 0 ) {
            BetaIdentifierDTO betaIdentifier = IdentifierDTOHelper.getBetaIdentifier("rank");
            ValueDTO rankValue = new IntegerValueDTO(rank);
            ConstraintDTO constraint = new GreaterDTO(betaIdentifier, rankValue);
            return constraint;
        }
        return null;
    }

     public static ConstraintDTO handlePublicKeyConstraint(String publicKey){
         if (publicKey != null && !publicKey.isEmpty()) {
            AlphaIdentifierDTO alphaIdentifier = IdentifierDTOHelper.getAlphaIdentifier("key");
            ValueDTO value = new StringValueDTO(publicKey);
            ConstraintDTO constraint = new EqualDTO(alphaIdentifier, value);
            return constraint;
        }
        return null;
     }
    public static ConstraintDTO handleMessageConstraintMoreThan(String message) {

        MessageIdentifierDTO messageIdentifier = IdentifierDTOHelper.getMessageIdentifier(message);
        return null;
    }

}
