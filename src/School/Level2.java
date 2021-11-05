package School;

import common.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Level2 extends Level {
    public static void main(String[] args) throws IOException {
        ArrayList<File> files = getAllMatchingFiles("level2");
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
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            int id = Integer.parseInt(strings[0]);
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 1; i < strings.length; i++) {
                if (!strings[i].isEmpty()) {
                    int val = Integer.parseInt(strings[i]);
                    arrayList.add(val);
                }
            }
            int minVal = Collections.min(arrayList);
            int maxVal = Collections.max(arrayList);
            ArrayList<Integer> ar = new ArrayList<Integer>();
            ar.add(minVal);
            ar.add(maxVal);
            map.put(id, ar);

        }

        ArrayList<int[]> outputs = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Integer>> i : map.entrySet()) {
            int highId = Integer.MAX_VALUE;
            int highVal = 0;
            for (Map.Entry<Integer, ArrayList<Integer>> j : map.entrySet()) {
                if (Objects.equals(i.getKey(), j.getKey()))
                    continue;

                if(highVal < j.getValue().get(1)){
                    highId = j.getKey();
                    highVal= j.getValue().get(1);
                }
            }

            int myLowest = i.getValue().get(0);
            if(myLowest < highVal)
                outputs.add(new int[]{i.getKey(), myLowest, highId, highVal});
            else
                outputs.add(new int[]{});
        }

        for (int[] output : outputs) {

            if(output.length == 0)
                fw.append("NO TRADE");
            else{
                String line = "";
                for (int i : output) {
                    line = line.concat(i+" ");
                }
                line = line.trim();
                fw.append(line);
            }
            fw.append("\n");
        }
    }
}
