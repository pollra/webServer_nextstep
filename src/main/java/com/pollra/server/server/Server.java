package com.pollra.server.server;

import com.pollra.server.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private List<Client> sessions;

    private Server(){
        try{
            serverSocket = new ServerSocket(PORT);
            sessions = new Vector<Client>();
            executorService = Executors.newFixedThreadPool(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static Server INSTANCE = new Server();
    }

    public void start() throws IOException {
        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress()+"에서 접속 시도");
            Client client = new Client(socket);
            sessions.add(client);
            executorService.execute(client);
            System.out.println("connection 중인 session 갯수 : " + sessions.size());
        }
    }

}
