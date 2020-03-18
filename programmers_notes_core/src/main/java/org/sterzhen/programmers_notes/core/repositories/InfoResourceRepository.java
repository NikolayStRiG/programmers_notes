package org.sterzhen.programmers_notes.core.repositories;

import org.sterzhen.programmers_notes.core.domain.InfoResource;

import java.util.Optional;

/**
 * This interface is used to interact with the database
 */
public interface InfoResourceRepository {

    /**
     * Find InfoResource by id in DB
     *
     * @param id InfoResource id
     * @return Option[InfoResource]
     */
    Optional<InfoResource> findById(Long id);

    /**
     * Create new InfoResource
     *
     * @param resource new InfoResource
     */
    void insert(InfoResource resource);

    /**
     * Update InfoResource
     *
     * @param resource InfoResource
     */
    void update(InfoResource resource);

    /**
     * Delete InfoResource by id
     *
     * @param id InfoResource id
     */
    void delete(Long id);

    /**
     * Return next entity id for
     *
     * @return next id
     */
    Long nextEntityId();

    /**
     * Exist by name
     *
     * @param name InfoResource name
     * @return true if exists there is otherwise false
     */
    Boolean existByName(String name);

    /**
     * Exist by address
     *
     * @param address InfoResource name
     * @return true if exists there is otherwise false
     */
    Boolean existByAddress(String address);
}
