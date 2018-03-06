import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class PuzzleJava {
    public static ArrayList<Integer> greaterThan10(int[] arr) {
        int sum = 0;
        ArrayList<Integer> returnArray = new ArrayList<Integer>();

        for(int value : arr) {
            sum += value;
            if (value > 10) {
                returnArray.add(value);
            }
        }
        System.out.println("Sum " + sum);
        return returnArray;
    }

    private static <T>void shuffleArray(T[] arr) {
        int index;
        T temp;
        Random random = new Random();
        for (int i = arr.length-1; i > 0; i--) {
            index = random.nextInt(i+1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public static ArrayList<String> shuffleReturnLong(String[] arr) {
        PuzzleJava.shuffleArray(arr);

        ArrayList<String> longStr = new ArrayList<String>();
        for(String str : arr) {
            System.out.println(str);
            if (str.length() > 5) {
                longStr.add(str);
            }
        }
        return longStr;
    }

    public static void shuffleAlpha(Character[] char_array) {
        PuzzleJava.shuffleArray(char_array);
        System.out.println(char_array[char_array.length-1]);
        System.out.println(char_array[0]);
        if ("aeiou".indexOf(char_array[0]) >= 0) {
            System.out.println("First character is a vowel");
        }
    }

    public static int[] randomNumArray() {
        int[] randomArr = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(101-55) +55;
            randomArr[i] = number;
        }
        return randomArr;
    }

    public static int[] randomSorted() {
        int[] randomArr = randomNumArray();
        Arrays.sort(randomArr);
        System.out.println(Arrays.toString(randomArr));
        System.out.println("Smallest " + randomArr[0] + " Larger " + randomArr[randomArr.length-1]);
        return randomArr;
    }

    private static char rndChar () {
        int rnd = (int) (Math.random() * 52); // or use Random or whatever
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }

    public static String randomString() {
        String randomStr = new String();
        char[] randomChars = new char[5];
        for (int i = 0; i < randomChars.length; i++) {
            randomChars[i] = rndChar();
        }
        return new String(randomChars);
    }

    public static String[] randomStrings() {
        String[] randomStrings = new String[5];
        for (int i = 0; i < randomStrings.length; i++) {
            randomStrings[i] = randomString();
        }
        return randomStrings;
    }
}
