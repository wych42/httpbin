package com.xiatiao.httpbin.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;

@RestController
@Api(tags = "HTTP Methods")
public class HttpMethods {
    @ApiOperation(value = "The request's query parameters.")
    @ApiResponse(code = 200, message = "The request's query parameters.")
    @GetMapping("/get")
    public ResponseEntity<MappingJacksonValue> get(@ApiIgnore WebRequest request) {
        FilterProvider filters = new SimpleFilterProvider().addFilter("filter", SimpleBeanPropertyFilter.serializeAllExcept(Collections.emptySet()));
        RequestModel model = new RequestModel(request);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(model);
        mappingJacksonValue.setFilters(filters);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }
}
