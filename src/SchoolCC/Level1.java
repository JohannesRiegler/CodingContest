package SchoolCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Level1 {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("C:\\Users\\Clemens\\eclipse\\eclipse-workspace\\CCC\\School\\src\\" + "input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        int len = scanner.nextInt();

        int flatLen1 = 1;
        int flatLen2 = 1;
        int flatHeight1 = 0;
        int flatHeight2 = 0;
        int flatPos1 = 0;
        int flatPos2 = 0;
        int lastNum = 0;
        boolean flat1 = false;
        int i = -1;

        while (scanner.hasNext()) {
            int z = scanner.nextInt();
            i++;

            if (z == lastNum) {
                if (flat1 == false) {
                    if (flatPos1 == 0) {
                        flatPos1 = i;
                    }
                    flatLen1++;
                    flatHeight1 = z;
                    System.out.println(flatHeight1);
                } else {
                    if (flatPos2 == 0) {
                        flatPos2 = i;
                    }
                    flat1 = true;
                    flatLen2++;
                    flatHeight2 = z;
                }
            } else {
                if (flatPos1 != 0) {
                    flat1 = true;
                }
                if (flatLen1 < flatLen2) {

                    flatLen1 = flatLen2;
                    flatPos1 = flatPos2;
                    flatHeight1 = flatHeight2;
                    flatPos2 = 0;
                    flatLen2 = 1;
                } else if (flatLen1 == flatLen2 && flatHeight1 > flatHeight2) {
                    flatLen1 = flatLen2;
                    flatPos1 = flatPos2;
                    flatHeight1 = flatHeight2;
                    flatPos2 = 0;
                    flatLen2 = 1;
                }
                flatPos2 = 0;
                flatLen2 = 1;
            }

            lastNum = z;
        }
        if (flatLen1 < flatLen2) {

            flatLen1 = flatLen2;
            flatPos1 = flatPos2;
            flatHeight1 = flatHeight2;
            flatPos2 = 0;
            flatLen2 = 1;
        } else if (flatLen1 == flatLen2 && flatHeight1 > flatHeight2) {
            flatLen1 = flatLen2;
            flatPos1 = flatPos2;
            flatHeight1 = flatHeight2;
            flatPos2 = 0;
            flatLen2 = 1;
        }
        System.out.println("i: " + i);

        System.out.println("flat: " + flatLen1 + "|" + "pos: " + (flatPos1 - 1) + "| height:" + flatHeight1);

        scanner.close();
    }
}
