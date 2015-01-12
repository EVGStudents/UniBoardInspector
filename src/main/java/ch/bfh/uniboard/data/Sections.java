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
public class Sections {

    public static String HESB = "BFH";
    public static String UZH = "UZH";
    public static String UNIBE = "UNIBE";

    public static List<String> getAllSections() {

        List<String> sections = new ArrayList<>();
        sections.add(HESB);
        sections.add(UZH);
        sections.add(UNIBE);
        return sections;
    }
}
