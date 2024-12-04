package com.gebms.gebmsbackend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Department {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<User> users;

    public Department() {}

    public UUID getId() {
        return id;
    }

    public Department(String name) {
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    // Generate UUID before persisting the entity
    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
