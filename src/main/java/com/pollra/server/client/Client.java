package com.pollra.server.client;

import com.pollra.server.client.handler.DefaultRequestHandler;
import com.pollra.server.client.handler.MyStringUtils;
import com.pollra.server.client.handler.RequestHandler;
import com.pollra.server.client.http.RequestParser;

import java.io.*;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class Client implements Runnable{

    private Socket socket;
    private final int BUF_SIZE = 8192;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        startClient();
    }

    public boolean containsSocket(Socket socket) {
        return this.socket == socket;
    }

    private void startClient() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()){
            final byte[] buffer = new byte[BUF_SIZE];

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] body;
            int len;

            MyStringUtils myStringUtils = new MyStringUtils();
            while (true) {
                len = dataInputStream.read(buffer);
                byteArrayOutputStream.write(Arrays.copyOfRange(buffer, 0, len));
                if (len != BUF_SIZE) {
                    break;
                }
            }

            final byte[] rawData = byteArrayOutputStream.toByteArray();

            System.out.println("받은 데이터 =============================================\n"+new String(rawData));
            RequestParser requestParser = new RequestParser(new String(rawData));
            HttpRequest request = requestParser.get();

            RequestHandler.GET<TestObject> responseHandler = new DefaultRequestHandler<TestObject>();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            HttpResponse<TestObject> response = responseHandler.response(request);
            System.out.println(response.toString());
            // 요청을 처리할 놈
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
