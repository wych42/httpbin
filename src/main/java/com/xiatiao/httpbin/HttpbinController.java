package com.xiatiao.httpbin;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HttpbinController {
    @GetMapping("/headers")
    public ResponseEntity<Map<String, String>> ip(@RequestHeader Map<String, String> headers) {
        return new ResponseEntity<Map<String, String>>(headers, HttpStatus.OK);
    }
}
