package com.xiatiao.httpbin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.Map;

@RestController
@Api(tags = "Request Inspection")
public class HttpbinController {
    @ApiOperation(value = "Return the incoming request's HTTP headers.")
    @ApiResponse(code=200, message = "The request's headers.")
    @GetMapping("/headers")
    public ResponseEntity<Map<String, String>> headers(@RequestHeader @ApiIgnore Map<String, String> headers) {
        return new ResponseEntity<Map<String, String>>(headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns the requester's IP Address.")
    @ApiResponse(code=200, message = "The Requester's IP Address.")
    @GetMapping("/ip")
    public ResponseEntity<Map<String, String>> ip(@RequestHeader @ApiIgnore Map<String, String> headers) {
        String origin = headers.get("x-forwarded-for");
        if (Strings.isNullOrEmpty(origin)) {
            origin = headers.get("remote-addr");
        }
        return new ResponseEntity<Map<String, String>>(Collections.singletonMap("origin", origin), HttpStatus.OK);
    }

    @ApiOperation(value = "Return the incoming requests's User-Agent header.")
    @ApiResponse(code=200, message="The request's User-Agent header.")
    @GetMapping("/user-agent")
    public ResponseEntity<Map<String, String>> userAgent(@RequestHeader @ApiIgnore Map<String, String> headers) {
        return new ResponseEntity<Map<String, String>>(Collections.singletonMap("user-agent", headers.get("user-agent")), HttpStatus.OK);
    }
}
