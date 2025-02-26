package com.emmanuel.rest.webservices.restful_web_services.versioning;
//This class is used to create a new PersonV1 object and implement versioning or seemless updates into PersonV2 object
//Versioning is a way of managing changes to a software application. It is the process of assigning unique version numbers to unique states of a software application. It is used to manage changes to software applications and to track the history of changes made to a software application.
//There are different types of versioning such as:
//1. URI versioning
//2. Request parameter versioning
//3. Header versioning
//4. Content negotiation versioning


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//URI versioning is the most popular type of versioning. It is the process of adding a version number to the URI of a resource. This is done by adding a version number to the URI of a resource. For example, /v1/person or /v2/person
@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
        // url: http://localhost:8080/v1/person
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
        // url: http://localhost:8080/v2/person
    }

    //Request parameter versioning is the process of adding a version number to the request parameter of a resource. This is done by adding a version number to the request parameter of a resource. For example, /person?version=1 or /person?version=2
    @GetMapping(path = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Bob Charlie");
        // url: http://localhost:8080/person/param?version=1
    }

    @GetMapping(path = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
        // url: http://localhost:8080/person/param?version=2
    }

    //Header versioning is the process of adding a version number to the header of a resource. For example, /person with a header of version=1 or /person with a header of version=2
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Bob Charlie");
        // url: http://localhost:8080/person/header
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
        // url: http://localhost:8080/person/header
    }

    //Content negotiation versioning is the process of adding a version number to the content negotiation of a resource. For example, /person with a header of Accept=application/vnd.company.app-v1+json or /person with a header of Accept=application/vnd.company.app-v2+json
    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Bob Charlie");
        // url: http://localhost:8080/person/produces
    }

    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
        // url: http://localhost:8080/person/produces
    }

    //Factors to consider when versioning:
    //URI Pollution: URI becomes more complex with versioning which is the case for URI versioning
    //Misuse of HTTP Headers: HTTP headers are not meant for versioning but for headers which is the case for Header versioning and Content Negotiation
    //Caching: Caching becomes complex with versioning. If the same URI is used for different versions, caching becomes complex which is the case for URI versioning and Request Content Negotiation
    //Can we execute the request on the browser: We can execute the request on the browser with URI versioning and Request Parameter versioning but not with Header versioning and Content Negotiation because headers are not visible on the browser.
    //API Documentation: API documentation becomes complex with Header versioning and Content Negotiation because the version is not part of the URI
    //Recommendation: Use one versioning strategy for the entire application or enterprise.
}
