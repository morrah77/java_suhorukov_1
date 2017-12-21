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
        for (String method: methods) {
            try {
                generator.getClass().getMethod(method).invoke(generator);
            } catch (Exception e) {
                System.out.println(method);
                System.out.println(e.getCause());
            } catch (Error e) {
                System.out.println(method);
                System.out.println(e.getCause());
            }
        }
    }
}
