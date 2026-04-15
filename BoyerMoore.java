import java.util.*;

public class BoyerMoore {

    static int[] buildBadCharTable(String pattern) {
        int[] badChar = new int[256];
        int m = pattern.length();

        Arrays.fill(badChar, -1);

        for (int i = 0; i < m; i++) {
            badChar[pattern.charAt(i)] = i;
        }

        return badChar;
    }

    static int search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        int[] badChar = buildBadCharTable(pattern);

        int shift = 0;

        while (shift <= (n - m)) {
            int j = m - 1;

            // Compare from right to left
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            // Match found
            if (j < 0) {
                return shift;
            } else {
                // Bad character rule
                shift += Math.max(1, j - badChar[text.charAt(shift + j)]);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text:");
        String text = sc.nextLine();
        System.out.println("Enter the pattern :");
        String pattern = sc.nextLine();

        int result = search(text, pattern);

        if (result != -1)
            System.out.println("Pattern found at index: " + result);
        else
            System.out.println("Pattern not found");
    }
}