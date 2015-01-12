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

    public static List<PostData> getTop50MostRecentPosts() {
        logger.info("UniBoardClient sending query to get the top 50 most recent posts");

        QueryBuilder builder = new QueryBuilder();
        QueryDTO query = builder.buildQuery();
        System.out.println("Query: "+query.toString());
        if(query!=null){
           List<PostDTO> posts = sendQuery(query);
           return convertToPostData(posts);
        }
        return null;
    }

    public static List<PostDTO> sendQuery(QueryDTO query) {
        logger.info("UniBoardClient connecting to UniboardService web service provider");
      if(query==null){
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

//    public static void printMessages(List<PostDTO> posts) {
//
//        int nbMessages = posts.size();
//        List<Character> messageChar = new ArrayList<Character>();
//        for (int i = 0; i < nbMessages; i++) {
//            byte[] message = posts.get(i).getMessage();
//            System.out.println("Message" + (i + 1));
//            for (int j = 0; j < message.length; j++) {
//                System.out.print((char) message[j]);
//                messageChar.add((char) message[j]);
//            }
//            PostData data = new PostData(posts.get(i));
//            System.out.println();
//            System.out.println("Message: " + data.getMessage());
//            System.out.println("Date: " + data.getDate());
//            System.out.println("Section: " + data.getSection());
//            System.out.println("Group: " + data.getGroup());
//            System.out.println();
//        }
//    }
//
    private static List<PostData> convertToPostData(List<PostDTO> posts) {
        List<PostData> postData = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            PostData post = new PostData(posts.get(i));
            postData.add(post);
        }
        return postData;
    }
}

  //  public static void test()
//{
//    String endpointUrl="http://urd:5080/UniBoardService/UniBoardServiceImpl";
//    UniBoardService board;
//        try {
//            URL wsdlLocation = new URL(endpointUrl);
//            QName qname = new QName("http://uniboard.bfh.ch/", "UniBoardService");
//            UniBoardService_Service mixingService = new UniBoardService_Service(wsdlLocation, qname);
//            board = mixingService.getUniBoardServicePort();
//            BindingProvider bp = (BindingProvider) board;
//            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
//        } catch (Exception ex) {
//            //logger.log(Level.SEVERE, "Unable to connect to UniBoard  service: {0}, exception: {1}", new
////Object[]{endpointUrl, ex});
//           // throw new CertificateCreationException("230 Unable to connect to UniBoard to publish certificate");
//        }
//}

