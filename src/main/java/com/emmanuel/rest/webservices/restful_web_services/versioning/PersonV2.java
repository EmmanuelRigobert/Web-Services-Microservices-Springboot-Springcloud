package com.emmanuel.rest.webservices.restful_web_services.versioning;
// This class is used to create a new PersonV2 object
public class PersonV2 {

    private Name name;

    public PersonV2(Name name) {
        super();
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV2 [name=" + name + "]";
    }

}
