package org.sterzhen.programmers_notes.rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sterzhen.programmers_notes.core.damain.InfoResource;
import org.sterzhen.programmers_notes.core.services.InfoResourceService;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceDto;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceRestApi;

@CrossOrigin
@RestController
@RequestMapping("api/info_resource")
public class InfoResourceController implements InfoResourceRestApi {

    private final InfoResourceService infoResourceService;

    @Autowired
    public InfoResourceController(InfoResourceService infoResourceService) {
        this.infoResourceService = infoResourceService;
    }

    @GetMapping("{id}")
    @Override
    public InfoResourceDto getById(@PathVariable Long id) {
        var op = infoResourceService.findById(id).map(this::mapToDto);
        return op.isEmpty() ? null : op.get();
    }

    private InfoResourceDto mapToDto(InfoResource o) {
        return new InfoResourceDto(o.id(), o.name(), o.address(), o.description());
    }
}
