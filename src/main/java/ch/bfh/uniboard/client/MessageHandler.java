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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Priya Bianchetti &lt;bianp2@bfh.ch&gt;
 */
public class MessageHandler {

    public static Map<String, Object> getParametersFromPayload(byte[] message) throws JSONException {

        String jsonMessage = new String(message);
        JSONObject jsonObject = new JSONObject(jsonMessage);
        return jsonToMap(jsonObject);
    }

    public static Map jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

}
