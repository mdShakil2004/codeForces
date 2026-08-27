import java.util.*;

class Solution {
    public String compress(String s) {
        int n = s.length();

        // Build KMP LPS array
        int[] lps = new int[n];

        for (int i = 1; i < n; i++) {
            int j = lps[i - 1];

            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = lps[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        Stack<Character> stack = new Stack<>();

        // Process prefixes from right to left
        for (int i = n - 1; i > 0; i--) {
            int len = i + 1;

            // A prefix must have even length to be split into
            // two identical halves.
            if (len % 2 != 0) {
                stack.push(s.charAt(i));
                continue;
            }

            int border = lps[i];
            int period = len - border;

            boolean canCompress =
                border * 2 >= len &&
                len % period == 0 &&
                (len / period) % 2 == 0;

            if (canCompress) {
                // Replace the second half by '*'
                stack.push('*');

                // Continue with the first half
                i = len / 2;
            } else {
                stack.push(s.charAt(i));
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(s.charAt(0));

        // We processed backwards, so restore the order
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.toString();
    }
}
