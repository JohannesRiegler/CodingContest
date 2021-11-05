package ClassicCC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Level1 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("E:/Benutzer/jotsc/OneDrive - Schulzentrum Ybbs/3CHIT/SEW/Praxis/IdeaProjects/Schulübung/src/SchoolCC/" + "input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        //insert Code here
    }
}
