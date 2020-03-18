package org.sterzhen.programmers_notes.core.domain;

public class InfoResource {

    private Long id;
    private String name;
    private String address;
    private String description;

    public static InfoResource of(Long id, String name, String address, String description) {
        final InfoResource resource = new InfoResource();
        resource.setId(id);
        resource.setName(name);
        resource.setAddress(address);
        resource.setDescription(description);
        return resource;
    }

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

    @Override
    public String toString() {
        return "InfoResource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
