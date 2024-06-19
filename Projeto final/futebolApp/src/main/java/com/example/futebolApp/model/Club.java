package com.example.futebolApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "State is mandatory")
    @Size(min = 2, max = 2, message = "State must have 2 characters")
    @Column(nullable = false)
    private String state;

    @NotNull(message = "Creation date is mandatory")
    @PastOrPresent(message = "Creation date cannot be in the future")
    @Column(nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Active status is mandatory")
    @Column(nullable = false)
    private Boolean active;



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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
