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

import ch.bfh.uniboard.client.QueryBuilder;
import ch.bfh.uniboard.client.UniBoardClient;
import ch.bfh.uniboard.data.DefaultValues;
import ch.bfh.uniboard.data.PostDTO;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.data.QueryDTO;
import ch.bfh.uniboard.exception.MissingQueryParametersException;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@ManagedBean
@ApplicationScoped
public class BasicSearchBean {

    //StudentBean studentBean;
    private String section = "";

    private String group = "";

    private Date dateFrom;

    private Date dateTo;

    private int limit = DefaultValues.LIMIT;

    private List<PostData> postData = new ArrayList<>();

    private PostData selectedPost;

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

    public List<PostData> getPostData() {
        return postData;
    }

    public PostData getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(PostData selectedPost) {
        this.selectedPost = selectedPost;
    }
    public String inspect() {
//        List<Data> dataSet = StudentBean.dataSet;
//        if (!selectedSection.isEmpty()) {
//
//            for (int i = 0; i < dataSet.size(); i++) {
//                Data data = dataSet.get(i);
//                if (!data.getSection().equals(selectedSection)) {
//                    dataSet.remove(data);
//                }
//            }
//            System.out.println("here*******************************************************");
//            StudentBean.dataSet = dataSet;
//            StudentBean.newDataSet = true;
//            return "basicSearch";
//        }

        QueryBuilder builder = new QueryBuilder();
        try {
            XMLGregorianCalendar dateBegin=XMLGregorianCalendarImpl.createDateTime(dateFrom.getYear(), dateFrom.getMonth(), dateFrom.getDay(),
                    dateFrom.getHours(), dateFrom.getMinutes(), dateFrom.getSeconds());
            XMLGregorianCalendar dateEnd=XMLGregorianCalendarImpl.createDateTime(dateTo.getYear(), dateTo.getMonth(), dateTo.getDay(),
                    dateTo.getHours(), dateTo.getMinutes(), dateTo.getSeconds());
            QueryDTO query = builder.buildQuery(section, group, dateBegin, dateEnd, limit);
            List<PostDTO> posts = UniBoardClient.sendQuery(query);

            for (int i = 0; i < posts.size(); i++) {
                PostDTO post = posts.get(i);
                PostData data = new PostData(post);
                postData.add(data);
            }
            return "basicSearch";

        } catch (MissingQueryParametersException exception) {
            MessageFactory.error("ch.bfh.UniBoard.No_SECTION_FOUND");
        }
        return null;
    }

    public String viewPost(PostData post){
        this.selectedPost = post;
        return "postDetails";
    }

    @PostConstruct
    public void init(){
        postData = UniBoardClient.getTop50MostRecentPosts();
    }
}
