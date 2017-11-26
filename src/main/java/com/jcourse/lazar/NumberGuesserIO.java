package com.jcourse.lazar;

import java.io.InputStreamReader;

public class NumberGuesserIO {
    public static void main(String[] args) {
        NumberGuesser numberGuesser = new NumberGuesser();
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        char buf[] = new char[2];
        int num;
        try {
            inputStreamReader.read(buf, 0, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //TODO(h.lazar) find a way to convert byte/char input to integer
//        buf.toString()
    }
}
