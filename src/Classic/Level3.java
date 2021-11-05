package Classic;

import Classic.classes.Variable;
import common.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Level3 extends Level {
    enum Statement {
        IF("if"), ELSE("else"), PRINT("print"), RETURN("return"), START("start"), END("end");

        Statement(String s) {
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<File> files = getAllMatchingFiles("level3");
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

    private static void calculateValues(Scanner scanner, FileWriter fw) throws Exception {
        int loc = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> inputs = new ArrayList<>();
        ArrayList<Variable> variables = new ArrayList<>();
        ArrayList<String> functionOutputs = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            inputs.addAll(List.of(line.split(" ")));
        }

        ArrayList<Boolean> ifValues = new ArrayList<>();
        int curFunction = 0;
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            try {
                switch (input) {
                    case "start":
                        curFunction++;
                        functionOutputs.add("");
                        variables.clear();
                        break;
                    case "var": {
                        String name = inputs.get(++i);
                        String value = inputs.get(++i);

                        if (variables.contains(new Variable(name))) {
                            throw new Exception();
                        }
                        variables.add(new Variable(value, name));
                        break;
                    }

                    case "set": {
                        String name = inputs.get(++i);
                        String value = inputs.get(++i);
                        if (!variables.contains(new Variable(name))) {
                            throw new Exception();
                        } else {
                            variables.get(variables.indexOf(new Variable(name))).setValue(value);
                        }
                    }
                    case "print":
                        functionOutputs.get(curFunction).concat(inputs.get(++i));
                        break;
                    case "if":
                        if (Objects.equals(inputs.get(++i), "false")) {
                            ifValues.add(false);
                            for (int j = ++i; j < inputs.size(); j++) {
                                if (Objects.equals(inputs.get(j), "end")) {
                                    i = j;
                                    break;
                                }
                            }
                        } else {
                            ifValues.add(true);
                        }
                        break;
                    case "else":
                        if (!ifValues.get(ifValues.size()-1)) {
                            for (int j = i + 1; j < inputs.size(); j++) {
                                if (Objects.equals(inputs.get(j), "end")) {
                                    i = j;
                                    break;
                                }
                            }

                        }
                        int index = ifValues.size() - 1;
                        ifValues.remove(index);
                        break;
                    case "return":
                        return;
                    default:
                        System.out.println("NOT: " + input);
                }

            } catch (Exception e) {
                if (i <= inputs.size() - 1)
                    functionOutputs.add("");
                functionOutputs.set(curFunction, "ERROR");
                curFunction++;
                variables.clear();
                i += nextStart(inputs.subList(i, inputs.size() - 1));

            }
        }

        for (String functionOutput : functionOutputs) {
            fw.append(functionOutput + "\n");
        }


    }

    private static int nextStart(List<String> subList) {
        for (int i = 1; i < subList.size(); i++) {
            if (Objects.equals(subList.get(i - 1), "start"))
                return i;
        }
        return subList.size();
    }


}
