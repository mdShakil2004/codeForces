import java.util.*;

class Solution {
    public boolean isPossible(int[] arr, int s, int x) {
        if (x == 0) return true;

        ArrayList<Long> seq = new ArrayList<>();
        long sum = s;
        seq.add((long) s);

        for (int v : arr) {
            long next = sum + v;
            seq.add(next);
            sum = sum + next;   // = 2*sum + v
        }

        long target = x;

        for (int i = seq.size() - 1; i >= 0; i--) {
            long val = seq.get(i);
            if (val <= target) {
                target -= val;
            }
        }

        return target == 0;
    }
}
