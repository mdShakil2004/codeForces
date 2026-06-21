class Solution {
    public String chooseSwap(String s) {
        int n = s.length();

        int[] first = new int[26];
        for (int i = 0; i < 26; i++) {
            first[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (first[idx] == -1) {
                first[idx] = i;
            }
        }

        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int cur = arr[i] - 'a';

            // Try to find a smaller character that occurs later
            for (int ch = 0; ch < cur; ch++) {
                if (first[ch] > i) {
                    char c1 = arr[i];
                    char c2 = (char) ('a' + ch);

                    for (int j = 0; j < n; j++) {
                        if (arr[j] == c1) {
                            arr[j] = c2;
                        } else if (arr[j] == c2) {
                            arr[j] = c1;
                        }
                    }
                    return new String(arr);
                }
            }
        }

        return s;
    }
}
