package org.sterzhen.programmers_notes.rest_api.dto;

/**
 * Information resource
 */
public interface InfoResourceApi {

    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    String getAddress();
    void setAddress(String address);
    String getDescription();
    void setDescription(String description);
}
