package com.xiatiao.httpbin.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@JsonFilter("filter")
public class RequestModel {
    private WebRequest request;

    public Map<String, String> headers;
    public String origin;
    public String userAgent;
    public String url;
    public String args;

    public RequestModel(WebRequest r) {
        request = r;
    }

    @JsonProperty("user-agent")
    public String getUserAgent() {
        return request.getHeader("user-agent");
    }

    public String getArgs() {
        return ((ServletWebRequest) request).getRequest().getQueryString();
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        for (Iterator<String> it = request.getHeaderNames(); it.hasNext(); ) {
            String name = it.next();
            headers.put(name, request.getHeader(name));
        }
        return headers;
    }

    public String getOrigin() {
        String origin = request.getHeader("x-forwarded-for");
        if (Strings.isNullOrEmpty(origin)) {
            origin = request.getHeader("remote-addr");
        }
        return origin;
    }

    public String getUrl() {
        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        String protocol = request.getHeader("x-forwarded-proto");
        if (Strings.isNullOrEmpty(protocol)) {
            protocol = request.getHeader("x-forwarded-protocol");
        }
        if (Strings.isNullOrEmpty(protocol)) {
            String forwardedSsl = request.getHeader("x-forwarded-ssl");
            if (!Strings.isNullOrEmpty(forwardedSsl) && forwardedSsl.equals("on")) {
                protocol = "https";
            }
        }
        if (Strings.isNullOrEmpty(protocol)) {
            return url;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        return builder.scheme(protocol).toUriString();
    }
}
