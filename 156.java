import java.util.*;

class Solution {
    public boolean canRepresentBST(List<Integer> arr) {
        Stack<Integer> st = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int val : arr) {
            // Current value violates BST property
            if (val < lowerBound)
                return false;

            // Move to right subtree
            while (!st.isEmpty() && val > st.peek()) {
                lowerBound = st.pop();
            }

            st.push(val);
        }

        return true;
    }
}
