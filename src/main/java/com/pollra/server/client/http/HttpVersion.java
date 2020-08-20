package com.pollra.server.client.http;


import com.pollra.server.client.exception.TypeMissMathException;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1"), HTTP_2_0("HTTP/2.0");

    private String name;

    HttpVersion(String s1) {
        this.name = s1;
    }

    public static HttpVersion match(String name){
        for(HttpVersion version : HttpVersion.values()) {
            if(version.name.equals(name.trim())) {
                return version;
            }
        }
        throw new TypeMissMathException("존재하지 않는 버전입니다");
    }

    @Override
    public String toString() {
        return name;
    }
}
