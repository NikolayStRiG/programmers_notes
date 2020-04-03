package org.sterzhen.rest.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.ws.rs.*;
import java.util.List;

@Path("api/info_resources")
public interface TestInterface {

    @GET
    @Path("test")
    @Produces(value={"application/json"})
    TestReturnType getTest();

    @GET
    @Path("{id}")
    @Produces(value={"application/json"})
    TestReturnType getById(@PathParam("id") Long id);

    @GET
    @Path("{id}/field/{name}")
    @Produces(value={"application/json"})
    TestReturnType getById(@PathParam("id") Long id, @PathParam("name") String name);

    @POST
    @Path("")
    @Consumes(value={"application/json"})
    @Produces(value={"application/json"})
    TestReturnType create(TestReturnType resource);

    @PUT
    @Path("/{id}")
    @Consumes(value={"application/json"})
    @Produces(value={"application/json"})
    TestReturnType update(TestReturnType resource, @PathParam("id") Long id);

    @GET
    @Path("")
    @Produces(value={"application/json"})
    @JsonDeserialize(contentAs = TestReturnType.class)
    List<TestReturnType> getAll();

    @DELETE
    @Path("/{id}")
    @Produces(value={"application/json"})
    TestReturnType deleteById(@PathParam("id") Long id);
}
