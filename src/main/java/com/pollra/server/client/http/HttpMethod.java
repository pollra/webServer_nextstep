package com.pollra.server.client.http;

import com.pollra.server.client.exception.MethodMissMathException;

public enum HttpMethod {
    GET, POST, PUT, DELETE, HEAD, CONNECT, OPTIONS, TRACE, PATCH;

    public static HttpMethod match(String text) {
        for (HttpMethod method : HttpMethod.values()) {
            if(method.toString().equals(text)) return method;
        }
        throw new MethodMissMathException("존재하지 않는 메소드입니다");
    }
}
