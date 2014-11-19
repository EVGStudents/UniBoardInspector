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

import ch.bfh.uniboard.data.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class StudentBean implements Serializable {

    private boolean sectionSelected=false;

    private boolean groupSelected=false;

    private List<Data> dataSet;

    private Data selectedPost;

    public List<Data> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Data> dataSet) {
        this.dataSet = dataSet;
    }

    public Data getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(Data selectedPost) {
        this.selectedPost = selectedPost;
    }



    @PostConstruct
    public void init() {
        dataSet = new ArrayList<Data>();
        Data data1 = new Data("EPFL", "Student", "29-09-2014 14:05", "affcb1");
        Data data2 = new Data("HESB", "Student", "23-09-2014 13:05", "bianp2");
        Data data3 = new Data("EPFL", "Student", "12-09-2014 09:05", "bergd5");
        Data data4 = new Data("HESB", "Classroom", "10-08-2014 08:59", "300");
        Data data5 = new Data("EPFL", "Classroom", "05-08-2014 17:05", "B703");
        Data data6 = new Data("HESB", "Classroom", "11-07-2014 06:51", "CM321");
        Data data7 = new Data("HESB", "Classrom", "03-07-2014 18:23", "ME500");
        Data data8 = new Data("HESB", "Teacher", "01-07-2014 13:05", "affcb1");
        Data data9 = new Data("ETHZ", "Teacher", "11-06-2014 10:47", "schoro3");
        Data data10 = new Data("ETHZ", "Teacher", "11-06-2014 08:47", "martkel5");


        dataSet.add(data1);
        dataSet.add(data2);
        dataSet.add(data3);
        dataSet.add(data4);
        dataSet.add(data5);
        dataSet.add(data6);
        dataSet.add(data7);
        dataSet.add(data8);
        dataSet.add(data9);
        dataSet.add(data10);

        dataSet.add(data1);
        dataSet.add(data2);
        dataSet.add(data3);
        dataSet.add(data4);
        dataSet.add(data5);
        dataSet.add(data6);
        dataSet.add(data7);
        dataSet.add(data8);
        dataSet.add(data9);
        dataSet.add(data10);

        dataSet.add(data1);
        dataSet.add(data2);
        dataSet.add(data3);
        dataSet.add(data4);
        dataSet.add(data5);
        dataSet.add(data6);
        dataSet.add(data7);
        dataSet.add(data8);
        dataSet.add(data9);
        dataSet.add(data10);
    }

    public String viewPost(Data data){
        selectedPost= data;
        return "viewPost";
    }
}
