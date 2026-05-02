class Solution {
    static int toggleBits(int n, int l, int r) {
        // Step 1: create mask with bits set from l to r
        int mask = ((1 << r) - 1) ^ ((1 << (l - 1)) - 1);
        
        // Step 2: toggle using XOR
        return n ^ mask;
      
    }
}
