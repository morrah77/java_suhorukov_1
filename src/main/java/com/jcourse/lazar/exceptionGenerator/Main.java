package com.jcourse.lazar.exceptionGenerator;

import com.jcourse.lazar.exceptionGenerator.core.CoreExceptionGenerator;
public class Main {

    public static void main(String[] args) throws Exception {
        CoreExceptionGenerator generator = new CoreExceptionGenerator();
        String methods[] = new String[]{
                "generateNullPointerException",
                "generateClassCastException",
                "generateNumberFormatException",
                "generateStackOverflowError",
                "generateOutOfMemoryError",
                "generateMyException"
        };
        for (int i = 0; i < methods.length; i++) {
            try {
                generator.getClass().getMethod(methods[i]).invoke(generator);
            } catch (Exception e) {
                System.out.println(methods[i]);
                System.out.println(e.getMessage());
            }
        }
    }
}
