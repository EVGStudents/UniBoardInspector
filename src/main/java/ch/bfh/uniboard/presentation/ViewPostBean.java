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

import ch.bfh.uniboard.data.Keys;
import ch.bfh.uniboard.data.PostData;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@ManagedBean
@ApplicationScoped
public class ViewPostBean {

    private String section;

    private String group;

    private int rank;

    private String timeStamp;

    private String message;

    private String signature;

    private String publicKey;

    private String boardSignature;

    public String getSection() {
        return section;
    }

    public String getGroup() {
        return group;
    }

    public int getRank() {
        return rank;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSignature() {
        return signature;
    }

    public String getBoardSignature() {
        return boardSignature;
    }

    public String getMessage() {
        return message;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public void setBoardSignature(String boardSignature) {
        this.boardSignature = boardSignature;
    }

    public void postDetails(PostData post) {
        section = post.getSection();
        group = post.getGroup();
        timeStamp = post.getDate();
        message = getMessageContents(post);
        signature = post.getSignature();
        publicKey = post.getPublicKey();
        boardSignature = post.getBoardSignature();

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('viewPost').show();");

    }
    private String getMessageContents(PostData post){
        String messageContents="";
        Map<String, Object> map = post.getMessagePayload();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if(!key.equals(Keys.MESSAGE_ID)){
            Object value = entry.getValue();
            messageContents = messageContents+key+": "+value+" <br />";
            }
        }
        return messageContents;
    }
}
