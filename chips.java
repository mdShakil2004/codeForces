



import java.util.Scanner;

public class chips {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // git 
        int currentWalrus = 1;
        int remainingChips = m;
        
        while (true) {
            // Check if we can give chips to current walrus
            if (remainingChips >= currentWalrus) {
                remainingChips -= currentWalrus;
                // Move to next walrus in circle
                currentWalrus++;
                if (currentWalrus > n) {
                    currentWalrus = 1;
                }
            } else {
                // Can't give enough chips, process ends
                break;
            }
        }
        
        System.out.println(remainingChips);
        sc.close();
    }
}