import java.util.*;

class Solution {
    public ArrayList<Integer> find3Numbers(int[] arr) {
        int n = arr.length;
        
        int[] smaller = new int[n];
        int[] greater = new int[n];
        
        Arrays.fill(smaller, -1);
        Arrays.fill(greater, -1);
        
        // Step 1: Fill smaller[]
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] <= arr[minIndex]) {
                minIndex = i;
            } else {
                smaller[i] = minIndex;
            }
        }
        
        // Step 2: Fill greater[]
        int maxIndex = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[maxIndex]) {
                maxIndex = i;
            } else {
                greater[i] = maxIndex;
            }
        }
        
        // Step 3: Find valid triplet
        for (int i = 0; i < n; i++) {
            if (smaller[i] != -1 && greater[i] != -1) {
                ArrayList<Integer> result = new ArrayList<>();
                result.add(arr[smaller[i]]);
                result.add(arr[i]);
                result.add(arr[greater[i]]);
                return result;
            }
        }
        
        return new ArrayList<>(); // no subsequence found
    }
}
