package com.pollra.server.client.http;

import com.pollra.server.client.exception.TypeMissMathException;

import java.net.http.HttpClient;

public enum HttpProtocol {
    HTTP_1_1("HTTP/1.1"), HTTP_2_0("HTTP/2.0");

    private String name;

    HttpProtocol(String s) {
        this.name = s;
    }

    public static HttpClient.Version match(String text){
        for (HttpProtocol protocol : HttpProtocol.values()){
            if(protocol.name.equals(text)) {
                return HttpClient.Version.valueOf(protocol.name());
            }
        }
        throw new TypeMissMathException("존재하지 않는 타입입니다");
    }

    @Override
    public String toString() {
        return name;
    }
}
