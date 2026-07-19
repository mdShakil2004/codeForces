class Solution {
    public ArrayList<Boolean> processQueries(int[] arr, int[][] queries) {
        int n = arr.length;

        int[] inc = new int[n];
        int[] dec = new int[n];

        // inc[i] = farthest index till which array is non-decreasing
        inc[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] <= arr[i + 1])
                inc[i] = inc[i + 1];
            else
                inc[i] = i;
        }

        // dec[i] = leftmost index from which array is non-increasing till i
        dec[0] = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] >= arr[i])
                dec[i] = dec[i - 1];
            else
                dec[i] = i;
        }

        ArrayList<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            ans.add(inc[l] >= dec[r]);
        }

        return ans;
    }
}
