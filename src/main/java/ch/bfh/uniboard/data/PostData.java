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

import ch.bfh.uniboard.client.MessageHandler;
import ch.bfh.uniboard.data.AttributesDTO.AttributeDTO;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;
import org.primefaces.json.JSONException;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@XmlRootElement
public class PostData implements Serializable{
    private static final Logger logger = Logger.getLogger(PostData.class.getName());

    private String message;
    private String section;
    private String group;
    private String signature;
    private String publicKey;
    private String date;
    private int rank;
    private String boardSignature;
    private Map<String, Object> messagePayload;
    private List<String> messageKeys;
    private int messageSize;

    public PostData(PostDTO post) {
        extractValues(post);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
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

    public Map<String, Object> getMessagePayload() {
        return messagePayload;
    }

    public List<String> getMessageKeys() {
        return new ArrayList<>(messagePayload.keySet());
    }

    public void setMessageKeys(List<String> messageKeys) {
        this.messageKeys = messageKeys;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    public void setMessagePayload(Map<String, Object> messagePayload) {
        this.messagePayload = messagePayload;
    }

    public String getParameterValue(String parameterName) {
        Object parameterValue = messagePayload.get(parameterName);
        if (parameterValue != null) {
            return messagePayload.get(parameterName).toString();
        }
        return "";
    }

    private void extractValues(PostDTO post) {
        logger.log(Level.INFO, "Extracting values from PostDTO");
        if (post != null) {
            this.message = digestMessage(post.getMessage());
            AttributesDTO alphaAttributes = post.getAlpha();
            List<AttributeDTO> alphaAttributeList = alphaAttributes.getAttribute();
            for (AttributeDTO alphaAttribute : alphaAttributeList) {
                String key = alphaAttribute.getKey();
                ValueDTO value = alphaAttribute.getValue();
                if (key.equals(Keys.SECTION)) {
                    this.section = ((StringValueDTO) value).getValue();
                }
                if (key.equals(Keys.GROUP)) {
                    this.group = ((StringValueDTO) value).getValue();
                }
                if (key.equals(Keys.SIGNATURE)) {
                    this.signature = ((StringValueDTO) value).getValue();
                }
                if (key.equals(Keys.PUBLIC_KEY)) {
                    this.publicKey = ((StringValueDTO) value).getValue();
                }
            }

            AttributesDTO betaAttributes = post.getBeta();
            List<AttributeDTO> betaAttributeList = betaAttributes.getAttribute();
            for (AttributeDTO betaAttribute : betaAttributeList) {
                String key = betaAttribute.getKey();
                ValueDTO value = betaAttribute.getValue();
                if (key.equals(Keys.TIMESTAMP)) {
                    XMLGregorianCalendar timestamp = ((DateValueDTO) value).getValue();
                    this.date = formatDate(timestamp);
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

    private String digestMessage(byte[] message){
        logger.log(Level.INFO, "Digesting message of PostDTO");
        try {
            this.messagePayload = MessageHandler.getParametersFromPayload(message);
            this.messageKeys = new ArrayList<>(messagePayload.keySet());
            this.messageSize = messageKeys.size();
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(message);
            byte[] msg=md.digest(message);
            return msg.toString();
        } catch (JSONException | NoSuchAlgorithmException exception) {
           logger.log(Level.WARNING, exception.getMessage());
           return null;
        }
    }

    private String formatDate(XMLGregorianCalendar timestamp) {
        logger.log(Level.INFO, "Formatting date");

        int day = timestamp.getDay();
        String d = "" + day;
        if (day < 10) {
            d = "0" + day;
        }
        int month = timestamp.getMonth();
        String m = "" + month;
        if (month < 10) {
            m = "0" + month;
        }
        int hour = timestamp.getHour();
        String h = "" + hour;
        if (hour < 10) {
            h = "0" + hour;
        }
        int minute = timestamp.getMinute();
        String min = "" + minute;
        if (minute < 10) {
            min = "0" + minute;
        }
        return d + "/" + m + "/" + timestamp.getYear() + "   "
                + h + ":" + min;
    }
}