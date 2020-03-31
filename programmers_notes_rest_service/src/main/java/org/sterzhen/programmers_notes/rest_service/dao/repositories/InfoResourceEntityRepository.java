package org.sterzhen.programmers_notes.rest_service.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sterzhen.programmers_notes.rest_service.dao.entities.InfoResourceEntity;

@Repository
public interface InfoResourceEntityRepository extends CrudRepository<InfoResourceEntity, Long> {

    /**
     * Exists by name
     *
     * @param name name
     * @return true if exists
     */
    boolean existsByName(String name);

    /**
     * Exists by address
     *
     * @param address address
     * @return true if exists
     */
    boolean existsByAddress(String address);
}
