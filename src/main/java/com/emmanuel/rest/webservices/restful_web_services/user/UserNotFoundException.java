package com.emmanuel.rest.webservices.restful_web_services.user;
// This class is used to create a custom exception that extends the RuntimeException class
// However, this is only a predefined implementation. An advanced implementation is done in the CustomizedResponseEntityExceptionHandler class where the ErrorDetails object is created and used to generate a custom error response
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND) // This annotation is used to handle the response status by returning a 404 status code
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        // This constructor is used to create a new UserNotFoundException object with a message
        //How is the error  being generated?

        super(message);
    }
}
