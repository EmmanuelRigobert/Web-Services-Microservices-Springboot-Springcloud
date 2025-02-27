package com.emmanuel.rest.webservices.restful_web_services.filtering;
//Customizing REST API Response using Filtering
//2. Using @JsonFilter annotation to filter out fields in the response e.g password field. This is done by static or dynamic filtering. Static filtering is done at the class level while dynamic filtering is done at the method level.

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties("field3") // This annotation is used to ignore the field3 value in the response.
@JsonFilter("SomeBeanFilter") // This annotation is used to filter out fields in the response. This is dynamic filtering.
public class SomeBean {
    private String field1;

    //@JsonIgnore // This annotation is used to ignore the field2 value in the response. This is static filtering.
    private String field2;


    private String field3;

    public SomeBean(String value1, String value2, String value3) {
        super();
        this.field1 = value1;
        this.field2 = value2;
        this.field3 = value3;
    }

    public String getValue1() {
        return field1;
    }

    public String getValue2() {
        return field2;
    }

    public String getValue3() {
        return field3;
    }

    public void setValue1(String value1) {
        this.field1 = value1;
    }

    public void setValue2(String value2) {
        this.field2 = value2;
    }

    public void setValue3(String value3) {
        this.field3 = value3;
    }

    @Override
    public String toString() {
        return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }
}
