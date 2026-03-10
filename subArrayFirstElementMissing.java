// subArrayFirstElementMissing.java    

import java.util.*;

class Solution {
    public int countSubarrays(int[] arr) {
        int n = arr.length;
        int[] nextSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Find next smaller element for each index
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nextSmaller[i] = n;
            } else {
                nextSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            count += nextSmaller[i] - i;
        }

        return count;
    }
}
