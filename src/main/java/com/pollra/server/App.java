package com.pollra.server;

import com.pollra.server.server.Server;

public class App {

    public static void main(String[] args) {
        try {
            Server.getInstance().start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
