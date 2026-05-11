import java.util.HashMap;

class Solution {

    // Check palindrome in substring
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        }
        return true;
    }

    public boolean palindromePair(String[] arr) {

        HashMap<String, Integer> map = new HashMap<>();

        // Store string with index
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {

            String word = arr[i];
            int len = word.length();

            // Try every split
            for (int j = 0; j <= len; j++) {

                String prefix = word.substring(0, j);
                String suffix = word.substring(j);

                // Case 1:
                // prefix palindrome
                if (isPalindrome(prefix, 0, prefix.length() - 1)) {

                    String revSuffix =
                            new StringBuilder(suffix).reverse().toString();

                    if (map.containsKey(revSuffix) &&
                        map.get(revSuffix) != i) {
                        return true;
                    }
                }

                // Case 2:
                // suffix palindrome
                // j != len avoids duplicate checking
                if (j != len &&
                    isPalindrome(suffix, 0, suffix.length() - 1)) {

                    String revPrefix =
                            new StringBuilder(prefix).reverse().toString();

                    if (map.containsKey(revPrefix) &&
                        map.get(revPrefix) != i) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
