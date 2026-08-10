class Solution {
    public int maxTask(int[] h, int[] l) {
        int n = h.length;

        if (n == 1)
            return Math.max(h[0], l[0]);

        int[] dp = new int[n];

        dp[0] = Math.max(h[0], l[0]);

        dp[1] = Math.max(dp[0] + l[1], h[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + l[i], dp[i - 2] + h[i]);
        }

        return dp[n - 1];
    }
}
