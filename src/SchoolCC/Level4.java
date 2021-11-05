package SchoolCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level4 {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("E:/Benutzer/jotsc/OneDrive - Schulzentrum Ybbs/3CHIT/SEW/Praxis/IdeaProjects/Schulübung/src/SchoolCC/" + "input.txt"));
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
        int height;
        int size;
        boolean f;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = -1; k <= 1; k++) {

                    height = numbers[i][j] + k;
                    size = 2;
                    f = true;

                    while (f) {
                        for (int x = 0; x < size; x++) {
                            for (int y = 0; y < size; y++) {

                                if (i + x == r || j + y == c) {
                                    size--;
                                    squares.add(new Square(i, j, size));
                                    f = false;
                                    break;
                                }

                                if ((numbers[i + x][j + y] == height) ||
                                        (numbers[i + x][j + y] == height - 1) ||
                                        (numbers[i + x][j + y] == height + 1)) {
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
