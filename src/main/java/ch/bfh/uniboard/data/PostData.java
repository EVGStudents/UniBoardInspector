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
package ch.bfh.uniboard.data;

import ch.bfh.uniboard.data.AttributesDTO.AttributeDTO;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@XmlRootElement
public class PostData {

    private String message;
    private String section;
    private String group;
    private String signature;
    private String date;
    private int rank;
    private String boardSignature;

    public PostData(PostDTO post) {
        extractValues(post);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBoardSignature() {
        return boardSignature;
    }

    public void setBoardSignature(String boardSignature) {
        this.boardSignature = boardSignature;
    }

    private void extractValues(PostDTO post) {

        this.message = post.getMessage().toString();

        AttributesDTO alphaAttributes = post.getAlpha();
        List<AttributeDTO> attributeList = alphaAttributes.getAttribute();
        for (int i = 0; i < attributeList.size(); i++) {
            String key = attributeList.get(i).getKey();
            ValueDTO value = attributeList.get(i).getValue();
            if (key.equals(Keys.SECTION)) {
                this.section = ((StringValueDTO) value).getValue();
            }
            if (key.equals(Keys.GROUP)) {
                this.group = ((StringValueDTO) value).getValue();
            }
            if (key.equals(Keys.SIGNATURE)) {
                this.signature = ((StringValueDTO) value).getValue();
            }
        }

        AttributesDTO betaAttributes = post.getBeta();
        List<AttributeDTO> betaAttributeList = betaAttributes.getAttribute();
        for (int i = 0; i < betaAttributeList.size(); i++) {
            String key = betaAttributeList.get(i).getKey();
            ValueDTO value = betaAttributeList.get(i).getValue();
            if (key.equals(Keys.TIMESTAMP)) {
                XMLGregorianCalendar timestamp = ((DateValueDTO) value).getValue();
                this.date = timestamp.getYear()+"/"+timestamp.getMonth()+"/"+timestamp.getDay()+" "+
                        timestamp.getHour()+":"+timestamp.getMinute();
            }
            if (key.equals(Keys.RANK)) {
                this.rank = ((IntegerValueDTO) value).getValue();
            }
            if (key.equalsIgnoreCase(Keys.BOARD_SIGNATURE)) {
                this.boardSignature = ((StringValueDTO) value).getValue();
            }
        }
    }
}