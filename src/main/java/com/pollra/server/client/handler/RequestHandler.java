package com.pollra.server.client.handler;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface RequestHandler {
    interface GET<T> {
        HttpResponse<T> response(HttpRequest request);
    }
}
