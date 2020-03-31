package org.sterzhen.programmers_notes.rest_service.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InfoResourceEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static InfoResourceEntity of(Long id, String name, String address, String description) {
        final InfoResourceEntity resource = new InfoResourceEntity();
        resource.setId(id);
        resource.setName(name);
        resource.setAddress(address);
        resource.setDescription(description);
        return resource;
    }
}
