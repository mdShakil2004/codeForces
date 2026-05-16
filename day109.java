class Solution {
    public int optimalKeys(int n) {
        
        int[] dp = new int[n + 1];
        
        // Base cases
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // pressing 'A' every time
        }
        
        // Compute optimal values
        for (int i = 7; i <= n; i++) {
            
            for (int b = i - 3; b >= 1; b--) {
                
                int curr = dp[b] * (i - b - 1);
                
                dp[i] = Math.max(dp[i], curr);
            }
        }
        
        return dp[n];
    }
}
