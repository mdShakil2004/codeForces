class Solution {
    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        void update(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public int countSubstring(String s) {
        int n = s.length();

        // Prefix sums lie in [-n, n]
        int offset = n + 1;
        int size = 2 * n + 5;

        Fenwick ft = new Fenwick(size);

        int prefix = 0;
        long ans = 0;

        // Insert prefix sum = 0
        ft.update(offset, 1);

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1')
                prefix++;
            else
                prefix--;

            int idx = prefix + offset;

            // Count previous prefix sums < current prefix
            ans += ft.query(idx - 1);

            ft.update(idx, 1);
        }

        return (int) ans;
    }
}
