package com.a1qa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastCreatedFile {
    static final int maxDifference = 10000;

    public static void main(String[] args) {
        File dir = new File(args[0]);
        File[] allFiles = dir.listFiles();
        ArrayList<File> files = new ArrayList<>();
        Pattern pattern = Pattern.compile(".+" + args[1]);

        for (File file: allFiles) {
            Matcher m = pattern.matcher(file.toString());
            if (m.matches()) {
                files.add(file);
            }
        }

        switch (files.size()) {
            case 0:
                System.out.println("empty");
                break;
            case 1:
            System.out.println(files.get(0));
            break;
            default :
            findLastCreated(files);
        }

    }

    public static void findLastCreated(ArrayList <File> files) {
        files.sort((File o1, File o2) -> {
            BasicFileAttributes att1 = null;
            BasicFileAttributes att2 = null;
            try {
                att1 = Files.readAttributes(o1.toPath(), BasicFileAttributes.class);
                att2 = Files.readAttributes(o2.toPath(), BasicFileAttributes.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return att2.creationTime().toMillis() < att1.creationTime().toMillis() ? -1 : 1;
        });

        System.out.println(files.get(0));
        System.out.println("--------------------");

        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(files.get(0).toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < files.size(); i++) {
            BasicFileAttributes atToCompare = null;
            try {
                atToCompare = Files.readAttributes(files.get(i).toPath(), BasicFileAttributes.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (attr.creationTime().toMillis() - atToCompare.creationTime().toMillis() > maxDifference)  {
                break;
            }
            System.out.println(files.get(i));
        }

    }
}