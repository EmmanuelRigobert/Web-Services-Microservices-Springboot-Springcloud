package com.emmanuel.rest.webservices.restful_web_services.helloworld;

public class HelloWorldBean {
    private String message;
    public HelloWorldBean(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override // This annotation is used to override the toString() method
    public String toString() { // This is called when the object is printed.
        return String.format("HelloWorldBean [message=%s]", message); // This method returns the formatted string
    }

    //How is the json response being generated?
    //The Jackson library is used to convert the HelloWorldBean object to JSON format. This is done automatically by Spring MVC
    // Springboot starter web dependency includes Jackson library which is used to convert Java objects to JSON format
    // Springboot starter web also includes Tomcat which is used to run the application
    // Springboot starter web also includes Spring MVC which is used to create RESTful web services
}
