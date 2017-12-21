package com.jcourse.lazar.dirIndexGenerator;

import org.apache.logging.log4j.core.util.ArrayUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class HtmlGenerator {
    private StringBuilder htmlStringBuilder;
    public HtmlGenerator() {
        htmlStringBuilder = new StringBuilder();
    }
    public byte[] Generate(ArrayList<DirectoryParser.FileInfo> directoryList) {
        String documentTitle = "";
        if (directoryList.size() > 0) {
            documentTitle = directoryList.get(0).fullPath;
        }
        formHeader(documentTitle);
        formBody(directoryList);
        formFooter();
        return htmlStringBuilder.toString().getBytes(Charset.forName("UTF-8"));
    }
    private void formBody(ArrayList<DirectoryParser.FileInfo> directoryList) {
        htmlStringBuilder.append("<table>");
        for (DirectoryParser.FileInfo info: directoryList) {
            htmlStringBuilder.append("<tr>");

            htmlStringBuilder.append("<td>");
            htmlStringBuilder.append(getHyperlink(info.fullPath, info.name));
            htmlStringBuilder.append("&nbsp;");
            htmlStringBuilder.append(info.modified.toString());
            htmlStringBuilder.append("</td>");
            htmlStringBuilder.append("<td>");
            htmlStringBuilder.append(String.valueOf(info.size));
            htmlStringBuilder.append("</td>");

            htmlStringBuilder.append("</tr>");
        }
        htmlStringBuilder.append("</table>");
    }
    private String getHyperlink(String path, String name) {
        return "<a href=\"" + path + "\">" + name + "</a>";
    }
    private void formHeader(String title) {
        htmlStringBuilder.append("<!DOCTYPE html>");
        htmlStringBuilder.append("<html>");
        htmlStringBuilder.append("<head>");
        htmlStringBuilder.append("<title>");
        htmlStringBuilder.append(title);
        htmlStringBuilder.append("</title>");
        htmlStringBuilder.append("</head>");
        htmlStringBuilder.append("<body>");
    }
    private void formFooter() {
        htmlStringBuilder.append("</body>");
        htmlStringBuilder.append("<html>");
        htmlStringBuilder.append("\n");
    }
}
