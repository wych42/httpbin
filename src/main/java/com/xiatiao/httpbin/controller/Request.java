package com.xiatiao.httpbin.controller;

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
import java.util.HashSet;

@RestController
@Api(tags = "Request Inspection")
public class Request {
    @ApiOperation(value = "Return the incoming request's HTTP headers.")
    @ApiResponse(code = 200, message = "The request's headers.")
    @GetMapping("/headers")
    public ResponseEntity<MappingJacksonValue> headers(@ApiIgnore WebRequest request) {
        return new ResponseEntity<>(Helper.getRequest(request, new HashSet<>(Collections.singletonList("headers"))), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns the requester's IP Address.")
    @ApiResponse(code = 200, message = "The Requester's IP Address.")
    @GetMapping("/ip")
    public ResponseEntity<MappingJacksonValue> ip(@ApiIgnore WebRequest request) {
        return new ResponseEntity<>(Helper.getRequest(request, new HashSet<>(Collections.singletonList("origin"))), HttpStatus.OK);
    }

    @ApiOperation(value = "Return the incoming requests's User-Agent header.")
    @ApiResponse(code = 200, message = "The request's User-Agent header.")
    @GetMapping("/user-agent")
    public ResponseEntity<MappingJacksonValue> userAgent(@ApiIgnore WebRequest request) {
        return new ResponseEntity<>(Helper.getRequest(request, new HashSet<>(Collections.singletonList("user-agent"))), HttpStatus.OK);
    }

}
