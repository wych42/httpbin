package com.xiatiao.httpbin.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@RestController
@Api(tags = "Request Inspection")
public class Request {
    @ApiOperation(value = "Return the incoming request's HTTP headers.")
    @ApiResponse(code = 200, message = "The request's headers.")
    @GetMapping("/headers")
    public ResponseEntity<ObjectNode> headers(@ApiIgnore WebRequest request) {
        Helper helper = new Helper(request);
        return new ResponseEntity<>(helper.getObjectNode(Arrays.asList("headers")), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns the requester's IP Address.")
    @ApiResponse(code = 200, message = "The Requester's IP Address.")
    @GetMapping("/ip")
    public ResponseEntity<ObjectNode> ip(@ApiIgnore WebRequest request) {
        Helper helper = new Helper(request);
        return new ResponseEntity<>(helper.getObjectNode(Arrays.asList("origin")), HttpStatus.OK);
    }

    @ApiOperation(value = "Return the incoming requests's User-Agent header.")
    @ApiResponse(code = 200, message = "The request's User-Agent header.")
    @GetMapping("/user-agent")
    public ResponseEntity<ObjectNode> userAgent(@ApiIgnore WebRequest request) {
        Helper helper = new Helper(request);
        return new ResponseEntity<>(helper.getObjectNode(Arrays.asList("user-agent")), HttpStatus.OK);
    }

}
