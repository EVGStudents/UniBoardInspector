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
import ch.bfh.uniboard.exception.MissingQueryParametersException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class UniBoardClient {

    @WebServiceRef(wsdlLocation
            = "http://urd:5080/UniBoardService/UniBoardServiceImpl?wsdl")
    private static UniBoardService uniBoardService;

//    public static void main(String args[]) {
//
//        getTop50MostRecentPosts();
//
//    }

    public static List<PostData> getTop50MostRecentPosts() {

        QueryBuilder builder = new QueryBuilder();

        try {
            QueryDTO query = builder.buildQuery("HESB", "student", null, null, 10);

            List<PostDTO> posts = sendQuery(query);

            printMessages(posts);
        } catch (MissingQueryParametersException exp) {

        }
        return null;

    }

    public static List<PostDTO> sendQuery(QueryDTO query) {
        ResultContainerDTO resultContainer = new ResultContainerDTO();
        try {
            URL wsdlLocation = new URL("http://urd:5080/UniBoardService/UniBoardServiceImpl?wsdl");
            QName qname = new QName("http://uniboard.bfh.ch/", "UniBoardService");
            UniBoardService_Service mixingService = new UniBoardService_Service(wsdlLocation, qname);
            uniBoardService = mixingService.getUniBoardServicePort();

            resultContainer = uniBoardService.get(query);

            return resultContainer.getResult().getPost();

        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public static void printMessages(List<PostDTO> posts) {

        int nbMessages = posts.size();

        for (int i = 0; i < nbMessages; i++) {
            byte[] message = posts.get(i).getMessage();
            System.out.println("Message" + (i + 1));
            for (int j = 0; j < message.length; j++) {
                System.out.print((char) message[j]);
            }
            PostData data = new PostData(posts.get(i));
            System.out.println();
            System.out.println("Message: " + data.getMessage());
            System.out.println("Date: " + data.getDate());
            System.out.println("Section: " + data.getSection());
            System.out.println("Group: " + data.getGroup());
            System.out.println();
        }

//        for(int i=0;i<posts.size();i++){
//            PostData data= posts.get(i);
//            System.out.println("Post"+(i+1)+data.getDate());
//             System.out.println("Post"+(i+1)+data.getMessage());
//
//        }
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

