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

import ch.bfh.uniboard.UniBoardService;
import ch.bfh.uniboard.UniBoardService_Service;
import ch.bfh.uniboard.data.PostDTO;
import ch.bfh.uniboard.data.PostData;
import ch.bfh.uniboard.data.QueryDTO;
import ch.bfh.uniboard.data.ResultContainerDTO;
import ch.bfh.uniboard.exception.ServiceConnectionException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class UniBoardClient {

    private static final Logger logger = Logger.getLogger(UniBoardClient.class.getName());

    private static UniBoardService uniBoardService;

    public static List<PostData> getTop50MostRecentPosts() throws ServiceConnectionException{
        logger.info("UniBoardClient sending query to get the top 50 most recent posts");

        QueryBuilder builder = new QueryBuilder();
        QueryDTO query = builder.buildQuery();

        if(query!=null){
           List<PostDTO> posts = sendQuery(query);
           if(posts!=null && !posts.isEmpty()){
               return convertToPostData(posts);
           }
        }
        return null;
    }

    public static List<PostDTO> sendQuery(QueryDTO query) throws ServiceConnectionException{
        logger.info("UniBoardClient connecting to UniboardService web service provider");

        if (query == null) {
            logger.info("A null value was received where a QueryDTO object was expected!");
            return null;
        }
        ResultContainerDTO resultContainer;
        try {
            URL wsdlLocation = new URL("http://urd:5080/UniBoardService/UniBoardServiceImpl?wsdl");
            QName qname = new QName("http://uniboard.bfh.ch/", "UniBoardService");
            UniBoardService_Service mixingService = new UniBoardService_Service(wsdlLocation, qname);
            uniBoardService = mixingService.getUniBoardServicePort();

            resultContainer = uniBoardService.get(query);

            return resultContainer.getResult().getPost();

        } catch (MalformedURLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            return null;
        }
    }

    private static List<PostData> convertToPostData(List<PostDTO> posts) {
        List<PostData> postData = new ArrayList<>();
        for (PostDTO post : posts) {
            PostData postdata = new PostData(post);
            postData.add(postdata);
        }
        return postData;
    }
}