// Almost Prime


import java.util.Scanner;

public class  Almost_Prime{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (countDistinctPrimes(i) == 2) {
                count++;
            }
        }
        
        System.out.println(count);
        sc.close();
    }
    
    static int countDistinctPrimes(int num) {
        int count = 0;
        
        // Check for factor 2
        if (num % 2 == 0) {
            count++;
            while (num % 2 == 0) {
                num /= 2;
            }
        }
        
        // Check for odd factors from 3 onwards
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                count++;
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        
        // If num > 1, then it's a prime factor
        if (num > 1) {
            count++;
        }
        
        return count;
    }
}