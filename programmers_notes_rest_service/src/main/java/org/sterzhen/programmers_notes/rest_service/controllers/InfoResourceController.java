package org.sterzhen.programmers_notes.rest_service.controllers;

import org.springframework.web.bind.annotation.*;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceDto;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceService;

@CrossOrigin
@RestController
@RequestMapping("api/info_resource")
public class InfoResourceController implements InfoResourceService {

    @GetMapping("{id}")
    @Override
    public InfoResourceDto getById(@PathVariable Long id) {
        InfoResourceDto dto = new InfoResourceDto();
        dto.setId(id);
        return dto;
    }
}
