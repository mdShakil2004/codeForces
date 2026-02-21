

import java.util.*;
public class Prefix_Max {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            
            long best = Long.MIN_VALUE;
            // try all swaps including i==j (no swap)
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    // swap
                    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                    // compute value
                    long val = 0, mx = 0;
                    for (int k = 0; k < n; k++) {
                        if (a[k] > mx) mx = a[k];
                        val += mx;
                    }
                    if (val > best) best = val;
                    // swap back
                    tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                }
            }
            System.out.println(best);
        }
    }
}