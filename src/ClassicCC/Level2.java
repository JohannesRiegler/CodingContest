package ClassicCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader(
                    "E:/Benutzer/jotsc/OneDrive - Schulzentrum Ybbs/3CHIT/SEW/Praxis/IdeaProjects/Schulübung/src/ClassicCC/" +
                            "level2_example.in"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int[][] cells = new int[r][c];
        int[][] country = new int[r][c];
        ArrayList<Integer> countries = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> countriesN = new ArrayList<ArrayList<Integer>>();
        int k = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cells[i][j] = scanner.nextInt();
                country[i][j] = scanner.nextInt();

                if (countries.indexOf(country[i][j]) == -1) {
                    k++;
                    countries.add(country[i][j]);
                }
            }
        }
        countries = new ArrayList<Integer>();

        for (int i = 0; i < k; i++) {

            countriesN.add(countries);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //System.out.println(country[i][j]);
                if (i == 0 || j == 0) {
                    countriesN.get(country[i][j]).add((i * c + j));
                } else if (i > 0 && country[i - 1][j] != country[i][j]) {
                    if (countriesN.get(country[i][j]).indexOf((i - 1) * c + j) == -1)
                        countriesN.get(country[i][j]).add((i - 1) * c + j);
                } else if (j < (c - 1) && country[i][j + 1] != country[i][j]) {
                    if (countriesN.get(country[i][j]).indexOf((i) * c + (j + 1)) == -1)
                        countriesN.get(country[i][j]).add((i) * c + (j + 1));
                } else if (j > 0 && country[i][j - 1] != country[i][j]) {
                    if (countriesN.get(country[i][j]).indexOf((i) * c + (j - 1)) == -1)
                        countriesN.get(country[i][j]).add((i) * c + (j - 1));
                } else if (i < (r - 1) && country[i + 1][j] != country[i][j]) {
                    if (countriesN.get(country[i][j]).indexOf((i + 1) * c + j) == -1)
                        countriesN.get(country[i][j]).add((i + 1) * c + j);
                }

            }

        }
        for (int i = 0; i < k; i++) {
            System.out.println(countriesN.get(i).size());
            System.out.println(countriesN.get(i));

        }
        FileWriter writer = new FileWriter("output.txt");
        writer.append("STRING");
        writer.close();
    }
}
