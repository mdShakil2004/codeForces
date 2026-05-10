import java.util.*;

class Solution {

    static class Task {
        int aProfit, bProfit, diff;

        Task(int a, int b) {
            this.aProfit = a;
            this.bProfit = b;
            this.diff = Math.abs(a - b);
        }
    }

    public int maxProfit(int x, int y, int[] a, int[] b) {

        int n = a.length;

        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(a[i], b[i]);
        }

        // Sort by absolute difference descending
        Arrays.sort(tasks, (t1, t2) -> t2.diff - t1.diff);

        int profit = 0;

        for (Task t : tasks) {

            // Prefer Machine A
            if ((t.aProfit >= t.bProfit && x > 0) || y == 0) {
                profit += t.aProfit;
                x--;
            }
            else {
                profit += t.bProfit;
                y--;
            }
        }

        return profit;
    }
}
