package School;

import common.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level2 extends Level {
    public static void main(String[] args) throws IOException {
        ArrayList<File> files = getAllMatchingFiles("level1");
        for (File file : files) {
            Scanner scanner = getScanner(file);
            if (scanner == null) return;
            FileWriter fileWriter = getFileWriter(file.getName().replaceFirst("\\.in", ".out"));

            calculateValues(scanner, fileWriter);

            fileWriter.flush();
            fileWriter.close();
        }

    }

    private static void calculateValues(Scanner scanner, FileWriter outputFileWriter) throws IOException {
        int gameCount = scanner.nextInt();
        int playerCount = scanner.nextInt();
        ArrayList<Integer[]> inputs = new ArrayList<>();

        while (scanner.hasNext()) {
            inputs.add(new Integer[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()});
        }

        for (Integer[] value : inputs) {
            outputFileWriter.append("" + value[0] + " " + value[1]);
        }
    }
}
