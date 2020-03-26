package org.sterzhen.programmers_notes.rest_service.repositories;

import org.springframework.stereotype.Repository;
import org.sterzhen.programmers_notes.core.domain.InfoResource;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InfoResourceRepositoryImpl implements InfoResourceRepository {

    private final Map<Long, InfoResource> resourceMap = new HashMap<>();

    private final AtomicLong idGenerate = new AtomicLong(0);

    public InfoResourceRepositoryImpl() {
        resourceMap.put(1L, InfoResource.of(1L, "Test", "http://localhost:8080/test", "Description"));
    }

    @Override
    public Optional<InfoResource> findById(Long id) {
        if (resourceMap.containsKey(id)) {
            return Optional.ofNullable(resourceMap.get(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void insert(InfoResource resource) {
        if (resourceMap.containsKey(resource.getId())) {
            throw new IllegalArgumentException("Unique constraint by id");
        }
        resourceMap.put(resource.getId(), resource);
    }

    @Override
    public void update(InfoResource resource) {
        if (!resourceMap.containsKey(resource.getId())) {
            throw new IllegalArgumentException("Id not found");
        }
        resourceMap.put(resource.getId(), resource);
    }

    @Override
    public void delete(Long id) {
        resourceMap.remove(id);
    }

    @Override
    public Long nextEntityId() {
        return idGenerate.incrementAndGet();
    }

    @Override
    public boolean existByName(String name) {
        return resourceMap.values().stream().anyMatch(r -> r.getName().equals(name));
    }

    @Override
    public boolean existByAddress(String address) {
        return resourceMap.values().stream().anyMatch(r -> r.getAddress().equals(address));
    }
}
