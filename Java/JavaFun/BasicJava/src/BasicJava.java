public class BasicJava {
    public void to255() {
        for (int i = 1; i < 256; i++) {
            System.out.println(i);
        }
    }

    public void oddTo255() {
        for (int i = 1; i < 256; i+=2) {
            System.out.println(i);
        }
    }

    public void sumTo255() {
        int sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i;
            System.out.println("New number: " + i + " Sum: " + sum);
        }
    }

    public void printArr(int[] myArray) {
        for(int value : myArray) {
           System.out.println(value);
        }
    }

    public void printMax(int[] myArray) {
        if (myArray.length > 0) {
            int max = myArray[0];
            for (int value : myArray) {
                if (value > max) {
                    max = value;
                }
            }
            System.out.println(max);
        } else {
            System.out.println("");
        }
    }

    public void printAverage(int[] myArray) {
        int sum = 0;
        for (int value : myArray) {
            sum += value;
        }
        System.out.println(sum/(double)myArray.length);
    }

    public int[] oddNumberArrayTo255() {
        int[] oddArray = new int[255/2 + 1];
        for (int i = 1; i < 256; i+= 2) {
            oddArray[i/2] = i;
        }
        for (int value : oddArray) {
            System.out.println(value);
        }
        return oddArray;
    }

    public int greaterThanY(int[] arr, int y) {
        int count = 0;
        for(int value : arr) {
            if (value > y) {
                count++;
            }
        }
        return count;
    }

    public void squareValues(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] *= arr[i];
        }
    }

    public void eliminateNegative(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Math.max(0, arr[i]);
        }
    }

    public double[] maxMinAverage(int[] arr) {
        double[] returnArr = {0,0,0};
        for(int value : arr) {
            returnArr[0] = Math.max(returnArr[0], value);
            returnArr[1] = Math.min(returnArr[1], value);
            returnArr[2] += value;
        }
        returnArr[2] = returnArr[2]/(double)arr.length;
        return returnArr;
    }

    public void shiftValues(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = 0;
    }
}
