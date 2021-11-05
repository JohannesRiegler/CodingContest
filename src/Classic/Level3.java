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
        try {
            for (File file : files) {
//                if (!file.getName().endsWith("example.in"))
//                    continue;
                Scanner scanner = getScanner(file);
                System.out.println(file.getName());
                if (scanner == null) return;
                FileWriter fileWriter = getFileWriter(file.getName().replaceFirst("\\.in", ".out"));
                calculateValues(scanner, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class errorException extends Exception {

    }

    private static void calculateValues(Scanner scanner, FileWriter fw) throws Exception {
        int loc = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> inputs1 = new ArrayList<>();
        ArrayList<Variable> variables = new ArrayList<>();
        ArrayList<String> functionOutputs = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            inputs1.addAll(List.of(line.split(" ")));
        }

        ArrayList<Boolean> ifValues = new ArrayList<>();
        ArrayList<ArrayList<String>> functions = new ArrayList<>();
        for (int i = 0; i < inputs1.size(); i++) {
            String input = inputs1.get(i);
            if (Objects.equals(input, "start")) {
                functions.add(new ArrayList<>());
                continue;
            }
            if (Objects.equals(input, "end") && i < inputs1.size() - 1 && Objects.equals(inputs1.get(i + 1), "start")) {
                functions.add(new ArrayList<>());
                i++;
                continue;
            }
            functions.get(functions.size() - 1).add(input);
        }
        for (int curFunction = 0; curFunction < functions.size(); curFunction++) {
            ArrayList<String> inputs = functions.get(curFunction);
            boolean breakFunction = false;
            variables.clear();
            functionOutputs.add("");
            for (int i = 0; i < inputs.size(); i++) {
                String input = inputs.get(i);
                try {
                    switch (input) {
                        case "var" -> {
                            String name = inputs.get(++i);
                            String value = inputs.get(++i);

                            if (variables.contains(new Variable(name))) {
                                throw new errorException();
                            }
                            variables.add(new Variable(name, value));
                        }
                        case "set" -> {
                            String name = inputs.get(++i);
                            String value = inputs.get(++i);
                            if (variables.contains(new Variable(value))) {
                                Variable var = variables.get(variables.indexOf(new Variable(value)));
                                value = var.getValue();
                            }
                            if (!variables.contains(new Variable(name))) {
                                throw new errorException();
                            } else {
                                variables.get(variables.indexOf(new Variable(name))).setValue(value);
                            }
                            //                        System.out.println("set: " +variables.toString());
                        }
                        case "print" -> {
                            String nextValue = inputs.get(++i);
                            if (variables.contains(new Variable(nextValue))) {
                                Variable var = variables.get(variables.indexOf(new Variable(nextValue)));
                                nextValue = var.getValue();
                                //                        System.out.println(var);
                            }
                            functionOutputs.set(curFunction, functionOutputs.get(curFunction).concat(nextValue));
                        }
                        case "if" -> {
                            String nextValue = inputs.get(++i);
                            String ifValue = nextValue;
                            if (variables.contains(new Variable(nextValue))) {
                                Variable var = variables.get(variables.indexOf(new Variable(nextValue)));
                                if (!Objects.equals(var.getType(), "boolean"))
                                    throw new errorException();
                                else
                                    ifValue = var.getValue();
                            }

                            if (ifValue.equals("false")) {
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
                        }
                        case "else" -> {
                            if (ifValues.size() == 0) {
                                System.out.println(i + ": " + inputs.subList(i - 5, i));
                            }
                            if (ifValues.get(ifValues.size() - 1)) {
                                for (int j = i + 1; j < inputs.size(); j++) {
                                    if (Objects.equals(inputs.get(j), "end")) {
                                        i = j;
                                        break;
                                    }
                                }

                            }
                            int index = ifValues.size() - 1;
                            ifValues.remove(index);
                        }
                        case "return" -> breakFunction = true;
                        default -> {
                            System.out.println("NOT: " + input);
                        }
                    }
                    if (breakFunction) {
                        break;
                    }
                } catch (Exception e) {
                    functionOutputs.set(curFunction, "ERROR");
                    break;
                }
            }

        }
        for (String functionOutput : functionOutputs) {
            fw.append(functionOutput + "\n");
        }
    }




}
