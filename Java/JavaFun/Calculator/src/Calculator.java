import java.util.ArrayList;
import java.io.*;

public class Calculator implements java.io.Serializable {

    private double operandOne;
    private double operandTwo;
    private double result;
    private char operation;
    private ArrayList<Object> components = new ArrayList<Object>();

    public Calculator() {
    }

    public double getOperandOne() {
        return operandOne;
    }

    public void setOperandOne(double operandOne) {
        this.operandOne = operandOne;
    }

    public double getOperandTwo() {
        return operandTwo;
    }

    public void setOperandTwo(double operandTwo) {
        this.operandTwo = operandTwo;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public ArrayList<Object> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Object> components) {
        this.components = components;
    }

    public void addComponent(double number) {
        components.add(number);
    }

    public void addComponent(String component) {
        if (component.equals("=")) {
            result = evaluateExpression(0,components.size()-1);
            components.clear();
        } else {
            components.add(component);
        }
    }

    public void performOperation() {
        if (operation == '-') {
            performSubtraction();
        } else if (operation == '+') {
            performAddition();
        }
    }

    private void performSubtraction() {
        result = operandOne - operandTwo;
    }

    private void performAddition() {
        result = operandOne + operandTwo;
    }

    private int indexSubarray(int start, int end, String search) {
        for (int i = end; i >= start; i--) {
            String component = components.get(i).toString();
            if (component == search) {
                return i;
            }
        }
        return -1;
    }

    private double evaluateExpression(int start, int end) {
        if (end == start) {
            String component = components.get(start).toString();
            return Double.parseDouble(component.replaceAll("\\s",""));
        } else {
            int index = indexSubarray(start, end, "+");
            if (index >= 0) {
                return evaluateExpression(start, index - 1) + evaluateExpression(index + 1, end);
            }

            index = indexSubarray(start, end, "-");
            if (index >= 0) {
                return evaluateExpression(start, index - 1) - evaluateExpression(index + 1, end);
            }

            index = indexSubarray(start, end, "*");
            if (index >= 0) {
                return evaluateExpression(start, index - 1) * evaluateExpression(index + 1, end);
            }

            index = indexSubarray(start, end, "/");
            if (index >= 0) {
                return evaluateExpression(start, index - 1) / evaluateExpression(index + 1, end);
            }
        }
        return 0;
    }
}
