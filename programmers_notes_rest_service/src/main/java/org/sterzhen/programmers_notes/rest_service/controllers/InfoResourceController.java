package org.sterzhen.programmers_notes.rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sterzhen.programmers_notes.core.domain.InfoResource;
import org.sterzhen.programmers_notes.core.services.InfoResourceService;
import org.sterzhen.programmers_notes.rest_api.core.Page;
import org.sterzhen.programmers_notes.rest_api.core.Pageable;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceServiceApi;
import org.sterzhen.programmers_notes.rest_service.dto.InfoResourceDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @GetMapping
    @Override
    public List<InfoResourceApi> getAll() {
        return infoResourceService.findAll().stream().map(this::mapToDto).collect(toList());
    }


    @PostMapping("/page")
    @Override
    public @ResponseBody
    Page<InfoResourceApi> getPage(@RequestBody Pageable pageable) {
         var result = infoResourceService.findAll();
        var page = result.stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .map(this::mapToDto).collect(toList());
        return new Page<>(pageable.getPageNumber(), pageable.getPageSize(), result.size() / pageable.getPageSize(), result.size(), page);
    }

    @PostMapping
    @Override
    public @ResponseBody
    InfoResourceApi create(@RequestBody InfoResourceApi resource) {
        var newResource = infoResourceService.create(resource.getName(), resource.getAddress(), resource.getDescription());
        return mapToDto(newResource);
    }

    @PutMapping
    @Override
    public @ResponseBody
    InfoResourceApi update(@RequestBody InfoResourceApi resource) {
        var updateResource = infoResourceService.update(mapToEntity(resource));
        return mapToDto(updateResource);
    }

    @DeleteMapping("{id}")
    @Override
    public @ResponseBody
    InfoResourceApi deleteById(@PathVariable Long id) {
        var old = infoResourceService.delete(id);
        return mapToDto(old);
    }

    private InfoResourceApi mapToDto(InfoResource o) {
        return new InfoResourceDto(o.getId(), o.getName(), o.getAddress(), o.getDescription());
    }

    private InfoResource mapToEntity(InfoResourceApi dto) {
        return InfoResource.of(dto.getId(), dto.getName(), dto.getAddress(), dto.getDescription());
    }
}
