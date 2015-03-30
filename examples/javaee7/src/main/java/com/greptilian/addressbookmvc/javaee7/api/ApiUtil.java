package com.greptilian.addressbookmvc.javaee7.api;

import com.greptilian.addressbookmvc.javaee7.Person;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ApiUtil {

    public static JsonObjectBuilder toJson(Person person) {
        JsonObjectBuilder personAsJson = Json.createObjectBuilder();
        long id = person.getId();
        String displayName = person.getDisplayName();
        personAsJson.add("id", id);
        personAsJson.add("displayName", displayName);
        return personAsJson;
    }

    public static String getDisplayName(JsonObject body) throws ApiException {
        String keyForDisplayName = "displayName";
        String displayName;
        try {
            displayName = body.getString(keyForDisplayName);
        } catch (NullPointerException ex) {
            throw new ApiException("Required field missing: " + keyForDisplayName);
        }
        return displayName;
    }

}
