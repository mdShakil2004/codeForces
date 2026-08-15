class Solution {
    public int countWithout(int n, int d) {
        if (n == 0) return 0;

        String s = String.valueOf(n);
        int len = s.length();

        // dp[i] = number of i-digit strings where each digit != d
        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1] * 9;
        }

        int ans = 0;

        // Count numbers having fewer digits than n
        for (int digits = 1; digits < len; digits++) {
            // First digit: 1-9, excluding d
            int firstChoices = 9 - (d != 0 ? 1 : 0);
            ans += firstChoices * dp[digits - 1];
        }

        // Count numbers having same number of digits as n
        for (int i = 0; i < len; i++) {
            int current = s.charAt(i) - '0';

            // Digits smaller than current
            for (int digit = (i == 0 ? 1 : 0); digit < current; digit++) {
                if (digit != d) {
                    ans += dp[len - i - 1];
                }
            }

            // If n itself contains d, stop
            if (current == d) {
                return ans;
            }
        }

        // n itself doesn't contain d
        return ans + 1;
    }
}
