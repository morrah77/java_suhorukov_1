package com.jcourse.lazar.calculator;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Stack;

import static java.nio.charset.Charset.defaultCharset;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader;
        if (args.length <= 0) {
            System.out.println("CLI input is not implemented yet! Please specify correct file name!");
            System.exit(0);
        }
        String fileName = args[0];
        Path path = FileSystems.getDefault().getPath(fileName);
        String line;
        Stack<Double> stack = new Stack<>();
        Map<String, Double> params;
        Invoker invoker = new Invoker(stack, params);
        try {
            bufferedReader = Files.newBufferedReader(path, Charset.forName(defaultCharset().toString()));
            while ((line = bufferedReader.readLine()) != null) {
                invoker.Invoke(line);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseInput() {

    }
}
