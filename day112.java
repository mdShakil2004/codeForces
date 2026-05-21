class Solution {
    public boolean isBitSet(int n) {
        // 0 should return false
        if (n == 0) return false;

        // Check if n is of the form 2^k - 1
        return (n & (n + 1)) == 0;
    }
}
