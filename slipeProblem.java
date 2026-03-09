class Solution {
    public String largestSwap(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Record last occurrence of each digit
        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            last[arr[i] - '0'] = i;
        }

        // Traverse left to right
        for (int i = 0; i < n; i++) {
            int curr = arr[i] - '0';

            // Look for a bigger digit (9 down to curr+1)
            for (int d = 9; d > curr; d--) {
                if (last[d] > i) { // found a better swap
                    // swap arr[i] with rightmost d
                    char temp = arr[i];
                    arr[i] = arr[last[d]];
                    arr[last[d]] = temp;
                    return new String(arr);
                }
            }
        }

        // already lexicographically largest
        return s;
    }
}
