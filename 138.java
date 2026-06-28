class Solution {
    static final int MOD = 1000000007;

    public int countStrings(int n, int k) {

        long[][] prev = new long[k + 1][2];
        long[][] curr = new long[k + 1][2];

        prev[0][0] = 1;
        prev[0][1] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j <= k; j++) {
                curr[j][0] = (prev[j][0] + prev[j][1]) % MOD;

                curr[j][1] = prev[j][0];
                if (j > 0)
                    curr[j][1] = (curr[j][1] + prev[j - 1][1]) % MOD;
            }

            prev = curr;
            curr = new long[k + 1][2];
        }

        if (n == 1)
            return k == 0 ? 2 : 0;

        return (int)((prev[k][0] + prev[k][1]) % MOD);
    }
}
