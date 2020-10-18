package com.xiatiao.httpbin.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Strings;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class Helper {
    public WebRequest request;

    public Helper(WebRequest r) {
        request = r;
    }

    public ObjectNode getObjectNode(List<String> keys) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode resp = mapper.createObjectNode();
        for (String key : keys) {
            switch (key) {
                case "headers":
                    resp.set("headers", mapper.convertValue(getHeaders(), JsonNode.class));
                    break;
                case "origin":
                    resp.put("origin", getOrigin());
                    break;
                case "user-agent":
                    resp.put("user-agent", request.getHeader("user-agent"));
                    break;
                case "url":
                    resp.put("url", getUrl());
                    break;
                case "args":
                    resp.put("args", ((ServletWebRequest) request).getRequest().getQueryString());
                    break;
            }
        }
        return resp;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        for (Iterator<String> it = request.getHeaderNames(); it.hasNext(); ) {
            String name = it.next();
            headers.put(name, request.getHeader(name));
        }
        return headers;
    }

    private String getOrigin() {
        String origin = request.getHeader("x-forwarded-for");
        if (Strings.isNullOrEmpty(origin)) {
            origin = request.getHeader("remote-addr");
        }
        return origin;
    }

    private String getUrl() {
        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        String protocol = request.getHeader("x-forwarded-proto");
        if (Strings.isNullOrEmpty(protocol)) {
            protocol = request.getHeader("x-forwarded-protocol");
        }
        if (Strings.isNullOrEmpty(protocol) && request.getHeader("x-forwarded-ssl").equals("on")) {
            protocol = "https";
        }
        if (Strings.isNullOrEmpty(protocol)) {
            return url;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        return builder.scheme(protocol).toUriString();
    }
}
