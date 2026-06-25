class Solution {
    public static ArrayList<Integer> increasingNumbers(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        if (n == 1) {
            for (int i = 0; i <= 9; i++) {
                ans.add(i);
            }
            return ans;
        }
        
        // No strictly increasing n-digit number exists for n >= 10
        if (n > 9) return ans;
        
        generate(n, 1, 0, ans);
        return ans;
    }
    
    private static void generate(int n, int startDigit, int num,
                                 ArrayList<Integer> ans) {
        if (n == 0) {
            ans.add(num);
            return;
        }
        
        for (int d = startDigit; d <= 9; d++) {
            generate(n - 1, d + 1, num * 10 + d, ans);
        }
    }
}
