class Solution {
    static final int MOD = 1000000007;

    public int countWays(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (i < m) {
                dp[i] = 1;
            } else if (i == m) {
                dp[i] = 2;
            } else {
                dp[i] = (int) (((long) dp[i - 1] + dp[i - m]) % MOD);
            }
        }

        return dp[n];
    }
}
