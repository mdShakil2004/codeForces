class Solution {
    public int maxProfit(int[] arr, int k) {
        int n = arr.length;
        
        int hold = -arr[0];  // Buying first stock
        int cash = 0;        // No stock initially
        
        for (int i = 1; i < n; i++) {
            int prevCash = cash;
            
            // Sell stock today
            cash = Math.max(cash, hold + arr[i] - k);
            
            // Buy stock today
            hold = Math.max(hold, prevCash - arr[i]);
        }
        
        return cash;
    }
}
