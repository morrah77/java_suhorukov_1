package com.jcourse.lazar.HttpServer;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            try {
                Integer p = Integer.valueOf(args[0]);
                port = p.intValue();
            } catch (NumberFormatException e) {
                System.out.println("Please pass correct port or do not pass anything to run HTTP server on port 8080!");
                System.exit(0);
            }
        }
        Server server = new Server(port);
        System.out.println("Try to run HTTP server on port " + String.valueOf(port) + "...");
        try {
            server.Start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
    }
}
