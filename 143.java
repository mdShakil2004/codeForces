import java.util.HashMap;

class Solution {
    public int maxCharGap(String s) {
        HashMap<Character, Integer> first = new HashMap<>();
        int maxGap = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (first.containsKey(ch)) {
                maxGap = Math.max(maxGap, i - first.get(ch) - 1);
            } else {
                first.put(ch, i);
            }
        }

        return maxGap;
    }
}
