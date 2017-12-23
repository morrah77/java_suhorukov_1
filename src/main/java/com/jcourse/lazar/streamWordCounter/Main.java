package com.jcourse.lazar.streamWordCounter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
//import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Expected 2 args!");
            System.exit(0);
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0]))))) {
            int[] count = {0};
            reader
                .lines()
                .map(line -> line.split("[^\\pL\\pN]"))
                .flatMap(Arrays::stream)
                .filter(str -> !str.isEmpty())
                .peek(a -> count[0]++)
                .collect(toMap(a->a, a->1, (a,b)->a + b))
                    // instead of
//                .map(word -> new AbstractMap.SimpleEntry<String, Integer>(word, 1))
//                .collect(toMap(AbstractMap.SimpleEntry::getKey, pair -> pair.getValue(), (a, b) -> a + b))
                .entrySet()
                .stream()
                .sorted((a, b) -> {
                    int rs = b.getValue().compareTo(a.getValue());
                    if (rs == 0) {
                        rs = a.getKey().compareTo(b.getKey());
                    }
                    return rs;
                })
//                    .sorted(Main::entryComparator)
                .forEach((entry) -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//    private static Comparator<Map.Entry<String, Integer>> entryComparator(AbstractMap.SimpleEntry<String, Integer> x, AbstractMap.SimpleEntry<String, Integer> y) {
//        return (a, b) ->{
//            int rs = b.getValue().compareTo(a.getValue());
//            if (rs == 0) {
//                rs = a.getKey().compareTo(b.getKey());
//            }
//            return rs;
//        };
//    }
}
