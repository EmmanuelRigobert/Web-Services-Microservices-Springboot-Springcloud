package com.emmanuel.rest.webservices.restful_web_services.versioning;
// This class is used to create a new PersonV1 object
public class PersonV1 {
    private String name;
    // This constructor is used to create a new PersonV1 object with a name
    public PersonV1(String name) {
        this.name = name;
    }
    // This method is used to get the name of the PersonV1 object
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV1 [name=" + name + "]";
    }
}
