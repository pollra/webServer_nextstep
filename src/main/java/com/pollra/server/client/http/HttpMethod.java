package com.pollra.server.client.http;

public enum HttpMethod {
    GET, POST, PUT, DELETE, HEAD, CONNECT, OPTIONS, TRACE, PATCH;

    public static HttpMethod match(String text) {
        return HttpMethod.valueOf(text.trim().toUpperCase());
    }
}
