package org.sterzhen.programmers_notes.core.repositories;

import org.sterzhen.programmers_notes.core.domain.InfoResource;

import java.util.List;
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
    InfoResource insert(InfoResource resource);

    /**
     * Update InfoResource
     *
     * @param resource InfoResource
     */
    InfoResource update(InfoResource resource);

    /**
     * Delete InfoResource by id
     *
     * @param id InfoResource id
     */
    void delete(Long id);

    /**
     * Exist by name
     *
     * @param name InfoResource name
     * @return true if exists there is otherwise false
     */
    boolean existsByName(String name);

    /**
     * Exist by address
     *
     * @param address InfoResource name
     * @return true if exists there is otherwise false
     */
    boolean existsByAddress(String address);

    /**
     * Find all InfoResource
     *
     * @return List<InfoResource>
     */
    List<InfoResource> findAll();
}
