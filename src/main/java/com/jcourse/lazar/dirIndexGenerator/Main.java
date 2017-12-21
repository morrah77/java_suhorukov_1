package com.jcourse.lazar.dirIndexGenerator;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            String dirPath = new File(".").getAbsolutePath();
            if (args.length > 0) {
                dirPath = args[0];
            }
            ArrayList<DirectoryParser.FileInfo> dirList = DirectoryParser.Parse(dirPath);
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            byte[] html = htmlGenerator.Generate(dirList);
            System.out.println(new String(html, Charset.forName("UTF-8")));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}
