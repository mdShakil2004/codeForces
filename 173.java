class Solution {
    public ArrayList<Integer> findWays(int[][] grid) {
        int n = grid.length;
        int MOD = 1000000007;

        long[][] ways = new long[n][n];
        int[][] best = new int[n][n];

        // Destination
        ways[n - 1][n - 1] = 1;
        best[n - 1][n - 1] = grid[n - 1][n - 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (i == n - 1 && j == n - 1)
                    continue;

                long cnt = 0;
                int mx = Integer.MIN_VALUE;

                // Right move
                if ((grid[i][j] == 1 || grid[i][j] == 3) && j + 1 < n) {
                    if (ways[i][j + 1] > 0) {
                        cnt = (cnt + ways[i][j + 1]) % MOD;
                        mx = Math.max(mx, grid[i][j] + best[i][j + 1]);
                    }
                }

                // Down move
                if ((grid[i][j] == 2 || grid[i][j] == 3) && i + 1 < n) {
                    if (ways[i + 1][j] > 0) {
                        cnt = (cnt + ways[i + 1][j]) % MOD;
                        mx = Math.max(mx, grid[i][j] + best[i + 1][j]);
                    }
                }

                ways[i][j] = cnt;
                if (cnt > 0) {
                    best[i][j] = mx;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add((int) (ways[0][0] % MOD));
        ans.add(best[0][0]);
        return ans;
    }
}
