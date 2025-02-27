package com.emmanuel.rest.webservices.restful_web_services.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Expose REST API url= "/hello-world"
@RestController // This annotation is used to create RESTful web services using Spring MVC
public class HelloWorldController {

    // MessageSource is an interface for resolving messages, with support for the parameterization and internationalization of messages
    private MessageSource messageSource;
    private HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // hello-world
    //@RequestMapping(method = RequestMethod.GET, path = "/hello-world") // This annotation is used to map web requests onto specific handler classes and/or handler methods or..
    @GetMapping(path = "/hello-world") // This annotation is used to map HTTP GET requests onto specific handler methods
    public String helloWorld() { // This method will return "Hello World"
        return "Hello World";
        //url = http://localhost:8080/hello-world
    }

    @GetMapping(path = "/hello-world-bean") // This maps the GET request from browser to the /hello-world-bean URL
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World"); // This method will return an instance of our HelloWorldBean class
        //url = http://localhost:8080/hello-world-bean
    }
    /*How are request being handled?
    The DispatcherServlet is the front controller in Spring MVC. It is responsible for dispatching the request to the appropriate controller via Autoconfiguration

    How is the json response being generated?
    The Jackson library is used to convert the HelloWorldBean object to JSON format. This is done automatically by Spring MVC
     Springboot starter web dependency includes Jackson library which is used to convert Java objects to JSON format
     Springboot starter web also includes Tomcat which is used to run the application
     Springboot starter web also includes Spring MVC which is used to create RESTful web services


    Path parameters are used to pass data to the server. They are used to identify a specific resource or resources.
    e.g /users/{id}/posts/{post_id} => /users/1/posts/2
    */
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) { // This method will take in a name as a parameter
        return new HelloWorldBean(String.format("Hello World, %s", name)); // This method will return an instance of our HelloWorldBean class
        //url = http://localhost:8080/hello-world/path-variable/emmanuel
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {// This method will return a message in different languages
        //A locale is a set of parameters that defines the user's language, region and any special variant preferences that the user wants to see in their user interface
        Locale locale = LocaleContextHolder.getLocale(); // This gets the locale from the request header eg Accept-Language: nl and passes it to the locale variable
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);// This uses the messageSource to get the message from the properties file (resources.Resource Bundle 'message'.message.properties) and returns it with respect to the request header
        //url = http://localhost:8080/hello-world-internationalized
    }


}
