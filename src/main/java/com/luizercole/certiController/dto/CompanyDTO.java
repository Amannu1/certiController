package com.luizercole.certiController.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizercole.certiController.entities.Company;

import java.time.Instant;

public class CompanyDTO {

    private Long id;

    // importar o jakarta validation @NotBlank
    private String name;
    private Boolean active;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;

    private CompanyDTO(){

    }

    public CompanyDTO(Long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public CompanyDTO(Company entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.active = entity.getActive();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
