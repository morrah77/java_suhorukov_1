package com.jcourse.lazar;

import static java.nio.charset.Charset.*;

import java.nio.charset.Charset;
import java.util.Scanner;

//import java.io.InputStreamReader;

/**
 *  ~/javadev/course/pract1 $ java -classpath src/main/java com.jcourse.lazar.EchoClass
 */
public class EchoClass {

    // some common constants
    private static String EXIT_COMMAND = "exit";
    private static final String IMPLEMENTATION_SCANNER = "scanner";
    private static final String IMPLEMENTATION_CONSOLE = "console";
    private static final String IMPLEMENTATION_STREAM = "stream";
    // TODO(h.lazar) find a way to use enum to switsh(){} implementation argument
//    public enum  AVAILABLE_IMPLEMENTATIONS {
//        scanner,
//        console,
//        stream
//    }

    public static void main(String[] args) {
        System.out.println("Accepts implementation [scanner|console|stream] and charset (like UTF-8, cp1251 etc.) arguments,\nechoes using 'stream' implementation and jvm 'file.encoding' property  by default");

        // some debug output
//        System.out.println("Received arguments:");
//        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
//        }

        // choose desired implementation
        String implementation = "";
        String charsetName = System.getProperties().getProperty("file.encoding");
        Charset charset = defaultCharset();

        // some debug output
//        System.out.println("jvm file encoding: " + charsetName);
//        System.out.println("jvm default charset: " + charset.toString());

        // TODO(h.lazar) find a way to process arguments better
        if (args.length > 0) {
            implementation = args[0];
        }
        if (args.length > 1) {
            charsetName = args[1];
            try {
                charset = Charset.forName(charsetName);
            } catch (Exception e) {
                System.out.println("Wrong charset!\n" + e.getMessage() + "\nexiting...");
                System.exit(1);
            }
        }
        System.out.println("Will echo in charset: " + charset.toString());

        // TODO(h.lazar) find a way to use switsh(enum) here
        switch (implementation) {
            case IMPLEMENTATION_CONSOLE:
                echoConsole(charset);
                break;
            case IMPLEMENTATION_STREAM:
                echoInputStreamReader(charset);
                break;
            case IMPLEMENTATION_SCANNER: default:
                echoScanner(charset);
        }

    }

    // implement echo using Scanner class
    private static void echoScanner(Charset charset) {
        System.out.println("implement echo using Scanner class\nType something then press Enter\nType 'exit' to stop echoing");
        String readString = "";
        Scanner scanner = new Scanner(System.in);
        while (readString != null && !readString.equals(EXIT_COMMAND)){
            readString = scanner.nextLine();
            System.out.println(new String(readString.getBytes(), charset));
        }
    }

    // implement echo using Console class
    private static void echoConsole(Charset charset) {
        System.out.println("implement echo using Console class\nType something then press Enter\nType 'exit' to stop echoing");
        String readString = "";
        java.io.Console console = System.console();
        if (console == null) {
            System.out.println("No console available!\nexiting...");
            System.exit(1);
        }
        while(readString != null && !readString.equals(EXIT_COMMAND)) {
            readString = System.console().readLine();
            System.console().printf("%s\n", new String(readString.getBytes(), charset));
        }
    }

    // implement echo using InputStreamReader class
    private static void echoInputStreamReader(Charset charset) {
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        System.out.println("Not implemented yet...");
    }
}
