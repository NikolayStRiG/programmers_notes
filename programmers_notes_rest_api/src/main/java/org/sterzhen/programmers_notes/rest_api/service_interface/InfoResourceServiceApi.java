package org.sterzhen.programmers_notes.rest_api.service_interface;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.sterzhen.programmers_notes.rest_api.core.Page;
import org.sterzhen.programmers_notes.rest_api.core.Pageable;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;

import javax.ws.rs.*;
import java.util.List;

/**
 * An interface that describes methods over an information resource
 */
@Path("api/info_resources")
public interface InfoResourceServiceApi {

    /**
     * Get information resource by id
     *
     * @param id information resource id
     * @return information resource or null
     */
    @GET
    @Path("{id}")
    @Produces(value={"application/json"})
    InfoResourceApi getById(@PathParam("id") Long id);

    /**
     * Get all information
     *
     * @return information resource list
     */
    @GET
    @Path("")
    @Produces(value={"application/json"})
    @JsonDeserialize(contentAs = InfoResourceApi.class)
    List<InfoResourceApi> getAll();

    /**
     * Return information resource page by pageable
     * @param pageable Pageable
     * @return Page<InfoResourceApi>
     */
    @GET
    @Path("/page")
    @Consumes(value={"application/json"})
    @Produces(value={"application/json"})
    Page<InfoResourceApi> getPage(Pageable pageable);

    /**
     * Create new information resource
     *
     * @param resource new information resource
     * @return information resource
     */
    @POST
    @Path("")
    @Consumes(value={"application/json"})
    @Produces(value={"application/json"})
    InfoResourceApi create(InfoResourceApi resource);

    /**
     * Update information resource
     *
     * @param resource information resource
     * @return information resource
     */
    @PUT
    @Path("")
    @Consumes(value={"application/json"})
    @Produces(value={"application/json"})
    InfoResourceApi update(InfoResourceApi resource);

    /**
     * Delete information resource by id
     *
     * @param id information resource id
     * @return delete information resource
     */
    @DELETE
    @Path("/{id}")
    @Produces(value={"application/json"})
    InfoResourceApi deleteById(@PathParam("id") Long id);
}
