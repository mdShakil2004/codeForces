class Solution {
    public ArrayList<Integer> optimalArray(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();

        long prefix = 0;
        long[] pref = new long[n];

        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            pref[i] = prefix;
        }

        for (int i = 0; i < n; i++) {
            int m = i / 2;

            long leftSum = pref[m];
            long leftCost = 1L * arr[m] * (m + 1) - leftSum;

            long rightSum = pref[i] - pref[m];
            long rightCost = rightSum - 1L * arr[m] * (i - m);

            ans.add((int)(leftCost + rightCost));
        }

        return ans;
    }
}
