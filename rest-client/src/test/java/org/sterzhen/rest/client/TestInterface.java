package org.sterzhen.rest.client;

import javax.ws.rs.*;

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
}
