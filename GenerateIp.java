import java.util.*;

class Solution {
    public ArrayList<String> generateIp(String s) {
        ArrayList<String> res = new ArrayList<>();
        int n = s.length();

        for (int i = 1; i <= 3 && i < n; i++) {
            for (int j = i + 1; j <= i + 3 && j < n; j++) {
                for (int k = j + 1; k <= j + 3 && k < n; k++) {

                    String a = s.substring(0, i);
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k);

                    if (valid(a) && valid(b) && valid(c) && valid(d)) {
                        res.add(a + "." + b + "." + c + "." + d);
                    }
                }
            }
        }

        return res;
    }

    private boolean valid(String s) {
        if (s.length() == 0 || s.length() > 3) return false;
        if (s.length() > 1 && s.charAt(0) == '0') return false;

        int num = Integer.parseInt(s);
        return num <= 255;
    }
}
