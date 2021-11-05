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
            System.out.println("" + e.getKey() + ": " + e.getValue().toString());
        }

        ArrayList<int[]> outputs = new ArrayList<>();
        boolean tradesPossible = true;
        while(tradesPossible){
            tradesPossible = false;
            for (Map.Entry<Integer, ArrayList<Integer>> i : econBaskets.entrySet()) {
                ArrayList<Trade> trades = new ArrayList<>();
                for (Map.Entry<Integer, ArrayList<Integer>> j : econBaskets.entrySet()) {
                    if (Objects.equals(i.getKey(), j.getKey()))
                        continue;

                    for (Integer sweetI : i.getValue()) {
                        for (Integer sweetJ : j.getValue()) {
                            if (econSweetValues.get(i.getKey()).get(sweetI - 1) < econSweetValues.get(i.getKey()).get(sweetJ - 1))
                                if (econSweetValues.get(j.getKey()).get(sweetI - 1) > econSweetValues.get(j.getKey()).get(sweetJ - 1)) {
                                    trades.add(new Trade(j.getKey(), sweetI, sweetJ, econSweetValues.get(i.getKey()).get(sweetJ - 1) - econSweetValues.get(i.getKey()).get(sweetI - 1)));
                                }
                        }
                    }
                }

                if (trades.isEmpty()) {
                    outputs.add(new int[]{});
                    continue;
                }
                Collections.sort(trades, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade o1, Trade o2) {
                        return Integer.compare(o2.gain, o1.gain);
                    }
                });


                outputs.add(new int[]{i.getKey(), trades.get(0).mySweetType, trades.get(0).targetId, trades.get(0).targetSweetType});
            }
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
