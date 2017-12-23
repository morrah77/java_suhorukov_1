package com.jcourse.lazar.HttpServer;

import com.jcourse.lazar.dirIndexGenerator.DirectoryParser;
import com.jcourse.lazar.dirIndexGenerator.HtmlGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ClientConnection extends Thread {
    private int id;
    private Socket socket;
    private byte[] rawRequest = new byte[1024];
    private String requestMethod;
    private String requestPath;
    public ClientConnection(Socket connectionSocket) {
        socket = connectionSocket;
    }
    public void setId(int connectionId) {
        id = connectionId;
    }
    public void run() {
        System.out.println("Client connection " + String.valueOf(id));
        int readBytes = 0;
        byte[] response;
        try {
            InputStream inputStream = socket.getInputStream();
            readBytes = inputStream.read(rawRequest);
        } catch (IOException e) {
            System.out.println("Connection read error!");
            System.out.println(e.getMessage());
            return;
        }

        if (readBytes == -1) {
            writeResoinse(new byte[]{});
            return;
        }

        try {
            parseRequest();
            response = generateResponse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            response = generateErrorResponse();
        }
        writeResoinse(response);

    }
    private void writeResoinse(byte[] response) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Connection write error!");
            System.out.println(e.getMessage());
        }
    }
    private void parseRequest() throws Exception{
        System.out.println("Raw request");
        System.out.println(new String(rawRequest));
        String requestLines[] = new String(rawRequest).split("\\n");
        if (requestLines.length <= 0) {
            throw new Exception("Bad request!");
        }
        String requestFields[] = requestLines[0].split("\\s");
        if (requestFields.length < 2) {
            throw new Exception("Bad 1st request line!");
        }
        requestMethod = requestFields[0];
        requestPath = requestFields[1];
    }
    private byte[] generateResponse() {
        byte[] html;
        String code;

        try {
            ArrayList<DirectoryParser.FileInfo> dirList = DirectoryParser.Parse(requestPath);
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            html = htmlGenerator.Generate(dirList);
            System.out.println(new String(html, Charset.forName("UTF-8")));
            code = "200 OK";
            return buildByteArrayResponse(code, html);
        } catch (Exception e) {
            return generateErrorResponse();
        }
    }

    private byte[] generateErrorResponse() {
        byte[] html;
        String code;

        code = "500 Internal server error";
        html = new byte[]{};

        return buildByteArrayResponse(code, html);
    }

    private byte[] buildByteArrayResponse(String code, byte[] body) {
        StringBuilder stringBuilder = new StringBuilder("HTTP/1.1 " + code + "\n");
        String headers = "Content-Type text/html/nContent-Length: " + body.length + "\n\n";
        stringBuilder.append(headers);
        stringBuilder.append(new String(body));

        return stringBuilder.toString().getBytes(Charset.forName("UTF-8"));
    }
}
