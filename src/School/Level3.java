package School;

import common.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Level3 extends Level {
    public static void main(String[] args) throws IOException {
        ArrayList<File> files = getAllMatchingFiles("level3");
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
        int econAmount = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Integer[]> inputs = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> econBaskets = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> econSweetValues = new HashMap<>();
        for (int i = 1; i <= econAmount; i++) {
            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            int id = Integer.parseInt(strings[0]);
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 1; j < strings.length; j++) {
                if (!strings[j].isEmpty()) {
                    int val = Integer.parseInt(strings[j]);
                    arrayList.add(val);
                }
            }
            Collections.sort(arrayList);
            econBaskets.put(id, arrayList);
            econSweetValues.put(id, new ArrayList<Integer>());
        }

        int sweetAmount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= sweetAmount; i++) {
            int type = scanner.nextInt();
            for (int j = 1; j <= econAmount; j++) {
                int val = scanner.nextInt();
                econSweetValues.get(j).add(val);
            }
        }

        for (Map.Entry<Integer, ArrayList<Integer>> e : econSweetValues.entrySet()) {
            System.out.println(""+e.getKey() + ": " + e.getValue().toString());
        }

        ArrayList<int[]> outputs = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Integer>> i : econBaskets.entrySet()) {
            int highId = Integer.MAX_VALUE;
            int highVal = 0;
            int myLowVal = 0;
            int myLowType = Integer.MAX_VALUE;
            HashMap<Integer, Trade> myBestTrades = new HashMap<>();
            for (Integer integer : i.getValue()) {
                int sub = econSweetValues.get(i.getKey()).get(integer);

                if (myLowVal > sub) {
                    myLowVal = sub;
                    myLowType = integer;
                }
            }

            for (Map.Entry<Integer, ArrayList<Integer>> j : econBaskets.entrySet()) {
                if (Objects.equals(i.getKey(), j.getKey()))
                    continue;

                for (Integer integer : j.getValue()) {
                    int sub = econSweetValues.get(j.getKey()).get(integer);

                    if (highVal < sub) {
                        highVal = sub;
                        highId = integer;
                    }
                }
            }

            int myLowest = i.getValue().get(0);
            if (myLowest < highVal)
                outputs.add(new int[]{i.getKey(), myLowest, highId, highVal});
            else
                outputs.add(new int[]{});
        }



        for (int[] output : outputs) {

            if (output.length == 0)
                fw.append("NO TRADE");
            else {
                String line = "";
                for (int i : output) {
                    line = line.concat(i + " ");
                }
                line = line.trim();
                fw.append(line);
            }
            fw.append("\n");
        }
    }
}
