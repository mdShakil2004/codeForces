import java.util.HashSet;

class Solution {
    public boolean isProduct(int[] arr, long target) {
        HashSet<Long> set = new HashSet<>();

        for (int num : arr) {
            // Handle target = 0 separately
            if (target == 0) {
                if (num == 0) {
                    // If there's already any element before 0,
                    // product can become 0
                    if (!set.isEmpty()) return true;
                } else {
                    // If 0 already exists
                    if (set.contains(0L)) return true;
                }
            } else {
                // target must be divisible by num
                if (num != 0 && target % num == 0) {
                    long needed = target / num;

                    if (set.contains(needed)) {
                        return true;
                    }
                }
            }

            set.add((long) num);
        }

        return false;
    }
}
