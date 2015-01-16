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

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
@Named
@SessionScoped
public class ValidatorBean implements Serializable{

    private String noSectionError;

    public String getNoSectionError() {
        return noSectionError;
    }

    public void setNoSectionError(String noSectionError) {
        this.noSectionError = noSectionError;
    }

    public boolean sectionSelected(){
        return false;
    }
    public static boolean validateTime(String time) {
        Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

}
