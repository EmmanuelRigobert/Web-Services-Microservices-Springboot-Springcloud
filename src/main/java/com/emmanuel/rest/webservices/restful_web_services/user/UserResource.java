
package com.emmanuel.rest.webservices.restful_web_services.user;

/* Social Media Application REST API
 Build a REST API for a social media application
 Key Resources:
    User
    Post
 Key Details:
    User:id,name,birthDate
    Post:id,user,description
 Key Operations:
    Retrieve all users - GET /users
    Create a user - POST /users
    Retrieve one user - GET /users/{id}
    Delete a user - DELETE /users/{id}
 */

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import jakarta.validation.Valid;

// This class is used to manage the user data
@RestController // This annotation is used to indicate that the class would be a controller in a RESTful web service.
public class UserResource {
    private UserDaoService service; // This is an instance of the UserDaoService class

    public UserResource(UserDaoService service) { // This is a constructor that takes an instance of the UserDaoService class
        this.service = service; // This assigns the instance of the UserDaoService class to the service variable
    }

    //GET /users
    @GetMapping("/users") // This annotation is used to map HTTP GET requests onto specific handler methods
    public List<User> retrieveAllUsers() { // This method will return all the users
        return service.findAll(); // This calls the findAll() method from the UserDaoService class
    //url = http://localhost:8080/users
    }
    //Get /users/{id}

    //HATEOAS - Hypermedia As The Engine Of Application State -With HATEOAS, a client interacts with a network application whose application servers provide information dynamically through hypermedia. A REST client needs little to no prior knowledge about how to interact with an application or server beyond a generic understanding of hypermedia.
    // With HATEOAS, the provider includes hypermedia links in the response that are used by the client to navigate the API. This allows the client to discover the API and interact with it without hardcoding URLs. e.g by providing links to all /users API from user/{id} API, the client can navigate the to the API without knowing the URL structure of /users.
    //Implementation options:
    //1. Custom implementation - You can manually create links in the response
    //2. Use Standard - HAL, Spring HATEOAS, JSON-LD, Siren, Collection+JSON, etc
    //HAL - Hypertext Application Language - A simple format that gives a consistent and easy way to hyperlink between resources in your API. It is a set of conventions for expressing hyperlinks in either JSON or XML.
    //Spring HATEOAS - A library of APIs that you can use to easily create REST representations that follow the HATEOAS principle when working with Spring and especially Spring MVC. It builds on the core JSON/XML support in Spring.

    @GetMapping("/users/{id}") // This annotation is used to map HTTP GET requests onto specific handler methods
    public EntityModel<User> retrieveUser(@PathVariable int id) { // PathVariable is used to bind the id value from the URL to the id parameter of the method
        User user = service.findOne(id); // This calls the findOne() method from the UserDaoService class

        //Response in case of user not found is not handled. Throws 200 status code instead of 404
        if(user == null) {
            throw new UserNotFoundException("id-" + id); // This throws a UserNotFoundException with the message "id-" + id
        }
        EntityModel<User> entityModel = EntityModel.of(user); // This creates an EntityModel of the user - hateoas

        //This creates a dynamic link to the retrieveAllUsers() method
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()); // This creates a link to the retrieveAllUsers() method
        entityModel.add(linkTo.withRel("all-users")); // This adds the link to the entityModel with the relation "all-users"
        return entityModel;
        //url = http://localhost:8080/users/1
    }

    //DELETE /users/{id}
    @DeleteMapping("/users/{id}") // This annotation is used to map HTTP DELETE requests onto specific handler methods
    public void deleteUser(@PathVariable int id) { // PathVariable is used to bind the id value from the URL to the id parameter of the method
        service.deleteById(id); // This calls the deleteById() method from the UserDaoService class
        //Delete = http://localhost:8080/users/1
    }

    //POST /users
    @PostMapping("/users") // This annotation is used to map HTTP POST requests onto specific handler methods
    //public void createUser(@RequestBody User user) { // RequestBody is used to bind the request body to a method parameter. I used Talend API Tester to test this by sending a POST request to http://localhost:8080/users with JSON data in the body. 200 response code was returned
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { // RequestBody is used to bind the request body to a method parameter. I used Talend API Tester to test this by sending a POST request to http://localhost:8080/users with JSON data in the body. Modified the return type to ResponseEntity<Object> to return a response entity with a 201 status code
    //How do I include validation in the function above?
    //To include validation in the function above, you can add the @Valid annotation to the function parameter. This would validate the user object before it is saved

        /*
        200: OK (successful request)
        201: Created (successful request)
        204: No Content (successful request but no content to return)
        400: Bad Request (wrong data)
        401: Unauthorized (authentication required)
        403: Forbidden (access denied)
        404: Not Found (wrong endpoint)
        500: Internal Server Error (server error)
         */

        User savedUser = service.save(user); // This calls the save() method from the UserDaoService class
        //Post = http://localhost:8080/users
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // This gets the current request uri
                .path("/{id}").buildAndExpand(savedUser.getId()) // This appends the id of the saved user to the current request uri
                .toUri(); // This converts the uri to a URI object
        // In summary, this generates the URI of the created user ie. http://localhost:8080/users/+(id of the saved user). This location of the newly created user would be returned in the header of the response when testing with Postman or Talend API Tester
        return ResponseEntity.created(location).build(); // This returns a response entity with a 201 status code.
    }
}