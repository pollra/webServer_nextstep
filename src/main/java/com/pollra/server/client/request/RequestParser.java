package com.pollra.server.client.request;

import com.pollra.server.client.http.HttpMethod;
import com.pollra.server.client.http.HttpVersion;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class RequestParser {

    private String request;
    private URI uri;
    private String relativeUri;
    private HttpMethod method;
    private HttpVersion version;
    private Map<String, String> headers;
    private byte[] body;

    public RequestParser(String text) {
        request = text;
        String[] requests = request.split("\\n");
        parseStartLine(requests[0]);
        parseHeaders(requests);
        parseUri();
    }

    protected void parseStartLine(String startLine){
        String[] startLines = startLine.split(" ");
        method  = HttpMethod.match(startLines[0]);
        relativeUri = startLines[1];
        version = HttpVersion.match(startLines[2]);
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

    private void parseUri(){
        String uriString = "http://" + headers.get("Host") + relativeUri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    protected void parseBody(){
        if(request.split("\n\n").length > 1) {
            body = request.split("\n\n")[1].getBytes();
        }
    }


    @Override
    public String toString() {
        return "HttpRequest" +
                "{\n" +
                "uri="      + (uri     == null ? "null" : uri.toString())     + ", \n" +
                "method="   + method   + ", \n" +
                "version="  + version  + ", \n" +
                "header="   + (headers == null ? "null" : headers.toString()) + ", \n" +
                "body="     + (body    == null ? "null" : new String(body)) +
                "\n}";
    }

}
