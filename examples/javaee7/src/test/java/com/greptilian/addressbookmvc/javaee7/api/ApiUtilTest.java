package com.greptilian.addressbookmvc.javaee7.api;

import com.greptilian.addressbookmvc.javaee7.Person;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ApiUtilTest {

    @Test
    public void testToJson() {
        Person person = new Person();
        person.setId(1l);
        String numberOne = "William Riker";
        person.setDisplayName(numberOne);
        JsonObjectBuilder expResult = Json.createObjectBuilder().add("id", 1l).add("displayName", numberOne);
        JsonObjectBuilder result = ApiUtil.toJson(person);
        assertEquals(expResult.build(), result.build());
    }

    @Test
    public void testGetDisplayName() {
        JsonObject body = Json.createObjectBuilder().add("id", 1l).build();
        try {
            ApiUtil.getDisplayName(body);
        } catch (ApiException ex) {
            assertEquals(ex.getLocalizedMessage(), "Required field missing: displayName");
        }
    }

    @Test
    public void testGetPhoneNumberMissing() {
        JsonObject body = Json.createObjectBuilder().add("id", 1l).build();
        String phoneNumber = ApiUtil.getPhoneNumber(body);
        assertEquals(phoneNumber, null);
    }

    @Test
    public void testGetPhoneNumberKey() {
        Person person = new Person();
        person.setId(1l);
        String name = "Chatty";
        person.setDisplayName(name);
        String phone = "555-5555";
        person.setPhoneNumber(phone);
        JsonObjectBuilder expResult = Json.createObjectBuilder()
                .add("id", 1l)
                .add("displayName", name)
                .add("phoneNumber", phone);
        JsonObjectBuilder result = ApiUtil.toJson(person);
        assertEquals(expResult.build(), result.build());
    }

}
