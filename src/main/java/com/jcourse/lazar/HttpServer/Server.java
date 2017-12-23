package com.jcourse.lazar.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public ServerSocket serverSocket;
    private int port;
    private Map<Integer, Thread> connectionThreads;
    public Server() {
        try {
            port = 8080;
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public Server(int port) {
        try {
            port = port;
            serverSocket = new ServerSocket(port);
            connectionThreads = new HashMap<>();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void Start() {
        System.out.println("Listen port " + port);
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Connection accepted!");
                ClientConnection connection = new ClientConnection(socket);
                int id = connection.hashCode();
                connection.setId(id);

                Thread connectionThread = new Thread(connection);
                connectionThreads.put(Integer.valueOf(id), connectionThread);
                connectionThread.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
