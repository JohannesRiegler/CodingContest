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
                if (!file.getName().endsWith("example.in"))
                    continue;
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
        int curFunction = -1;
        boolean returns = false;
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
//            System.out.println(i+ ": " + input + " " + inputs.get(i+1));
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
                            throw new Exception("df");
                        }
                        variables.add(new Variable(name, value));
                        System.out.println(variables.toString());
                        break;
                    }

                    case "set": {
                        String name = inputs.get(++i);
                        String value = inputs.get(++i);
                        if (variables.contains(new Variable(value))) {
                            Variable var = variables.get(variables.indexOf(new Variable(value)));
                            value = var.getValue();
                        }
                        if (!variables.contains(new Variable(name))) {
                            throw new Exception("sdf");
                        } else {
                            variables.get(variables.indexOf(new Variable(name))).setValue(value);
                        }
//                        System.out.println("set: " +variables.toString());
                        break;
                    }
                    case "print": {
                        String nextValue = inputs.get(++i);
                        if (variables.contains(new Variable(nextValue))) {
                            Variable var = variables.get(variables.indexOf(new Variable(nextValue)));
                            nextValue = var.getValue();
//                        System.out.println(var);
                        }
                        functionOutputs.set(curFunction, functionOutputs.get(curFunction).concat(nextValue));
                        break;
                    }

                    case "if": {
                        String nextValue = inputs.get(++i);
                        String ifValue = nextValue;
                        if (variables.contains(new Variable(nextValue))) {
                            Variable var = variables.get(variables.indexOf(new Variable(nextValue)));
                            if (!Objects.equals(var.getType(), "boolean"))
                                throw new Exception();
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
                        break;
                    }

                    case "else":
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
                        break;
                    case "return":
                        returns = true;
//                        System.out.println(inputs.subList(i + 1, inputs.size() - 1).toString());
                        i++;
                        break;
                    default:
//                        System.out.println("NOT: " + input);
                }
                if (returns) {
                    i += nextStart(inputs.subList(i + 1, inputs.size() - 1));
                }

            } catch (Exception e) {
                System.out.println(functionOutputs.set(curFunction, "ERROR"));
                curFunction++;
                if (i < inputs.size() - 1) {
                    functionOutputs.add("");
                    i += nextStart(inputs.subList(i, inputs.size() - 1));
                }
                variables.clear();
            }
        }
        for (String functionOutput : functionOutputs) {
            fw.append(functionOutput + "\n");
        }


    }

    private static int nextStart(List<String> subList) {
        System.out.println(subList.toString());
        System.out.println(subList.indexOf("start"));
        if (subList.size() == 0)
            return 0;
        int index = subList.indexOf("start");
        return index != -1 ? index : subList.size()-1;
    }


}
