class Solution {
    public int getLastDigit(String a, String b) {
        // a^0 = 1
        if (b.equals("0")) return 1;

        int lastDigit = a.charAt(a.length() - 1) - '0';

        // Find b % 4
        int mod = 0;
        for (char ch : b.toCharArray()) {
            mod = (mod * 10 + (ch - '0')) % 4;
        }

        int exp = (mod == 0) ? 4 : mod;

        int ans = 1;
        for (int i = 0; i < exp; i++) {
            ans = (ans * lastDigit) % 10;
        }

        return ans;
    }
}
