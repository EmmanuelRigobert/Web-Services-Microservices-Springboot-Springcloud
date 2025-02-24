package com.emmanuel.rest.webservices.restful_web_services.exception;
//In this class, we are creating a custom error response for the Restful Web Services application
import com.emmanuel.rest.webservices.restful_web_services.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice //This annotation is used to handle exceptions globally across the whole application
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //This class is used to handle exceptions thrown by the Restful Web Services application
    //ie the structure of the response we see when an exception occurs e.g. 404 error

    @ExceptionHandler(Exception.class) //This annotation is used to handle exceptions of type Exception
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception { //This is the method from the parent class that we we want to override. But we are not overriding it. We are just using it to handle exceptions so we give it a different name
        //This method is used to handle exceptions thrown by the Restful Web Services application
        //It is responsible for providing a response to the client when an exception occurs
        //We can override the default error response by creating a custom error response

        //This creates a new ErrorDetails object with the current date, the exception message and the request description. Our custom error response
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); //This returns a response entity with the error details and a 500 status code
    }

    @ExceptionHandler(UserNotFoundException.class)
    //This annotation is used to handle exceptions of type UserNotFoundException
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception { //This is the method from the parent class that we we want to override.
        //This creates a new ErrorDetails object with the current date, the exception message and the request description. Our custom error response
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND); //This returns a response entity with the error details and a 500 status code
    }

    @Override //This annotation is used to override the handleMethodArgumentNotValid method from the parent class
    protected ResponseEntity<Object> handleMethodArgumentNotValid( //This method is used to handle exceptions of type MethodArgumentNotValidException
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        //This creates a new ErrorDetails object with the current date, the exception message and the request description. Our custom error response
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), //This is the current date and time
                //ex.getMessage(), //This is the exception message
                //ex.getFieldError().getDefaultMessage(), //This is the exception message which desplays the first default message of validation error
                "Validation error: " + ex.getErrorCount() + " error(s) :" + ex.getFieldError().getDefaultMessage(), //Similar to the above commented alternative but with a custom message
                request.getDescription(false)); //This is the request description
        //This returns a response entity with the error details and a 400 status code
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
