package Classic;

import common.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Level2 extends Level {
    enum Statement {
        PRINT, ELSE, RETURN, IF
    }

    public static void main(String[] args) throws IOException {
        ArrayList<File> files = getAllMatchingFiles("level2");
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
        int loc = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> inputs = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            inputs.addAll(List.of(line.split(" ")));
        }

        boolean lastIf = false;
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            switch (input) {
                case "print":
                    fw.append(inputs.get(i + 1));
                case "if":
                    if (inputs.get(i + 1) == "false") {
                        for (int j = i + 2; j < inputs.size(); j++) {
                            if (inputs.get(j) == "end") {
                                i = ++j;
                                if (inputs.get(j) == "else")
                                    i++;
                                break;
                            }

                        }
                    } else {
                        i++;
                    }
                    break;
                case "else":
                    for (int j = i + 1; j < inputs.size(); j++) {
                        if (Objects.equals(inputs.get(j), "end")) {
                            i = j;
                            break;
                        }
                    }
                    break;
                case "return":
                    return;
            }
        }


    }
}
