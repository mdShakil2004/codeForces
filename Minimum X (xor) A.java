// Minimum X (xor) A


class Solution {
    public static int minVal(int a, int b) {
        int k = Integer.bitCount(b); // number of set bits needed
        int x = 0;

        // Step 1: match bits with 'a' from MSB to LSB
        for (int i = 31; i >= 0; i--) {
            if (((a >> i) & 1) == 1 && k > 0) {
                x |= (1 << i);
                k--;
            }
        }

        // Step 2: fill remaining bits from LSB to MSB
        for (int i = 0; i <= 31 && k > 0; i++) {
            if (((x >> i) & 1) == 0) {
                x |= (1 << i);
                k--;
            }
        }

        return x;
    }
}
