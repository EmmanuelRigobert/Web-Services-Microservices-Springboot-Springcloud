package com.emmanuel.rest.webservices.restful_web_services.versioning;
// This class is used to create a new Name object.
public class Name {

    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Name [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
