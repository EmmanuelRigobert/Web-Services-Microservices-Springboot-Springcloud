package com.emmanuel.rest.webservices.restful_web_services.exception;
/*
    * This class is used to create a custom exception. Here we define the structure of the error response for implementation in the CustomizedResponseEntityExceptionHandler class
*/

import java.time.LocalDateTime;

public class ErrorDetails {
    //timestamp, message, details

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        super();
        //what is the parent of ErrorDetails?

        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
