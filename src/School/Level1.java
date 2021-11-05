package School;

import common.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Level1 extends Level {
    public static void main(String[] args) throws IOException {
        ArrayList<File> files = getAllMatchingFiles("level1");
        for (File file : files) {
            Scanner scanner = getScanner(file);
            System.out.println(file.getName());
            if (scanner == null) return;
            FileWriter fileWriter = getFileWriter(file.getName().replaceFirst("\\.in", ".out"));

            calculateValues(scanner, fileWriter);

            fileWriter.flush();
            fileWriter.close();
        }

    }

    private static void calculateValues(Scanner scanner, FileWriter fw) throws IOException {
        int amount = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Integer[]> inputs = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            int sum = 0;
            int id = Integer.parseInt(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                System.out.println(strings[i]);
                if (!strings[i].isEmpty())
                    sum += Integer.parseInt(strings[i]);
            }
            map.put(id, sum);

        }

        for (Integer value : map.values()) {
            fw.append("" + value + "\n");
        }
    }
}
