
package com.emmanuel.rest.webservices.restful_web_services.user;

/*
    * JPA: Java Persistence API - A Java specification for accessing, persisting, and managing data between Java objects/classes and a relational database
    We created a duplicate of UserResource to implement JPA database operations. This class is used to manage the user data
    * The UserJpaResource class is a controller class that handles HTTP requests for the User entity using JPA
    * To prevent Mapping Ambiguity, we modified the RequestMapping annotation to "/jpa" to differentiate it from the UserResource class
 */

import com.emmanuel.rest.webservices.restful_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

// This class is used to manage the user data
@RestController
public class UserJpaResource {

    private UserRepository repository;

    public UserJpaResource(UserRepository repository) { // This is a constructor that takes an instance of the UserRepository interface
        this.repository = repository; // This assigns the instance of the UserRepository interface to the repository variable
    }

    //GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll(); // This calls the findAll() method from the UserRepository interface.
        //But there is no findAll() method in the UserRepository interface. How does this work?
        //The JpaRepository interface provides the findAll() method by default. The UserRepository interface extends the JpaRepository interface. Hence, the UserRepository interface has access to the findAll() method
    //url = http://localhost:8080/jpa/users
    }

    //Get /users/{id}
    @GetMapping("/jpa/users/{id}") // This annotation is used to map HTTP GET requests onto specific handler methods
    public EntityModel<User> retrieveUser(@PathVariable int id) { // PathVariable is used to bind the id value from the URL to the id parameter of the method
        Optional<User> user = repository.findById(id); // This calls the findById() method from the UserRepository interface. Optional is used to handle null values

        //Response in case of user not found is not handled. Throws 200 status code instead of 404. So we need to handle this by throwing a UserNotFoundException
        if(user.isEmpty()) {
            throw new UserNotFoundException("id-" + id); // This throws a UserNotFoundException with the message "id-" + id
        }
        EntityModel<User> entityModel = EntityModel.of(user.get()); // This creates an EntityModel of the user - hateoas. To get user object from Optional, we use the get() method

        //This creates a dynamic link to the retrieveAllUsers() method
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()); // This creates a link to the retrieveAllUsers() method
        entityModel.add(linkTo.withRel("all-users")); // This adds the link to the entityModel with the relation "all-users"
        return entityModel;
        //url = http://localhost:8080/jpa/users/1
    }

    //DELETE /users/{id}
    @DeleteMapping("/jpa/users/{id}") // This annotation is used to map HTTP DELETE requests onto specific handler methods
    public void deleteUser(@PathVariable int id) { // PathVariable is used to bind the id value from the URL to the id parameter of the method
        repository.deleteById(id); // This calls the deleteById() method from the UserRepository interface
        //Delete = http://localhost:8080/jpa/users/1
    }

    //POST /users
    @PostMapping("/jpa/users")

    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = repository.save(user); // This calls the save() method from the UserRepository interface
        // url = http://localhost:8080/jpa/users
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // This gets the current request uri
                .path("/{id}").buildAndExpand(savedUser.getId()) // This appends the id of the saved user to the current request uri
                .toUri(); // This converts the uri to a URI object
        // In summary, this generates the URI of the created user ie. http://localhost:8080/jpa/users/+(id of the saved user). This location of the newly created user would be returned in the header of the response when testing with Postman or Talend API Tester
        return ResponseEntity.created(location).build(); // This returns a response entity with a 201 status code.
    }
}