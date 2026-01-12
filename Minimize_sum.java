import java.util.*;
import java.io.*;

public class Minimize_sum {
    
    static long calculateSum(int[] a) {
        int n = a.length;
        long total = 0;
        int currMin = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            currMin = Math.min(currMin, a[i]);
            total += currMin;
        }
        
        return total;
    }
    
    static long solve(int n, int[] a) {
        // Try not performing any operation
        long best = calculateSum(a);
        
        // Try all possible operations: move a[j] to a[i] where i < j
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                // Create a copy and perform operation
                int[] temp = a.clone();
                temp[i] = a[i] + a[j];
                temp[j] = 0;
                
                // Calculate sum for this configuration
                long currSum = calculateSum(temp);
                best = Math.min(best, currSum);
            }
        }
        
        return best;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] tokens = br.readLine().split(" ");
            int[] a = new int[n];
            
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(tokens[i]);
            }
            
            pw.println(solve(n, a));
        }
        
        pw.flush();
        pw.close();
        br.close();
    }
}