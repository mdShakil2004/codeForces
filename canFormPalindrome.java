class Solution {
    boolean canFormPalindrome(String s) {
        int bitmask = 0;
        
        for (char c : s.toCharArray()) {
            int bit = c - 'a';
            bitmask ^= (1 << bit);
        }
        
        // Check if at most one bit is set
        return bitmask == 0 || (bitmask & (bitmask - 1)) == 0;
    }
}
