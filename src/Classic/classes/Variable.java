package Classic.classes;

import java.util.Objects;

public class Variable {
    private String value;
    public String name;

    public Variable( String name, String value) {
        this.name = name;
        this.value = value;
    }
    public Variable( String name) {
        this.value = "";
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getType(){
        if(Objects.equals(value, "true") || Objects.equals(value, "false"))
            return "boolean";
        try {
            Integer.parseInt(value);
            return "number";
        } catch (NumberFormatException e) {
            return "string";
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Variable{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
