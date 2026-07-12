import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int maxAmount(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int tickets : arr) {
            maxHeap.offer(tickets);
        }

        long ans = 0;
        int MOD = 1000000007;

        while (k > 0 && !maxHeap.isEmpty()) {
            int curr = maxHeap.poll();

            ans = (ans + curr) % MOD;

            if (curr > 1) {
                maxHeap.offer(curr - 1);
            }

            k--;
        }

        return (int) ans;
    }
}
