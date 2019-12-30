package org.sterzhen.programmers_notes.rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sterzhen.programmers_notes.core.damain.InfoResource;
import org.sterzhen.programmers_notes.core.services.InfoResourceService;
import org.sterzhen.programmers_notes.rest_api.core.Page;
import org.sterzhen.programmers_notes.rest_api.core.Pageable;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceServiceApi;
import org.sterzhen.programmers_notes.rest_service.dto.InfoResourceDto;

@CrossOrigin
@RestController
@RequestMapping("api/info_resources")
public class InfoResourceController implements InfoResourceServiceApi {

    private final InfoResourceService infoResourceService;

    @Autowired
    public InfoResourceController(InfoResourceService infoResourceService) {
        this.infoResourceService = infoResourceService;
    }

    @GetMapping("{id}")
    @Override
    public InfoResourceApi getById(@PathVariable Long id) {
        var op = infoResourceService.findById(id).map(this::mapToDto);
        return op.isEmpty() ? null : op.get();
    }

    @GetMapping("")
    @Override
    public Page<InfoResourceApi> getPage(Pageable pageable) {
        return null;
    }

    @PostMapping("")
    @Override
    public InfoResourceApi create(InfoResourceApi resource) {
        var newResource = infoResourceService.create(resource.getName(), resource.getAddress(), resource.getDescription());
        return mapToDto(newResource);
    }

    @PutMapping("{id}")
    @Override
    public InfoResourceApi update(InfoResourceApi resource) {
        var updateResource = infoResourceService.update(mapToEntity(resource));
        return mapToDto(updateResource);
    }

    @DeleteMapping("{id}")
    @Override
    public InfoResourceApi deleteById(Long id) {
        var old = infoResourceService.delete(id);
        return mapToDto(old);
    }

    private InfoResourceApi mapToDto(InfoResource o) {
        return new InfoResourceDto(o.id(), o.name(), o.address(), o.description());
    }

    private InfoResource mapToEntity(InfoResourceApi dto) {
        return InfoResource.of(dto.getId(), dto.getName(), dto.getAddress(), dto.getDescription());
    }
}
