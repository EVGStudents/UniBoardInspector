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

import ch.bfh.uniboard.data.Groups;
import ch.bfh.uniboard.data.Sections;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@ManagedBean
@ApplicationScoped
public class PopulateBean {

    private List<String> sections = new ArrayList<String>();

    private List<String> groups = new ArrayList<String>();

    @PostConstruct
    public void init() {
        sections = Sections.getAllSections();
        groups = Groups.getAllGroups();
    }

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

}
