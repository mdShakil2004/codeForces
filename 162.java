class Solution {
    static final int MOD = 1000000007;

    public int countSubsets(int[] arr) {

        int[] primes = {2,3,5,7,11,13,17,19,23,29};

        // mask[i] = prime mask of i, -1 if not square-free
        int[] mask = new int[31];

        for (int num = 2; num <= 30; num++) {
            int x = num;
            int m = 0;
            boolean ok = true;

            for (int i = 0; i < 10; i++) {
                int p = primes[i];
                int cnt = 0;
                while (x % p == 0) {
                    x /= p;
                    cnt++;
                }
                if (cnt > 1) {
                    ok = false;
                    break;
                }
                if (cnt == 1) m |= (1 << i);
            }

            mask[num] = ok ? m : -1;
        }

        long[] dp = new long[1 << 10];
        dp[0] = 1;

        int ones = 0;

        for (int x : arr) {
            if (x == 1) {
                ones++;
                continue;
            }

            if (mask[x] == -1) continue;

            int cur = mask[x];

            for (int s = (1 << 10) - 1; s >= 0; s--) {
                if ((s & cur) == 0) {
                    dp[s | cur] = (dp[s | cur] + dp[s]) % MOD;
                }
            }
        }

        long pow = 1;
        while (ones-- > 0)
            pow = (pow * 2) % MOD;

        long ans = 0;

        // Exclude empty mask
        for (int s = 1; s < (1 << 10); s++) {
            ans = (ans + dp[s]) % MOD;
        }

        ans = (ans * pow) % MOD;

        return (int) ans;
    }
}
