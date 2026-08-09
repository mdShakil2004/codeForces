class Solution {
    public int zigzagSequence(int[][] mat) {
        int n = mat.length;

        int[] dp = new int[n];

        // Initialize first row
        for (int j = 0; j < n; j++) {
            dp[j] = mat[0][j];
        }

        // Process remaining rows
        for (int i = 1; i < n; i++) {

            int max1 = -1, max2 = -1, idx = -1;

            // Find largest and second largest in previous row
            for (int j = 0; j < n; j++) {
                if (dp[j] > max1) {
                    max2 = max1;
                    max1 = dp[j];
                    idx = j;
                } else if (dp[j] > max2) {
                    max2 = dp[j];
                }
            }

            int[] newDp = new int[n];

            for (int j = 0; j < n; j++) {
                if (j == idx)
                    newDp[j] = mat[i][j] + max2;
                else
                    newDp[j] = mat[i][j] + max1;
            }

            dp = newDp;
        }

        int ans = 0;
        for (int x : dp)
            ans = Math.max(ans, x);

        return ans;
    }
}
