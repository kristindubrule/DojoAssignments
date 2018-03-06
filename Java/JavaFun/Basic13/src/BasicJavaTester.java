import java.util.Arrays;

public class BasicJavaTester {
    public static void main(String[] args) {
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
