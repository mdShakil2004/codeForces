class Solution {
    public int minSwaps(int[] arr) {
        int n = arr.length;

        // Step 1: Count total 1s
        int totalOnes = 0;
        for (int num : arr) {
            if (num == 1) totalOnes++;
        }

        // Step 2: Edge case
        if (totalOnes == 0) return -1;

        // Step 3: Sliding window
        int windowOnes = 0;

        // First window
        for (int i = 0; i < totalOnes; i++) {
            if (arr[i] == 1) windowOnes++;
        }

        int maxOnes = windowOnes;

        // Slide the window
        for (int i = totalOnes; i < n; i++) {
            if (arr[i] == 1) windowOnes++;
            if (arr[i - totalOnes] == 1) windowOnes--;

            maxOnes = Math.max(maxOnes, windowOnes);
        }

        // Step 4: Result
        return totalOnes - maxOnes;
    }
}
