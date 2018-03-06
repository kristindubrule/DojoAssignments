import java.lang.String;

public class Basic13 {

    public static void print1to255() {
        for(int i = 1; i < 256; i++) {
            System.out.println(i);
        }
    }

    public static void printAndSumto255() {
        int sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i;
            System.out.println("Integer " + i + " Sum " + sum);
        }
    }

    public static void maxArray(int[] arr) {
        int max = arr[0];
        for(int value : arr) {
            max = Math.max(max, value);
        }
        System.out.println(max);
    }

    public static int[] oddArray() {
        int[] oddArr = new int[255/2 + 1];
        for(int i = 1; i < 256; i+=2) {
            oddArr[i/2] = i;
        }
        return oddArr;
    }

    public static int greaterThanY(int[] arr, int y) {
        int count = 0;
        for(int value : arr) {
            if (value > y) {
                count++;
            }
        }
        return count;
    }

    public static void maxMinAvg(int[] arr) {
        int sum = 0;
        int min = arr[0];
        int max = arr[0];
        for(int value : arr) {
            sum += value;
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        System.out.println("Max " + max + " Min " + min + " Average " + sum/(double)arr.length);
    }

    public static String[] swapNegative(int[] arr) {
        String[] returnArr = new String[arr.length];
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                returnArr[i] = "Dojo";
            }
            else {
                returnArr[i] = Integer.toString(arr[i]);
            }
        }
        return returnArr;
    }

    public static void printOddTo255() {
        for(int i = 1; i < 256; i+=2) {
            System.out.println(i);
        }
    }

    public static void printArr(int[] arr) {
        for(int value : arr) {
            System.out.println(value);
        }
    }

    public static void printAvg(int[] arr) {
        int sum = 0;
        for(int value : arr) {
            sum += value;
        }
        System.out.println(sum / (double)arr.length);
    }

    public static int[] squareArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] *= arr[i];
        }
        return arr;
    }

    public static int[] zeroNegative(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = 0;
            }
        }
        return arr;
    }

    public static int[] shiftLeft(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            arr[i-1] = arr[i];
        }
        arr[arr.length-1] = 0;
        return arr;
    }
}
