class Solution {
    public int coin(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        // Continue removing coins until one remains
        while (left < right) {
            // Players pick the larger coin from the two ends
            if (arr[left] >= arr[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Last remaining coin
        return arr[left];
    }
}
