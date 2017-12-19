package com.jcourse.lazar.wordcounter;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.Charset.defaultCharset;

// TODO(h.lazar) make it based on Stream API (BufferedReader.lines().map(implementLogicHere()))
public class Main {
    private static Map<String, WordCounter> freqDictionary;
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Please pass file name!");
        }
        String charsetName = defaultCharset().toString();
        String fileName = args[0];
        if (args.length <= 1) {
            charsetName = args[1];
        }
        Path filePath = FileSystems.getDefault().getPath(fileName);
        int readInt;
        freqDictionary = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Reader fileReader = new InputStreamReader(new BufferedInputStream(new FileInputStream(filePath.toString())), charsetName);
            LOOP:
            while(true) {
                readInt = fileReader.read();
                if  (readInt <= 0) {
                    writeTheWord(stringBuilder);
                    break LOOP;
                }
                if (!Character.isLetterOrDigit(readInt)) {
                    writeTheWord(stringBuilder);
                    continue LOOP;
                }
                stringBuilder.append(readInt);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }
    private static void writeTheWord(StringBuilder stringBuilder) {
        String currentWord = stringBuilder.toString();
        WordCounter currentWordCounter = freqDictionary.get(currentWord);
        if (currentWordCounter == null) {
            putNewWordIndoDictionary(currentWord);
        }
        int currentFreq = currentWordCounter.freqAbsolute;
        currentFreq += 1;
        currentWordCounter.freqAbsolute = currentFreq;
        stringBuilder.setLength(0);
    }

    private static void sortTheDictionary() {
        ArrayList<Map.Entry<String, WordCounter>> arrayList = new ArrayList<>(freqDictionary.entrySet());
//        arrayList.addAll(freqDictionary.entrySet());
        arrayList.sort(new Comparator() {
            @Override
            public int compare(Map.Entry<String, WordCounter> o1, Map.Entry<String, WordCounter> o2) {
                // use Integer.compare()
                return Integer.compare(o2.getValue().freqAbsolute, o1.getValue().freqAbsolute);
            }
        });
    }

    private static void putNewWordIndoDictionary(String currentWord) {
        WordCounter currentWordCounter = new WordCounter(1.);
        freqDictionary.put(currentWord, currentWordCounter);
    }

    private static class WordCounter {
        int freqAbsolute;
        Double freqPercentage;
        WordCounter() {
            freqAbsolute = 0;
            freqPercentage = 0.;
        }
        WordCounter(Double freqAbsolute) {
            freqAbsolute = freqAbsolute;
            freqPercentage = 0.;
        }
    }
}
