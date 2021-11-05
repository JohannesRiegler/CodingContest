package SchoolCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level3 {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("C:\\Users\\Clemens\\eclipse\\eclipse-workspace\\CCC\\School\\src\\" + "input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int[][] numbers = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                numbers[i][j] = scanner.nextInt();
            }
        }

        ArrayList<Square> squares = new ArrayList<Square>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                int height = numbers[i][j];
                int size = 2;
                boolean f = true;

                while (f) {
                    for (int x = 0; x < size; x++) {
                        for (int y = 0; y < size; y++) {

                            if (i + x == r || j + y == c) {
                                size--;
                                squares.add(new Square(i, j, size));
                                f = false;
                                break;
                            }

                            if (numbers[i + x][j + y] == height) {
                                continue;
                            } else {
                                size--;
                                squares.add(new Square(i, j, size));
                                f = false;
                                break;
                            }
                        }
                    }
                    size++;
                }
            }
        }

        int maxSize = 0;
        for (int i = 0; i < squares.size(); i++) {
            if (squares.get(i).size > maxSize) {
                maxSize = squares.get(i).size;
            }
        }

        for (int i = 0; i < squares.size(); i++) {
            if (squares.get(i).size != maxSize) {
                squares.remove(i);
                i--;
            }
        }

        System.out.println(maxSize);
        for (Square i : squares) {
            System.out.println(i);
        }
    }
}
