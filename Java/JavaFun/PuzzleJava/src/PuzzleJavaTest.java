import java.util.Arrays;
import java.util.ArrayList;

public class PuzzleJavaTest {
    public static void main(String[] args) {
        int[] arr1 = {3, 5, 1, 2, 7, 9, 8, 13, 25, 32};
        ArrayList<Integer> list1 = PuzzleJava.greaterThan10(arr1);
        System.out.println(Arrays.toString(list1.toArray()));

        String[] strArray = {"Nancy", "Jinichi", "Fujibayashi", "Momochi", "Ishikawa"};
        System.out.println(Arrays.toString(PuzzleJava.shuffleReturnLong(strArray).toArray()));

        Character[] alphabet = new Character[26];
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            alphabet[ch-'a'] = ch;
        }
        PuzzleJava.shuffleAlpha(alphabet);

        System.out.println(Arrays.toString(PuzzleJava.randomNumArray()));

        PuzzleJava.randomSorted();

        System.out.println(PuzzleJava.randomString());

        System.out.println(Arrays.toString(PuzzleJava.randomStrings()));
    }
}
