package com.a1qa;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseLines {
    final static String encoding = "ISO-8859-1";
        final static String ending = "_res";

    public static void main(String[] args) throws IOException {
            int amount = args.length == 2 ? Integer.parseInt(args[1]) : 10;
            StringBuffer newFileName = new StringBuffer(args[0]);
            newFileName.insert(newFileName.lastIndexOf("."), ending);

            List<String> originalList = Files.readAllLines(Paths.get(args[0]), Charset.forName(encoding));
            String header = originalList.remove(0);
            originalList.sort((a, b) -> (Math.random() - 0.5) < 0 ? -1 : 1);

            List<String> newList = Collections.synchronizedList(new ArrayList(originalList.subList(0, amount)));
            newList.add(0, header);
            Files.write(Paths.get(newFileName.toString()), newList);

            originalList.removeAll(newList);
            originalList.add(0, header);
            Files.write(Paths.get(args[0]), originalList);

            System.out.println(newFileName);
    }
}