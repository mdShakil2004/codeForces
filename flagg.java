import java.util.Scanner;

public class flagg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); // consume the newline
        
        String[] flag = new String[n];
        for (int i = 0; i < n; i++) {
            flag[i] = sc.nextLine();
        }
        
        boolean valid = true;
        
        // Check each row
        for (int i = 0; i < n; i++) {
            // Check if all characters in this row are the same
            char firstChar = flag[i].charAt(0);
            for (int j = 1; j < m; j++) {
                if (flag[i].charAt(j) != firstChar) {
                    valid = false;
                    break;
                }
            }
            if (!valid) break;
            
            // Check if adjacent rows have different colors
            if (i > 0) {
                if (flag[i].charAt(0) == flag[i-1].charAt(0)) {
                    valid = false;
                    break;
                }
            }
        }
        
        if (valid) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
        sc.close();
    }
}