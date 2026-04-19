class Solution {
    public boolean isPower(int x, int y) {
        if (y == 1) return true;   // x^0 = 1
        if (x == 1) return y == 1; // only 1^k = 1
        
        long power = 1;
        
        while (power < y) {
            power *= x;
        }
        
        return power == y;
    }
}
