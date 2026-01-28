

// Permutation
import java.util.*;

public class Permutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] p = new int[n];
            
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }
            
            // Find the first position where p[i] is not the maximum it could be
            int l = 0;
            for (int i = 0; i < n; i++) {
                if (p[i] != n - i) {
                    l = i;
                    break;
                }
            }
            
            // Find the position of maximum element from l to end
            int maxVal = p[l];
            int r = l;
            for (int i = l + 1; i < n; i++) {
                if (p[i] > maxVal) {
                    maxVal = p[i];
                    r = i;
                }
            }
            
            // Reverse segment [l, r]
            reverse(p, l, r);
            
            // Output
            for (int i = 0; i < n; i++) {
                System.out.print(p[i]);
                if (i < n - 1) System.out.print(" ");
            }
            System.out.println();
        }
        
        sc.close();
    }
    
    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}