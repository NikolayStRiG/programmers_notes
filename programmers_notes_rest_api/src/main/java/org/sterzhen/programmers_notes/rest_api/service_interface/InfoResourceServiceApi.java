package org.sterzhen.programmers_notes.rest_api.service_interface;

import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * An interface that describes methods over an information resource
 */
@Path("api/info_resource")
public interface InfoResourceServiceApi {

    /**
     * Get information resource by id
     *
     * @param id information resource id
     * @return information resource or null
     */
    @GET
    @Path("{id}")
    InfoResourceApi getById(@PathParam("id") Long id);
}
