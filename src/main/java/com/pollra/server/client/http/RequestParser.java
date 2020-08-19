package com.pollra.server.client.http;

import com.pollra.server.client.exception.TypeMissMathException;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.*;

public class RequestParser {

    private String request;
    private HttpMethod method;
    private HttpClient.Version version;
    private Map<String, String> headers;
    private byte[] body;

    public RequestParser(String text) {
        request = text;
        String[] requests = request.split("\\n");
        parseMethodAndProtocol(requests[0]);
//        parseUri();
        parseHeaders(requests);
        parseBody();
    }

    protected void parseMethodAndProtocol(String method){
        String[] function = method.split("/");
        this.method = HttpMethod.match(function[0].trim());
        version = versionValueOf(function[1].trim()+"_"+function[2].trim().replace(".", "_"));
    }

    private HttpClient.Version versionValueOf(String sign) {
        for(HttpClient.Version version :  HttpClient.Version.values()) {
            if(version.name().equals(sign)) {
                return version;
            }
        }
        throw new TypeMissMathException("해당 서버에서는 허용되지 않은 버전입니다.");
    }

    protected void parseUri() {
        System.out.println("uri 파싱 하기 전 request 상태");
        System.out.println(request);
    }

    private String stringsToArray(String ...s) {
        StringBuilder result = new StringBuilder();
        for(String item : s ) {
            result.append(item);
        }
        return result.toString();
    }

    protected void parseHeaders(String[] requests){
        headers = new HashMap<>();
        String requestHeaders = sliceToRequestHeaders(requests);
        headerSetToString(requestHeaders);
    }

    private String sliceToRequestHeaders(String[] request){
        StringBuilder result = new StringBuilder();
        for (int i=1; i<request.length; i++){
            result.append(request[i]).append("\n");
        }
        return result.toString();
    }

    protected void headerSetToString(String text){
        String[] slice;
        for (String row : text.split("\\n")) {
            slice = row.split(":", 2);
            if(slice.length == 2) {
                this.headers.put(slice[0].trim(), slice[1].trim());
            }
        }
    }

    protected void parseBody(){
        if(request.split("\n\n").length > 1) {
            body = request.split("\n\n")[1].getBytes();
        }
    }

    @SneakyThrows
    public java.net.http.HttpRequest get(){
        try{
            System.out.println("host 정보: "+headers.get("Host"));
            System.out.println(Arrays.toString(headers.keySet().toArray()));
        }catch (Exception e) {
            System.out.println("ㅇㅅㅇ 뀨?");
        }
        return java.net.http.HttpRequest.newBuilder()
                .GET()
                .version(version)
                .uri(new URI(headers.get("Host")))
                .headers((String[]) headers.values().toArray())
                .build();
    }

    @Override
    public String toString() {
        return "HttpRequest" +
                "{\n" +
                "method="   + method   + ", \n" +
                "version=" + version + ", \n" +
                "header="   + (headers == null ? "null" : headers.toString())  + ", \n" +
                "body="     + (body    == null ? "null" : new String(body)) +
                "\n}";
    }

}
