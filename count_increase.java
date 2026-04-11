class Solution {
    public int countIncreasing(int[] arr) {
        int n = arr.length;
        int len = 1;
        int ans = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                len++;
            } else {
                ans += (len * (len - 1)) / 2;
                len = 1;
            }
        }

        // last streak
        ans += (len * (len - 1)) / 2;

        return ans;
    }
}
