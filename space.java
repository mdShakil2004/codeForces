import java.util.*;
import java.io.*;

public class Solution {
    static long n, c;
    static long[] a, b;
    
    static long calculateDays(long x) {
        long total = n;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) continue;
            // Avoid overflow: if a[i] * x would overflow, then a[i]*x/b[i] is huge
            if (x > Long.MAX_VALUE / a[i]) {
                return Long.MAX_VALUE;
            }
            total += (a[i] * x) / b[i];
            if (total > c) return Long.MAX_VALUE; // Early exit
        }
        return total;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        n = Long.parseLong(firstLine[0]);
        c = Long.parseLong(firstLine[1]);
        
        a = new long[(int)n];
        b = new long[(int)n];
        
        boolean allZero = true;
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            a[i] = Long.parseLong(line[0]);
            b[i] = Long.parseLong(line[1]);
            if (a[i] > 0) allZero = false;
        }
        
        // Special case: all a[i] = 0
        if (allZero) {
            if (c == n) {
                System.out.println(-1);
            } else {
                System.out.println(0);
            }
            return;
        }
        
        // Binary search for minimum x
        long left = 1, right = 2000000000L;
        long xMin = -1;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long days = calculateDays(mid);
            if (days >= c) {
                if (days == c) xMin = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        if (xMin == -1) {
            System.out.println(0);
            return;
        }
        
        // Binary search for maximum x
        left = xMin;
        right = 2000000000L;
        long xMax = xMin;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long days = calculateDays(mid);
            if (days <= c) {
                if (days == c) xMax = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(xMax - xMin + 1);
    }
}