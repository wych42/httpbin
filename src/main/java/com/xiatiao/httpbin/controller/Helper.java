package com.xiatiao.httpbin.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

public class Helper {
    public static MappingJacksonValue getRequest(WebRequest request, Set<String> includes) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept((includes));
        FilterProvider filters = new SimpleFilterProvider().addFilter("filter", simpleBeanPropertyFilter);
        RequestModel model = new RequestModel(request);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(model);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
