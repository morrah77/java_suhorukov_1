package com.jcourse.helen;

import static java.nio.charset.Charset.*;
import java.util.Scanner;

//import java.io.InputStreamReader;

/**
 *  ~/javadev/course/pract1 $ java -classpath src/main/java com.jcourse.helen.MyClass
 */
public class MyClass {
    public static String EXIT_COMMAND = "exit";
    public static void main(String[] args) {
        System.out.println(System.getProperties().getProperty("file.encoding"));
        System.out.println(defaultCharset().toString());
        String test = new String(("Здесь был Вася").getBytes(), defaultCharset());
        System.out.println(test);
        String s = "";
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);

        // or Scanner class
        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
        while (s != null && !(s.equals(EXIT_COMMAND))){

            s = scanner.nextLine();
            System.out.println(s);
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
