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

import ch.bfh.uniboard.data.AlphaIdentifierDTO;
import ch.bfh.uniboard.data.BetaIdentifierDTO;
import ch.bfh.uniboard.data.MessageIdentifierDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class IdentifierDTOHelper {

    public static AlphaIdentifierDTO getAlphaIdentifier(String value) {
        List<String> part = new ArrayList<>();
        part.add(value);
        return new AlphaIdentifierDTO(part);
    }

    public static BetaIdentifierDTO getBetaIdentifier(String value) {
        List<String> part = new ArrayList<>();
        part.add(value);
        return new BetaIdentifierDTO(part);
    }

    public static MessageIdentifierDTO getMessageIdentifier(String value) {
        List<String> part = new ArrayList<>();
        part.add(value);
        return new MessageIdentifierDTO(part);
    }

}
