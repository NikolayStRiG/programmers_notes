package org.sterzhen.programmers_notes.rest_service.repositories;

import org.springframework.stereotype.Repository;
import org.sterzhen.programmers_notes.core.damain.InfoResource;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;
import scala.Option;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InfoResourceRepositoryImpl implements InfoResourceRepository {

    private final Map<Long, InfoResource> resourceMap = new HashMap<>();

    private final AtomicLong idGenerate = new AtomicLong(0);

    @Override
    public Option<InfoResource> findById(long id) {
        if (resourceMap.containsKey(id)) {
            return Option.apply(resourceMap.get(id));
        } else {
            return Option.empty();
        }
    }

    @Override
    public void insert(InfoResource resource) {
        if (resourceMap.containsKey(resource.id())) {
            throw new IllegalArgumentException("Unique constraint by id");
        }
        resourceMap.put(resource.id(), resource);
    }

    @Override
    public void update(InfoResource resource) {
        if (!resourceMap.containsKey(resource.id())) {
            throw new IllegalArgumentException("Id not found");
        }
        resourceMap.put(resource.id(), resource);
    }

    @Override
    public void delete(long id) {
        resourceMap.remove(id);
    }

    @Override
    public long nextEntityId() {
        return idGenerate.incrementAndGet();
    }

    @Override
    public boolean existByName(String name) {
        return resourceMap.values().stream().anyMatch(r -> r.name().equals(name));
    }

    @Override
    public boolean existByAddress(String address) {
        return resourceMap.values().stream().anyMatch(r -> r.address().equals(address));
    }
}
