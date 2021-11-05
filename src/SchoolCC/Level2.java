package SchoolCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Level2 {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("C:\\Users\\Clemens\\eclipse\\eclipse-workspace\\CCC\\School\\src\\" + "input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        int len = scanner.nextInt();
        int flatlen = 1;
        int flatlenmax = 0;
        int posmax = 0;
        int pos = 0;
        int thisInt = 0, nextInt = 0;

        for (int i = 0; i < len; i++) {
            if (i == 0) thisInt = scanner.nextInt();
            if (i < len - 1) nextInt = scanner.nextInt();

            if (nextInt == thisInt) {
                if (i - 1 != pos) pos = i;
                flatlen++;
            } else {
                if (flatlen > flatlenmax) {
                    flatlenmax = flatlen + 2;
                    posmax = pos + 2;
                    flatlen = 1;
                }
            }
            nextInt = thisInt;
        }

        System.out.print(posmax + " " + flatlenmax);
        scanner.close();
    }
}
