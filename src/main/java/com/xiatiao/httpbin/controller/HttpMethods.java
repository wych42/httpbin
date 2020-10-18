package com.xiatiao.httpbin.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;

@RestController
@Api(tags = "HTTP Methods")
public class HttpMethods {
    @ApiOperation(value = "The request's query parameters.")
    @ApiResponse(code = 200, message = "The request's query parameters.")
    @GetMapping("/get")
    public ResponseEntity<ObjectNode> get(@ApiIgnore WebRequest request) {
        Helper helper = new Helper(request);
        return new ResponseEntity<>(helper.getObjectNode(Arrays.asList("url", "args", "headers", "origin")), HttpStatus.OK);
    }
}
