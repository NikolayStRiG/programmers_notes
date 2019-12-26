package org.sterzhen.programmers_notes.rest_service.dto;

import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;

public class InfoResourceDto implements InfoResourceApi {

    private Long id;
    private String name;
    private String address;
    private String description;

    public InfoResourceDto() {
    }

    public InfoResourceDto(Long id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
