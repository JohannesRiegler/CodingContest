package SchoolCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level4_new {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("E:/Benutzer/jotsc/OneDrive - Schulzentrum Ybbs/3CHIT/SEW/Praxis/IdeaProjects/Schulübung/src/SchoolCC/" + "input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int[][] numbers1 = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                numbers1[i][j] = scanner.nextInt();
            }
        }
        int[][] numbers = numbers1;
        ArrayList<Square> squares = new ArrayList<Square>();
        for (int t = 0; t < 4; t++) {
            System.out.flush();
            Thread.sleep(500);
            final int finalT = t;
            new Thread(() -> {

                for (int i = finalT % 2 * r / 2; i < ((finalT % 2) + 1) * r / 2; i++) {

                    for (int j = finalT % 2 * c / 2; j < ((finalT) % 2 + 1) * c / 2; j++) {
                        System.out.println(i * 1000 + j);
                        for (int k = -1; k <= 1; k++) {

                            int height = numbers[i][j] + k;
                            int size = 2;
                            boolean f = true;

                            while (f) {
                                for (int x = size - 1, y = 0; y < size - 1; y++) {

                                    if (i + x >= r || j + y >= c) {
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
                                for (int x = 0, y = size - 1; x < size; x++) {
                                    if (f == false)
                                        break;
                                    if (i + x >= r || j + y >= c) {
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
                                size++;
                            }

                        }
                    }

                }
            }).run();

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
