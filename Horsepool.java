import java.util.*;

public class Horsepool {

    static int horspool(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        Map<Character, Integer> shift = new HashMap<>();

        // Build shift table
        for (int i = 0; i < m - 1; i++) {
            shift.put(pattern.charAt(i), m - 1 - i);
        }

        int defaultShift = m;

        int i = m - 1;

        while (i < n) {
            int k = 0;

            while (k < m && pattern.charAt(m - 1 - k) == text.charAt(i - k)) {
                k++;
            }

            if (k == m) {
                return i - m + 1;
            }

            char current = text.charAt(i);
            i += shift.getOrDefault(current, defaultShift);
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text :");
        String text = sc.nextLine();
        System.out.println("Enter the pattern");
        String pattern = sc.nextLine();

        int result = horspool(text, pattern);

        if (result != -1)
            System.out.println("Pattern found at index: " + result);
        else
            System.out.println("Pattern not found");
    }
}