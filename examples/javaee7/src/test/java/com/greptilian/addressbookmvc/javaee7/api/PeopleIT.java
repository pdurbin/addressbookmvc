package com.greptilian.addressbookmvc.javaee7.api;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PeopleIT {

    private static final Logger logger = Logger.getLogger(PeopleIT.class.getCanonicalName());

    public PeopleIT() {
        String basePath = "/javaee7addressbook-1.0-SNAPSHOT/api/v1";
        String port = System.getProperty("port");
        if (port != null) {
            RestAssured.baseURI = "http://localhost:" + port + basePath;
        } else {
            int VagrantPort = 8888;
            RestAssured.baseURI = "http://localhost:" + VagrantPort + basePath;
        }
        logger.fine("Testing with base URL " + RestAssured.baseURI);
    }

    @Test
    public void test404() {
        expect().statusCode(404).when().get("/no-such-path");
    }

    @Test
    public void test200() {
        expect().statusCode(200).when().get("/people/list");
    }

    @Test
    public void testAddBadRequest() {
        String typoInDisplayNameKey = "dsplayName";
        JsonObject body = Json.createObjectBuilder().add(typoInDisplayNameKey, "Sloppy Joe").build();
        People instance = new People();
        javax.ws.rs.core.Response expResult = javax.ws.rs.core.Response.status(BAD_REQUEST).build();
        javax.ws.rs.core.Response result = instance.add(body);
        assertEquals(expResult.getStatus(), result.getStatus());
    }

    @Test
    public void addAndDelete() {
        String displayNameKey = "displayName";
        JsonObject personToAdd = Json.createObjectBuilder().add(displayNameKey, "Some Guy").build();
        Response addResponse = given().body(personToAdd.toString()).contentType(ContentType.JSON).post("/people");
        assertEquals(200, addResponse.getStatusCode());

        JsonPath personAdded = JsonPath.from(addResponse.body().asString());
        int id = personAdded.get("id");
        Response deleteResponse = given().delete("/people/" + id);
        assertEquals(200, deleteResponse.getStatusCode());
        logger.fine("added and deleted id " + id);
    }

    @Test
    public void addEditAndDelete() {
        String displayNameKey = "displayName";
        String initialDisplayName = "Michael Finnegan";
        JsonObject personToAdd = Json.createObjectBuilder().
                add(displayNameKey, initialDisplayName).build();
        Response addResponse = given()
                .body(personToAdd.toString()).contentType(ContentType.JSON)
                .post("/people");
        assertEquals(200, addResponse.getStatusCode());

        JsonPath personAdded = JsonPath.from(addResponse.body().asString());
        int id = personAdded.get("id");
        String savedInitialDisplayName = personAdded.get(displayNameKey);
        assertEquals(initialDisplayName, savedInitialDisplayName);

        String updatedDisplayName = "Mike Finnegan";
        JsonObject updatedInfo = Json.createObjectBuilder().
                add(displayNameKey, updatedDisplayName).build();
        Response editResponse = given()
                .body(updatedInfo.toString()).contentType(ContentType.JSON)
                .put("/people/" + id);
        assertEquals(200, editResponse.getStatusCode());

        JsonPath personUpdated = JsonPath.from(editResponse.body().asString());
        String savedUpdatedDisplayName = personUpdated.get(displayNameKey);
        assertEquals(updatedDisplayName, savedUpdatedDisplayName);

        Response deleteResponse = given().delete("/people/" + id);
        assertEquals(200, deleteResponse.getStatusCode());
        logger.fine("added, edited, and deleted id " + id);
    }

}
