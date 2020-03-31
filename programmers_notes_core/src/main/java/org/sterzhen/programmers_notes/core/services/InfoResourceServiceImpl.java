package org.sterzhen.programmers_notes.core.services;

import org.sterzhen.programmers_notes.core.domain.InfoResource;
import org.sterzhen.programmers_notes.core.exceptions.EntityNotFoundException;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;

import java.util.Optional;

/**
 * Implementation InfoResourceService
 */
public class InfoResourceServiceImpl implements InfoResourceService {

    private final InfoResourceRepository infoResRepository;

    public InfoResourceServiceImpl(InfoResourceRepository infoResRepository) {
        this.infoResRepository = infoResRepository;
    }

    /**
     * Find InfoResource by id
     *
     * @param id InfoResource id
     * @return Option[InfoResource]
     */
    @Override
    public Optional<InfoResource> findById(Long id) {
        return infoResRepository.findById(id);
    }

    /**
     * Create new InfoResource
     *
     * @param name        InfoResource name
     * @param address     InfoResource address
     * @param description InfoResource description
     */
    @Override
    public InfoResource create(String name, String address, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("A name is empty");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("A address is empty");
        }
        if (infoResRepository.existsByName(name)) {
            throw new IllegalArgumentException("An entity with that name already exists");
        }
        if (infoResRepository.existsByAddress(address)) {
            throw new IllegalArgumentException("An entity with that address already exists");
        }
        final var resource = InfoResource.of(name, address, description);
        infoResRepository.insert(resource);
        return resource;
    }

    /**
     * Update InfoResource
     *
     * @param resource InfoResource
     */
    @Override
    public InfoResource update(InfoResource resource) {
        var oldOp = findById(resource.getId());
        if (oldOp.isEmpty()) {
            throw new EntityNotFoundException("Entity not found");
        }
        var old = oldOp.get();
        if (!old.getName().equals(resource.getName()) && infoResRepository.existsByName(resource.getName())) {
            throw new IllegalArgumentException("An entity with that name already exists");
        }
        if (!old.getAddress().equals(resource.getAddress()) && infoResRepository.existsByAddress(resource.getAddress())) {
            throw new IllegalArgumentException("An entity with that address already exists");
        }
        infoResRepository.update(resource);
        return resource;
    }

    /**
     * Delete InfoResource
     *
     * @param resource InfoResource
     */
    @Override
    public InfoResource delete(InfoResource resource) {
        return delete(resource.getId());
    }

    /**
     * Delete InfoResource by id
     *
     * @param id InfoResource id
     */
    @Override
    public InfoResource delete(Long id) {
        var old = infoResRepository.findById(id);
        if (old.isEmpty()) {
            throw new EntityNotFoundException("Entity not found");
        }
        infoResRepository.delete(id);
        return old.get();
    }
}
