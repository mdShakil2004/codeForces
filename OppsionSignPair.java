import java.util.*;

class Solution {
    public ArrayList<Integer> reducePairs(int[] arr) {
        Stack<Integer> st = new Stack<>();
        
        for (int num : arr) {
            boolean added = false;
            
            while (!st.isEmpty() && st.peek() * num < 0) {
                int top = st.peek();
                
                if (Math.abs(top) > Math.abs(num)) {
                    // current number gets destroyed
                    added = true;
                    break;
                } else if (Math.abs(top) < Math.abs(num)) {
                    // stack top gets destroyed
                    st.pop();
                } else {
                    // equal magnitude → both removed
                    st.pop();
                    added = true;
                    break;
                }
            }
            
            if (!added) {
                st.push(num);
            }
        }
        
        return new ArrayList<>(st);
    }
}
