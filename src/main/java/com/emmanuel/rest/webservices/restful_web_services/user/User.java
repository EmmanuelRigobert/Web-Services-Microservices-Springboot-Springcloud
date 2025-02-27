package com.emmanuel.rest.webservices.restful_web_services.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")//This annotation is used to create a table in the database. The name attribute is used to specify the table name to be user_details in the database because user is a reserved keyword in database
public class User {

    protected User() {
        //This constructor is used to create a new User object which is required by JPA
    }

    @Id//This annotation is used to specify the primary key of the table
    @GeneratedValue//This annotation is used to specify the generation strategy of the primary key
    private Integer id;

    //Customizing REST API Response
    //1. Using @JsonProperty annotation to customize the field name in the response
    //2. Using @JsonFilter annotation to filter out fields in the response e.g password field. This is done by static or dynamic filtering. Static filtering is done at the class level while dynamic filtering is done at the method level.

    @Size(min=2, message="Name should have at least 2 characters")
    //@JsonProperty("user_name") //This annotation is used to customize the field name in the response
    private String name;

    @Past(message="Birth date should be in the past")
   // @JsonProperty("user_birth_date") //This annotation is used to customize the field name in the response
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")//This annotation is used to specify the relationship between the User entity and the Post entity. The mappedBy attribute is used to specify the field in the Post entity that maps the relationship
    //@JsonIgnoreProperties("user")//This annotation is used to ignore the posts field in the response
    private List<Post> posts;

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s, posts=%s]", id, name, birthDate, posts);
    }
}
