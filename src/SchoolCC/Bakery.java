package SchoolCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bakery {

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("E:/Benutzer/jotsc/OneDrive - Schulzentrum Ybbs/3CHIT/SEW/Praxis/IdeaProjects/Schulübung/src/CCC/" + "input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        int[] umsatz = new int[1000];
        ArrayList<ArrayList<Integer>> einzahlung = new ArrayList<>();

        for (int i = 0; i < umsatz.length; i++) {
            umsatz[i] = 0;
            einzahlung.add(new ArrayList<Integer>());
        }

        int n = 0;
        int[] sorted = new int[1000];


        for (int i = 0; scanner.hasNext(); i++) {
            String str = scanner.next().trim();
            int tag = scanner.nextInt();
            int val = scanner.nextInt();

            if (str.equals("F")) {
                umsatz[tag - 1] = val;
            } else if (str.equals("B")) {
                einzahlung.get(n).add(tag);
                einzahlung.get(n).add(val);
                sorted[i] = val;
                n++;
            }

        }
        Arrays.sort(sorted);

        int sum = 0;
        for (int i = 0; i < umsatz.length; i++) {

            for (int j = i; j < einzahlung.size(); j++) {
                for (int k = 0; k < einzahlung.get(j).size(); k++) {
                    if (sum + einzahlung.get(j).get(2) == umsatz[i]) {
                        break;
                    } else {
                        sum = 0;
                    }
                }
            }

        }
    }

    public static void write(ArrayList<ArrayList<Integer>> list) {

    }

}