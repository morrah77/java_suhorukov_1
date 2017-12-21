package com.jcourse.lazar.dirIndexGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class DirectoryParser {
    public static ArrayList<FileInfo> Parse(String dirPath) {
        ArrayList<FileInfo> resultList = new ArrayList<>();
        Path path = FileSystems.getDefault().getPath(dirPath);
        try {
            DirectoryStream directoryStream = Files.newDirectoryStream(path);
            for (Object dirItem: directoryStream) {
                Path item = ((Path) dirItem).getFileName();
                resultList.add(new FileInfo(item));
            }
            resultList.sort(new Comparator<FileInfo>() {
                @Override
                public int compare(FileInfo o1, FileInfo o2) {
                    return o1.name.compareTo(o2.name);
                }
            });
//            resultList.add(0, new FileInfo(path, ".."));
            FileInfo dirRootFileInfo = new FileInfo(path);
            dirRootFileInfo.name = "..";
            resultList.add(0, dirRootFileInfo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (Error e) {
            System.out.println(e);
        }
        return resultList;
    }

    public static class FileInfo {
        String name;
        String fullPath;
        FileTime modified;
        long size;
        boolean isDirectory;
        public FileInfo(Path item) {
            try {
                name = item.getFileName().toString();
                fullPath = item.normalize().toString();
                isDirectory = Files.isDirectory(item, LinkOption.NOFOLLOW_LINKS);
                modified = Files.getLastModifiedTime(item, LinkOption.NOFOLLOW_LINKS);
                if (!isDirectory) {
                    size = Files.size(item);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // TODO(h.lazar) make an alternative constructor!
        public FileInfo(Path item, String name) {
            FileInfo fileInfo = new FileInfo(item);
            fileInfo.name = name;
        }
    }
}
