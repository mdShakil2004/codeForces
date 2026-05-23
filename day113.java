class Solution {
    int cntOnes(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        // Traverse all boundary cells
        for (int i = 0; i < n; i++) {
            // First column
            if (grid[i][0] == 1 && !vis[i][0]) {
                dfs(grid, vis, i, 0, n, m);
            }

            // Last column
            if (grid[i][m - 1] == 1 && !vis[i][m - 1]) {
                dfs(grid, vis, i, m - 1, n, m);
            }
        }

        for (int j = 0; j < m; j++) {
            // First row
            if (grid[0][j] == 1 && !vis[0][j]) {
                dfs(grid, vis, 0, j, n, m);
            }

            // Last row
            if (grid[n - 1][j] == 1 && !vis[n - 1][j]) {
                dfs(grid, vis, n - 1, j, n, m);
            }
        }

        // Count enclosed 1s
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    void dfs(int[][] grid, boolean[][] vis, int r, int c, int n, int m) {

        // Boundary check
        if (r < 0 || c < 0 || r >= n || c >= m) {
            return;
        }

        // Skip water or visited cells
        if (grid[r][c] == 0 || vis[r][c]) {
            return;
        }

        vis[r][c] = true;

        // 4 directions
        dfs(grid, vis, r + 1, c, n, m);
        dfs(grid, vis, r - 1, c, n, m);
        dfs(grid, vis, r, c + 1, n, m);
        dfs(grid, vis, r, c - 1, n, m);
    }
}
