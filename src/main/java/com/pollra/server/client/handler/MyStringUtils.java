package com.pollra.server.client.handler;

import java.util.HashMap;
import java.util.Map;

public class MyStringUtils {

    public static Map<String, String> toMap(String text){
        Map<String, String> header = new HashMap<>();
        for (String row : text.split("\\n")) {
            System.out.println("row:["+row+"]");
            if(row.split(": ").length == 2) header.put(row.split(": ")[0], row.split(": ")[1]);
        }
        return header;
    }

}
