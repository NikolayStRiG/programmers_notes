package org.sterzhen.programmers_notes.rest_api.service_interface;

import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceDto;

/**
 * An interface that describes methods over an information resource
 */
public interface InfoResourceRestApi {

    /**
     * Get information resource by id
     *
     * @param id information resource id
     * @return information resource
     */
    InfoResourceDto getById(Long id);
}
