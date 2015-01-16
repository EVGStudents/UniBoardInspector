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
 public static String VOTERS = "Voters";
 public static String ELECTION_DATA = "ElectionData";
 public static String CANDIDATES = "Candidates";
 public static String BALLOTS="Ballots";
 public static String DECRYPTED_VOTES="DecryptedVotes";
 public static String ELECTION_RESULT="ElectionResult";

    public static List<String> getAllGroups() {
        List<String> groups = new ArrayList<>();
        groups.add(VOTERS);
        groups.add(ELECTION_DATA);
        groups.add(CANDIDATES);
        groups.add(BALLOTS);
        groups.add(DECRYPTED_VOTES);
        groups.add(ELECTION_RESULT);
        return groups;
    }
}
