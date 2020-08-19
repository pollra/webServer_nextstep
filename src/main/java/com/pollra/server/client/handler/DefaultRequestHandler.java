package com.pollra.server.client.handler;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultRequestHandler<T> implements RequestHandler.GET<T>{
    @Override
    public HttpResponse<T> response(HttpRequest request) {
        System.out.println(request.uri());
        return null;
    }
}
