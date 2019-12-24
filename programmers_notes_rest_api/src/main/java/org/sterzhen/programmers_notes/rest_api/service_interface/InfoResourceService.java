package org.sterzhen.programmers_notes.rest_api.service_interface;

import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceDto;

public interface InfoResourceService {

    InfoResourceDto getById(Long id);
}
