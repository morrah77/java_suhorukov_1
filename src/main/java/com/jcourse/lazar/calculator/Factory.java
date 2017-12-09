package com.jcourse.lazar.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

public class Factory {
    private Properties properties;
    public Factory() {
        try {
            properties = new Properties();
            InputStream inputStream = Factory.class.getResourceAsStream("commands.properties");
            properties.load(inputStream);
            System.out.println("Factory is instantiated with properties:\n" + properties);
        } catch (IOException ex) {
            System.out.println(ex.getCause());
            ex.printStackTrace();
        }
    }
    public Command getCommand(Stack<Double> stack, Map<String, Double> params, String commandName) {
        try {
            Class<?> commandClass = Class.forName(properties.getProperty(commandName));
            Command command = (Command) commandClass.newInstance();
            Field[] fields = commandClass.getDeclaredFields();
            for (Field field : fields) {
//                System.out.println(field);
                if (field.isAnnotationPresent(In.class)) {
                    In annotation = field.getAnnotation(In.class);
                    switch (annotation.arg()) {
                        case STACK: {
                            field.setAccessible(true);
                            field.set(command, stack);
                        }
                    }
                    switch (annotation.arg()) {
                        case DEFINITIONS: {
                            field.setAccessible(true);
                            field.set(command, params);
                        }
                    }
                }
            }
            return command;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getCause());
            ex.printStackTrace();
        } catch (IllegalAccessException | InstantiationException ex) {
            System.out.println(ex.getCause());
            ex.printStackTrace();
        }
        return null;
    }
}
