package ClassicCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Level3 {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("E:/Benutzer/jotsc/OneDrive - Schulzentrum Ybbs/3CHIT/SEW/Praxis/IdeaProjects/Schulübung/src/ClassicCC/"
                    + "level3_example.in"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        /*
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        ArrayList<ArrayList<Cell>> countries = new ArrayList<ArrayList<Cell>>();
        int[][] cells = new int[r][c];
        int[][] countofc = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int cell = scanner.nextInt();
                int country = scanner.nextInt();
                cells[i][j] = cell;
                countofc[i][j] = country;

                if (countries.indexOf(country) == -1)
                    countries.add(country);
                countries.get(country).add(new Cell(i, j));
            }*/
    }

}