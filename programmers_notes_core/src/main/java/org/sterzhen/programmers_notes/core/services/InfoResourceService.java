package org.sterzhen.programmers_notes.core.services;

import org.sterzhen.programmers_notes.core.domain.InfoResource;

import java.util.Optional;

/**
 * The interface performs service operations with InfoResource
 */
public interface InfoResourceService {

    /**
     * Find InfoResource by id
     *
     * @param id InfoResource id
     * @return Option[InfoResource]
     */
    Optional<InfoResource> findById(Long id);

    /**
     * Create new InfoResource
     *
     * @param name        InfoResource name
     * @param address     InfoResource address
     * @param description InfoResource description
     */
    InfoResource create(String name, String address, String description);

    /**
     * Update InfoResource
     *
     * @param resource InfoResource
     */
    InfoResource update(InfoResource resource);

    /**
     * Delete InfoResource
     *
     * @param resource InfoResource
     */
    InfoResource delete(InfoResource resource);

    /**
     * Delete InfoResource by id
     *
     * @param id InfoResource id
     */
    InfoResource delete(Long id);
}
