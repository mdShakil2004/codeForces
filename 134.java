class Solution {

    boolean solve(int[][] mat, int i, int j, int n, int[][] path, int[][] dp) {

        if (i >= n || j >= n || mat[i][j] == 0)
            return false;

        if (i == n - 1 && j == n - 1) {
            path[i][j] = 1;
            return true;
        }

        // already known dead state
        if (dp[i][j] == -1)
            return false;

        path[i][j] = 1;

        int jump = mat[i][j];

        for (int k = 1; k <= jump; k++) {

            // right first (required by statement)
            if (solve(mat, i, j + k, n, path, dp))
                return true;

            // then down
            if (solve(mat, i + k, j, n, path, dp))
                return true;
        }

        path[i][j] = 0;
        dp[i][j] = -1; // mark impossible

        return false;
    }

    public ArrayList<ArrayList<Integer>> shortestDist(int[][] mat) {

        int n = mat.length;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        if (mat[0][0] == 0) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(-1);
            ans.add(row);
            return ans;
        }

        int[][] path = new int[n][n];
        int[][] dp = new int[n][n];

        if (!solve(mat, 0, 0, n, path, dp)) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(-1);
            ans.add(row);
            return ans;
        }

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(path[i][j]);
            }
            ans.add(row);
        }

        return ans;
    }
}
