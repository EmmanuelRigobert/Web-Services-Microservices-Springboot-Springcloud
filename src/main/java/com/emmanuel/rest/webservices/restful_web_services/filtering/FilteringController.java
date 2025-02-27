package com.emmanuel.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filtering() {
        return new SomeBean("value1", "value2", "value3");
        // url: http://localhost:8080/filtering
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> filteringList() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value12", "value22", "value32"));
        // url: http://localhost:8080/filtering-list
    }
/*
    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue dynamicFiltering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        // Dynamic filtering.This is used to filter out fields in the response
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3"); // This is used to filter out all fields except field1 and field3
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);//
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
    */
}
