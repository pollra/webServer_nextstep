package com.pollra.server.client;

import lombok.Data;

@Data
public class TestObject {

    private Integer privateIntData;
    private String privateStringData;
    public Person publicPersonObject;

    @Data
    public static class Person {
        private String name;
        private Integer age;
    }
}
