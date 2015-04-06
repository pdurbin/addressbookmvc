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
import javax.ws.rs.PUT;
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
            JsonObjectBuilder personAsJson = ApiUtil.toJson(person);
            peopleBuilder.add(personAsJson);
        }
        JsonObjectBuilder response = Json.createObjectBuilder();
        response.add("people", peopleBuilder);
        return Response.ok().entity(Json.createObjectBuilder().add("message", response).build()).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response add(JsonObject body) {
        String displayName;
        try {
            displayName = ApiUtil.getDisplayName(body);
        } catch (ApiException ex) {
            return Response.status(BAD_REQUEST).entity(Json.createObjectBuilder().add("message", ex.getLocalizedMessage()).build()).type(MediaType.APPLICATION_JSON).build();
        }
        Person toPersist = new Person();
        toPersist.setDisplayName(displayName);
        String phoneNumber = ApiUtil.getPhoneNumber(body);
        if (phoneNumber != null) {
            toPersist.setPhoneNumber(phoneNumber);
        }
        Person persistedPerson = addressBookService.add(toPersist);
//        return Response.ok().entity(Json.createObjectBuilder().add("id", persistedPerson.getId()).add("displayName", persistedPerson.getDisplayName()).build()).type(MediaType.APPLICATION_JSON).build();
        return Response.ok().entity(ApiUtil.toJson(persistedPerson).build()).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") long idToEdit, JsonObject body) {
        Person personToEdit = addressBookService.find(idToEdit);
        if (personToEdit == null) {
            return Response.status(BAD_REQUEST).entity(Json.createObjectBuilder().add("message", "Could not find id " + idToEdit).build()).type(MediaType.APPLICATION_JSON).build();
        }
        blankOutSinceToPutIsToReplace(personToEdit);
        String displayName;
        try {
            displayName = ApiUtil.getDisplayName(body);
        } catch (ApiException ex) {
            return Response.status(BAD_REQUEST).entity(Json.createObjectBuilder().add("message", ex.getLocalizedMessage()).build()).type(MediaType.APPLICATION_JSON).build();
        }
        personToEdit.setDisplayName(displayName);
        Person saved = addressBookService.save(personToEdit);
        return Response.ok().entity(ApiUtil.toJson(saved).build()).type(MediaType.APPLICATION_JSON).build();
    }

    private void blankOutSinceToPutIsToReplace(Person personToEdit) {
        personToEdit.setDisplayName(null);
        personToEdit.setPhoneNumber(null);
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
