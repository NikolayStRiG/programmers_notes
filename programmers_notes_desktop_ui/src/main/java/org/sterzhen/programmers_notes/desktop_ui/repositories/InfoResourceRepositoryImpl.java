package org.sterzhen.programmers_notes.desktop_ui.repositories;

import org.springframework.stereotype.Repository;
import org.sterzhen.programmers_notes.core.damain.InfoResource;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;
import scala.Option;

@Repository
public class InfoResourceRepositoryImpl implements InfoResourceRepository {
    private int index = 0;
    @Override
    public Option<InfoResource> findById(long id) {
        index++;
        return Option.apply(new InfoResource(id, "" + index, "" + index, "" + index));
    }

    @Override
    public void insert(InfoResource resource) {

    }

    @Override
    public void update(InfoResource resource) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long nextEntityId() {
        return 0;
    }

    @Override
    public boolean existByName(String name) {
        return false;
    }

    @Override
    public boolean existByAddress(String address) {
        return false;
    }
}
