import java.util.*;

public class nextFreqGreater {
    public ArrayList<Integer> nextFreqGreater(int[] arr) {
        
        int n = arr.length;   // find number of elements in array
        
        
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, -1));
         System.out.println(result); 

        // Step 1: Frequency map
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        // Step 2: Stack to store indices
        Stack<Integer> stack = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() &&
                   freq.get(arr[stack.peek()]) <= freq.get(arr[i])) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result.set(i, arr[stack.peek()]);
            }

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 1, 2, 3, 4, 2, 1};
        ArrayList<Integer> res = sol.nextFreqGreater(arr);
        System.out.println(res); // Output: [2, 2, 1, 2, 2, 1, -1]
    }
}
