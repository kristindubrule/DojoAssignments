public class Calculator implements java.io.Serializable {

    private double operandOne;
    private double operandTwo;
    private double result;
    private char operation;

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

}
