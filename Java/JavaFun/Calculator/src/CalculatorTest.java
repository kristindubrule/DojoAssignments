public class CalculatorTest {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        c.setOperandOne(10.5);
        c.setOperation('+');
        c.setOperandTwo(5.2);
        c.performOperation();
        System.out.println(c.getResult());

        c.setOperandOne(12);
        c.setOperation('-');
        c.setOperandTwo(3);
        c.performOperation();
        System.out.println(c.getResult());

        Calculator c2 = new Calculator();
        c2.addComponent(10.5);
        c2.addComponent("+");
        c2.addComponent(5.2);
        c2.addComponent("=");
        System.out.println(c2.getResult());

        c2.addComponent(10.5);
        c2.addComponent("+");
        c2.addComponent(5.2);
        c2.addComponent("*");
        c2.addComponent(10);
        c2.addComponent("=");
        System.out.println(c2.getResult());

        c2.addComponent(10.5);
        c2.addComponent("+");
        c2.addComponent(5.2);
        c2.addComponent("*");
        c2.addComponent(-10);
        c2.addComponent("=");
        System.out.println(c2.getResult());
    }
}
