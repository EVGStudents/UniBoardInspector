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

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
import javax.faces.bean.ManagedBean;
import org.primefaces.event.ToggleEvent;

@ManagedBean
public class FieldsetView {

    public void handleToggle(ToggleEvent event) {
        //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled", "Visibility:" + event.getVisibility());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

