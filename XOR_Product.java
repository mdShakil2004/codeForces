import java.util.*;
import java.io.*;

public class XOR_Product {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            String[] parts = br.readLine().split(" ");
            long x = Long.parseLong(parts[0]);
            long k = Long.parseLong(parts[1]);
            
            long result = solve(x, k);
            pw.println(result);
        }
        
        pw.close();
    }
    
    static long solve(long x, long k) {
        if (k == 1) return 1;
        
        // Find the position of the highest bit that changes in [x, x+k)
        long xorRange = (x ^ (x + k - 1));
        
        // Find the highest bit position
        int highBit = 63;
        while (highBit >= 0 && ((xorRange >> highBit) & 1) == 0) {
            highBit--;
        }
        
        if (highBit < 0) {
            // x and x+k-1 are the same (k=1 case, already handled)
            return 1;
        }
        
        // The answer is related to 2^(highBit+1) * k
        // But capped by theoretical maximum
        long powerOf2 = 1L << (highBit + 1);
        
        // The maximum distinct values is min(k*k, powerOf2 * k)
        // In practice, for optimal y, we get approximately 2k values
        // when k is small relative to the bit changes
        
        // Based on pattern analysis:
        // The answer is 2*k when we can spread optimally
        long result = Math.min(k * k, 2 * k);
        
        // But we need to account for bit structure
        // More accurate: we get powerOf2 * k values when properly aligned
        if (k <= powerOf2) {
            result = 2 * k;
        } else {
            result = powerOf2 * k;
        }
        
        return result;
    }
}