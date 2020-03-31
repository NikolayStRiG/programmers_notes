package org.sterzhen.programmers_notes.rest_service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sterzhen.programmers_notes.core.domain.InfoResource;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;
import org.sterzhen.programmers_notes.rest_service.dao.entities.InfoResourceEntity;
import org.sterzhen.programmers_notes.rest_service.dao.repositories.InfoResourceEntityRepository;

import java.util.Optional;

@Repository
public class InfoResourceDAO implements InfoResourceRepository {

    @Autowired
    private InfoResourceEntityRepository infoResourceEntityRepository;

    private InfoResourceEntity toEntity(InfoResource resource) {
        if (resource == null) {
            return null;
        } else {
            return InfoResourceEntity.of(resource.getId(), resource.getName(), resource.getAddress(), resource.getDescription());
        }
    }

    private InfoResource toRecourse(InfoResourceEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return InfoResource.of(entity.getId(), entity.getName(), entity.getAddress(), entity.getDescription());
        }
    }

    @Override
    public Optional<InfoResource> findById(Long id) {
        return infoResourceEntityRepository.findById(id).map(this::toRecourse);
    }

    @Override
    public void insert(InfoResource resource) {
        if (infoResourceEntityRepository.existsById(resource.getId())) {
            throw new IllegalArgumentException("Unique constraint by id");
        }
        infoResourceEntityRepository.save(toEntity(resource));
    }

    @Override
    public void update(InfoResource resource) {
        if (!infoResourceEntityRepository.existsById(resource.getId())) {
            throw new IllegalArgumentException("Id not found");
        }
        infoResourceEntityRepository.save(toEntity(resource));
    }

    @Override
    public void delete(Long id) {
        infoResourceEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return infoResourceEntityRepository.existsByName(name);
    }

    @Override
    public boolean existsByAddress(String address) {
        return infoResourceEntityRepository.existsByAddress(address);
    }
}
