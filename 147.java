import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int countCoordinates(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] p = new boolean[n][m];
        boolean[][] q = new boolean[n][m];

        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> qq = new LinkedList<>();

        // Station P (top row)
        for (int j = 0; j < m; j++) {
            if (!p[0][j]) {
                p[0][j] = true;
                pq.offer(new int[]{0, j});
            }
        }

        // Station P (left column)
        for (int i = 0; i < n; i++) {
            if (!p[i][0]) {
                p[i][0] = true;
                pq.offer(new int[]{i, 0});
            }
        }

        // Station Q (bottom row)
        for (int j = 0; j < m; j++) {
            if (!q[n - 1][j]) {
                q[n - 1][j] = true;
                qq.offer(new int[]{n - 1, j});
            }
        }

        // Station Q (right column)
        for (int i = 0; i < n; i++) {
            if (!q[i][m - 1]) {
                q[i][m - 1] = true;
                qq.offer(new int[]{i, m - 1});
            }
        }

        bfs(mat, pq, p);
        bfs(mat, qq, q);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (p[i][j] && q[i][j]) ans++;
            }
        }

        return ans;
    }

    private void bfs(int[][] mat, Queue<int[]> queue, boolean[][] vis) {
        int n = mat.length;
        int m = mat[0].length;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                    !vis[nr][nc] &&
                    mat[nr][nc] >= mat[r][c]) {

                    vis[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
