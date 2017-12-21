package com.jcourse.lazar.HttpServer;

import java.io.IOException;
import javax.imageio.IIOException;
import java.net.ServerSocket;

public class Server {
    public ServerSocket serverSocket;
    private int port;
    public Server() {
        try {
//            if(port == null) {
                port = 8080;
//            }
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public Server(int port) {
        port = port;
        serverSocket = new ServerSocket(port);
    }
}
