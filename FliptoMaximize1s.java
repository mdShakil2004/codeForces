// Flip to Maximize 1s


class Solution {
    int maxOnes(int[] arr) {
        int n = arr.length;
        
        int totalOnes = 0;
        for (int x : arr) {
            if (x == 1) totalOnes++;
        }
        
        int maxGain = 0;
        int currentGain = 0;
        
        for (int i = 0; i < n; i++) {
            int val = (arr[i] == 0) ? 1 : -1;
            
            currentGain = Math.max(val, currentGain + val);
            maxGain = Math.max(maxGain, currentGain);
        }
        
        return totalOnes + maxGain;
    }
}
