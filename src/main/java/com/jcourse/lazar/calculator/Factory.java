package com.jcourse.lazar.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Factory {
    private Properties properties;
    private boolean debugMode;
    private static Logger logger;
    private Object proxiedCommand;
    public Factory() {
        try {
            properties = new Properties();
            InputStream inputStream = Factory.class.getResourceAsStream("commands.properties");
            properties.load(inputStream);
            debugMode = System.getenv("debug") != null;
            if (debugMode) {
                logger = LogManager.getLogger(Factory.class);
            }
            System.out.println("Factory is instantiated with properties:\n" + properties);
        } catch (IOException ex) {
            System.out.println(ex.getCause());
            ex.printStackTrace();
        }
    }
    public Command getCommand(Stack<Double> stack, Map<String, Double> params, String commandName) {
        try {
            Class<?> commandClass = Class.forName(properties.getProperty(commandName));
            final Command invokedCommand;
            Command command;
            invokedCommand = (Command) commandClass.newInstance();
            Field[] fields = commandClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(In.class)) {
                    In annotation = field.getAnnotation(In.class);
                    switch (annotation.arg()) {
                        case STACK: {
                            field.setAccessible(true);
                            field.set(invokedCommand, stack);
                        }
                    }
                    switch (annotation.arg()) {
                        case DEFINITIONS: {
                            field.setAccessible(true);
                            field.set(invokedCommand, params);
                        }
                    }
                }
            }
            if (debugMode) {
                ClassLoader classLoader = Command.class.getClassLoader();
                Class<?>[] interfaces = new Class<?>[]{Command.class};
                InvocationHandler invocationHandler = new InvocationHandler() {
//                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        logger.trace(args);
                        return method.invoke(invokedCommand, args);
                    }
                };
                proxiedCommand = Proxy.newProxyInstance(
                        classLoader,
                        interfaces,
                        invocationHandler
//                (proxy, method, args) -> {
//                            logger.trace(args);
//                            return method.invoke(proxy, args);
//                        }
                );
                command = (Command) proxiedCommand;
            } else {
                command = invokedCommand;
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
