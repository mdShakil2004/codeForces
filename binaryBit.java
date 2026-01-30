


import java.util.Scanner;

public class binaryBit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();
        
        if (s.equals("1")) {
            System.out.println(0);
            return;
        }
        
        int operations = 0;
        int i = s.length() - 1;
        
        while (i > 0) {
            if (s.charAt(i) == '0') {
                operations++;
                i--;
            } else {
                // Count consecutive 1's
                int ones = 0;
                while (i >= 0 && s.charAt(i) == '1') {
                    ones++;
                    i--;
                }
                
                // If we've processed all digits (i < 0), the last bit group is all 1's
                if (i < 0) {
                    // For "111...1", add 1 gives "1000...0", then we divide (ones-1) times to get "1"
                    operations += ones;
                } else {
                    // For "...0111...1", add 1 gives "...1000...0", then divide ones times
                    operations += ones + 1;
                }
            }
        }
        
        System.out.println(operations);
    }
}