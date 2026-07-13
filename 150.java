class Solution {
    static final int MOD = 1000000007;

    public int minOperations(int[] b) {
        int n = b.length;
        boolean[] vis = new boolean[n];

        long lcm = 1;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int len = 0;
                int cur = i;

                while (!vis[cur]) {
                    vis[cur] = true;
                    cur = b[cur] - 1;   // convert to 0-based index
                    len++;
                }

                lcm = (lcm / gcd(lcm, len)) * len;
                lcm %= MOD;
            }
        }

        return (int) lcm;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
