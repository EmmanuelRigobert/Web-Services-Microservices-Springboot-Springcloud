package com.emmanuel.rest.webservices.restful_web_services.jpa;
// This interface enables the UserResource to communicate with the database

import com.emmanuel.rest.webservices.restful_web_services.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> { // This interface extends the JpaRepository interface which is used to perform CRUD operations on the User entity such as save, findAll, findById, delete, etc
    // The JpaRepository interface takes two parameters: the entity class and the primary key type
    // The User entity is the entity class and the primary key type is Integer
    // The User entity is a JPA entity which is mapped to a database table



}
