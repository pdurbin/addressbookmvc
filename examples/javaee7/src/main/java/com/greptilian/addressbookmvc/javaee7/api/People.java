package com.greptilian.addressbookmvc.javaee7.api;

import com.greptilian.addressbookmvc.javaee7.AddressBookServiceBean;
import com.greptilian.addressbookmvc.javaee7.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("v1/people")
public class People {

    @EJB
    AddressBookServiceBean addressBookService;

    @GET
    @Path("list")
    public Response list() {
        JsonArrayBuilder peopleBuilder = Json.createArrayBuilder();
        List<Person> allPeople = addressBookService.findAll();
        for (Person person : allPeople) {
            JsonObjectBuilder personAsJson = toJson(person);
            peopleBuilder.add(personAsJson);
        }
        JsonObjectBuilder response = Json.createObjectBuilder();
        response.add("people", peopleBuilder);
        return Response.ok().entity(Json.createObjectBuilder().add("message", response).build()).type(MediaType.APPLICATION_JSON).build();
    }

    private JsonObjectBuilder toJson(Person person) {
        JsonObjectBuilder personAsJson = Json.createObjectBuilder();
        long id = person.getId();
        String displayName = person.getDisplayName();
        personAsJson.add("id", id);
        personAsJson.add("displayName", displayName);
        return personAsJson;
    }

    @POST
    public Response add(JsonObject body) {
        String keyForDisplayName = "displayName";
        String displayName;
        try {
            displayName = body.getString(keyForDisplayName);
        } catch (NullPointerException ex) {
            return Response.status(BAD_REQUEST).entity(Json.createObjectBuilder().add("message", "Required field missing: " + keyForDisplayName).build()).type(MediaType.APPLICATION_JSON).build();
        }
        Person toPersist = new Person();
        toPersist.setDisplayName(displayName);
        Person persistedPerson = addressBookService.add(toPersist);
        return Response.ok().entity(Json.createObjectBuilder().add("message", "Added: " + persistedPerson.getId()).build()).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long doomedId) {
        Person doomed = addressBookService.find(doomedId);
        if (doomed == null) {
            return Response.status(NOT_FOUND).entity(Json.createObjectBuilder().add("message", "Could not find id: " + doomedId).build()).type(MediaType.APPLICATION_JSON).build();
        }
        boolean wasDeleted = addressBookService.delete(doomedId);
        if (wasDeleted) {
            return Response.ok().entity(Json.createObjectBuilder().add("message", "Deleted: " + doomedId).build()).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(INTERNAL_SERVER_ERROR).entity(Json.createObjectBuilder().add("message", "Problem deleting id: " + doomedId).build()).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
