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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class Groups {
 public static String STUDENT = "student";
 public static String TEACHER = "teacher";
 public static String CLASSROOM = "classroom";

    public static List<String> getAllGroups() {

        List<String> groups = new ArrayList<String>();
        groups.add("teacher");
        groups.add("classroom");
        groups.add("student");
        return groups;
    }
}
