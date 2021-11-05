package common;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Level {
    public static final String BASE_INPUT_PATH = System.getProperty("user.dir") + "/src/School/in/";
    public static final String BASE_OUTPUT_PATH = System.getProperty("user.dir") + "/src/School/out/";

    public static Scanner getScanner(File fileToScan) {
        Scanner scanner;
        try {
            scanner = new Scanner(fileToScan);
            scanner.useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
        return scanner;
    }

    public static FileWriter getFileWriter(String OutputFileName) throws IOException {
        return new FileWriter(BASE_OUTPUT_PATH + OutputFileName);

    }
    public static ArrayList<File> getAllMatchingFiles(String startsWith) {
        return getAllMatchingFiles(startsWith, ".in");
    }
    public static ArrayList<File> getAllMatchingFiles(String startsWith, String endsWith ) {
        File dir = new File(BASE_INPUT_PATH);
        ArrayList<File> fileArrayList = new ArrayList<>();
        if (!dir.isDirectory()) throw new IllegalStateException("wtf mate?");
        for (File file : dir.listFiles()) {
            if (file.getName().startsWith(startsWith) && file.getName().endsWith(endsWith))
                fileArrayList.add(file);
        }
        return fileArrayList;
    }
}
