package com.pollra.server.client.http;

public enum HttpStatus {
    // 1xx

    // 2xx
    OK("200 OK"),

    // 3xx

    // 4xx
    NotFound("404 NotFound");
    private final String statusMessage;

    HttpStatus(String statusMessage){ this.statusMessage = statusMessage; }

    public String getStatusMessage(){ return statusMessage; }

}
