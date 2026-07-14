class Solution {
    public int find(int[] arr) {
        long need = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            need = (need + arr[i] + 1) / 2; // ceil((need + arr[i]) / 2)
        }

        return (int) need;
    }
}
