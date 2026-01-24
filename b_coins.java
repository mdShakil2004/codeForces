import java.util.*;

public class b_coins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        List<Integer> coins = new ArrayList<>();
        int current = n;
        
        // Build the chain by repeatedly dividing by smallest prime factor
        while (current > 0) {
            coins.add(current);
            if (current == 1) break;
            
            // Find smallest prime factor
            int smallestFactor = findSmallestPrimeFactor(current);
            current = current / smallestFactor;
        }
        
        // Print the result
        for (int i = 0; i < coins.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(coins.get(i));
        }
        System.out.println();
        
        sc.close();
    }
    
    // Find the smallest prime factor of n
    private static int findSmallestPrimeFactor(int n) {
        if (n <= 1) return n;
        if (n % 2 == 0) return 2;
        
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return i;
        }
        
        return n; // n is prime
    }
}