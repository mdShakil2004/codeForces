


import java.util.Scanner;

public class flea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();
        long s = sc.nextLong();
        sc.close();
        
        // Find gcd(s, n) and gcd(s, m)
        long gx = gcd(s, n);
        long gy = gcd(s, m);
        
        // For x-direction: we have gx residue classes mod gx
        // Each class has either floor(n/gx) or ceil(n/gx) positions
        long qx = n / gx;  // quotient
        long rx = n % gx;  // remainder
        
        // rx classes have (qx+1) positions
        // (gx-rx) classes have qx positions
        // Maximum is (qx+1) if rx > 0, otherwise qx
        
        long maxX = (rx > 0) ? (qx + 1) : qx;
        long countX = (rx > 0) ? rx : gx;
        
        // For y-direction: similar logic
        long qy = m / gy;
        long ry = m % gy;
        
        long maxY = (ry > 0) ? (qy + 1) : qy;
        long countY = (ry > 0) ? ry : gy;
        
        // Answer is countX * countY
        System.out.println(countX * countY);
    }
    
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}