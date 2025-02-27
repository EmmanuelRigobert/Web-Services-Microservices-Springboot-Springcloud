package com.emmanuel.rest.webservices.restful_web_services.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {
    private Integer id;

    //Customizing REST API Response
    //1. Using @JsonProperty annotation to customize the field name in the response
    //2. Using @JsonFilter annotation to filter out fields in the response e.g password field. This is done by static or dynamic filtering. Static filtering is done at the class level while dynamic filtering is done at the method level.

    @Size(min=2, message="Name should have at least 2 characters")
    @JsonProperty("user_name") //This annotation is used to customize the field name in the response
    private String name;

    @Past(message="Birth date should be in the past")
    @JsonProperty("user_birth_date") //This annotation is used to customize the field name in the response
    private LocalDate birthDate;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
    }
}
