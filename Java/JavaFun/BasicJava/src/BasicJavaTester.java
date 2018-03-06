import java.util.Arrays;

public class BasicJavaTester {
    public static void main(String[] args) {
//        BasicJava basic = new BasicJava();
//
//        // Print 1-255
//        basic.to255();
//
//        // Print odd 1-255
//        basic.oddTo255();
//
//        // Print sum
//        basic.sumTo255();
//
//        // Iterate array
//        int[] arr1 = {1,2,3,4,5};
//        int[] arr2 = {};
//        int[] arr3 = {1,3,5,7,9,13};
//        basic.printArr(arr1);
//        basic.printArr(arr2);
//        basic.printArr(arr3);
//
//        // Find Max
//        int[] arr4 = {-3,-5,-7};
//        basic.printMax(arr1);
//        basic.printMax(arr2);
//        basic.printMax(arr3);
//        basic.printMax(arr4);
//        int[] arr5 = {-3,-5,-7, 5, 0};
//        basic.printMax(arr5);
//
//        // Get Average
//        int[] arr6 = {2,10,3};
//        System.out.println("Average");
//        basic.printAverage(arr1); // 3
//        basic.printAverage(arr2); // null
//        basic.printAverage(arr3); // 6.333
//        basic.printAverage(arr4); // -5
//        basic.printAverage(arr5); // -2
//        basic.printAverage(arr6); // 5
//
//        // Array w/ odd numbers
//        System.out.println("odd to 255 array");
//        basic.oddNumberArrayTo255(); //
//
//        // Greater than Y
//        System.out.println("greater than Y");
//        System.out.println(basic.greaterThanY(arr1, 4)); // 1
//        System.out.println(basic.greaterThanY(arr1, 5)); // 0
//        System.out.println(basic.greaterThanY(arr4, -7)); // 2
//        System.out.println(basic.greaterThanY(arr3, 1)); // 5
//
//        // Square values
//        System.out.println("Square values");
//        basic.squareValues(arr6);
//        for(int value : arr6) {
//            System.out.println(value);
//        }
//        int[] arr7 = {1,5,10,-2};
//        basic.squareValues(arr7);
//        System.out.println(Arrays.toString(arr7));
//
//        // Eliminate negatives
//        basic.eliminateNegative(arr1);
//        System.out.println(Arrays.toString(arr1));
//        basic.eliminateNegative(arr5);
//        System.out.println(Arrays.toString(arr5));
//        int[] arr8 = {1,5,10,-2};
//        basic.eliminateNegative(arr8);
//        System.out.println(Arrays.toString(arr8));
//
//        // Max, min, average
//        int[] arr9 = {1,5,10,-2};
//        System.out.println(Arrays.toString(basic.maxMinAverage(arr9))); // [5, -2, 3.5]
//
//        // Shift values
//        basic.shiftValues(arr9);
//        System.out.println(Arrays.toString(arr9));

        Basic13.print1to255();

        Basic13.printAndSumto255();

        Basic13.printOddTo255();

        int[] arr1 = {1,2,3,4,5};
        Basic13.printArr(arr1);

        Basic13.maxArray(arr1);

        Basic13.printAvg(arr1);

        System.out.println(Arrays.toString(Basic13.oddArray()));

        Basic13.squareArr(arr1);
        System.out.println(Arrays.toString(arr1));

        System.out.println(Basic13.greaterThanY(arr1,3));

        int[] arr2 = {-2,3,5,-7};
        System.out.println(Arrays.toString(Basic13.zeroNegative(arr2)));

        System.out.println(Arrays.toString(Basic13.shiftLeft(arr2)));

        int[] arr3 = {-2,3,5,-7};
        System.out.println(Arrays.toString(Basic13.swapNegative(arr3)));
    }
}
