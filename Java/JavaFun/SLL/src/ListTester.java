import java.util.Arrays;

public class ListTester {
    public static void main(String[] args) {
        SLL list1 = new SLL();
        list1.addFront(10);
        list1.addFront(9);
        list1.addBack(11);
        System.out.println("Full list");
        list1.print();

        System.out.println("Find 9: ");
        System.out.println(list1.find(9).getVal());
        //System.out.println("Find 1: ");
        //System.out.println(list1.find(1).getVal());

        System.out.println("2 nodes");
        list1.removeBack();
        list1.print();

        System.out.println("Empty list");
        list1.removeBack();
        list1.removeBack();
        list1.print();

        list1.addFront(10);
        list1.addFront(9);
        list1.addBack(11);
        list1.removeAt(2);
        System.out.println("After remove at 2");
        list1.print();

        list1.addBack(11);
        list1.removeAt(0);
        System.out.println("After remove at 0");
        list1.print();

        list1.addFront(9);
        list1.removeAt(1);
        System.out.println("After remove at 1");
        list1.print();
    }
}
