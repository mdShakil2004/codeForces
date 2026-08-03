162.java

class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;

        // maxEndHere[i] = maximum subarray sum ending at i
        int[] maxEndHere = new int[n];
        maxEndHere[0] = arr[0];

        for (int i = 1; i < n; i++) {
            maxEndHere[i] = Math.max(arr[i], maxEndHere[i - 1] + arr[i]);
        }

        // Sum of first window of size k
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int ans = windowSum;

        // Process remaining windows
        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];

            // Window alone
            ans = Math.max(ans, windowSum);

            // Extend window with best subarray ending before it
            ans = Math.max(ans, windowSum + maxEndHere[i - k]);
        }

        return ans;
    }
}
