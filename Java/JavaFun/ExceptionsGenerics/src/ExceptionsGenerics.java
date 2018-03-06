import java.util.ArrayList;

public class ExceptionsGenerics {
    public static void main(String[] args) {
        ArrayList<Object> myList = new ArrayList<Object>();
        myList.add("13");
        myList.add("hello world");
        myList.add(48);
        myList.add("Goodbye World");

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
            try {
                Integer castedValue = (Integer) myList.get(i);
            }
            catch (ClassCastException e) {
                System.out.println("Error on index " + i);
            }
        }

        ArrayList<Integer> myIntList = new ArrayList<Integer>();
        myIntList.add("13");
        myIntList.add("hello world");
        myIntList.add(48);
        myIntList.add("Goodbye World");

        for (int i = 0; i < myIntList.size(); i++) {
            System.out.println(myIntList.get(i));
            Integer castedValue = (Integer) myIntList.get(i);
        }
    }
}
