

import java.util.*;

public class ArrayColoring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            
            // Find sorted position for each element
            int mismatchCount = 0;
            
            for (int i = 0; i < n; i++) {
                // Count how many elements are smaller than a[i]
                // This gives us the sorted position
                int sortedPos = 0;
                for (int j = 0; j < n; j++) {
                    if (a[j] < a[i]) {
                        sortedPos++;
                    }
                }
                
                // Check if parities match
                if ((i % 2) != (sortedPos % 2)) {
                    mismatchCount++;
                }
            }
            
            // Valid if all match or all mismatch
            boolean valid = (mismatchCount == 0 || mismatchCount == n);
            System.out.println(valid ? "YES" : "NO");
        }
        
        sc.close();
    }
}