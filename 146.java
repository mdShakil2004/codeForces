import java.util.*;

class Solution {
    public int largestArea(int n, int m, int[][] arr) {
        int k = arr.length;

        int[] rows = new int[k];
        int[] cols = new int[k];

        for (int i = 0; i < k; i++) {
            rows[i] = arr[i][0];
            cols[i] = arr[i][1];
        }

        Arrays.sort(rows);
        Arrays.sort(cols);

        int maxRows = maxGap(rows, n);
        int maxCols = maxGap(cols, m);

        return maxRows * maxCols;
    }

    private int maxGap(int[] blocked, int limit) {
        if (blocked.length == 0) return limit;

        int max = blocked[0] - 1; // before first blocked
        for (int i = 1; i < blocked.length; i++) {
            max = Math.max(max, blocked[i] - blocked[i - 1] - 1);
        }
        max = Math.max(max, limit - blocked[blocked.length - 1]); // after last blocked

        return max;
    }
}
