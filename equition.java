import java.util.Scanner;

public class equition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        sc.close();
        
        if (A == 0) {
            // Linear equation: Bx + C = 0
            if (B == 0) {
                if (C == 0) {
                    // 0 = 0, infinite solutions
                    System.out.println(-1);
                } else {
                    // C = 0 where C != 0, no solution
                    System.out.println(0);
                }
            } else {
                // Bx + C = 0 => x = -C/B
                System.out.println(1);
                System.out.println((double) -C / B);
            }
        } else {
            // Quadratic equation: Ax^2 + Bx + C = 0
            long discriminant = (long) B * B - 4L * A * C;
            
            if (discriminant < 0) {
                // No real roots
                System.out.println(0);
            } else if (discriminant == 0) {
                // One root (repeated)
                double root = (double) -B / (2 * A);
                System.out.println(1);
                System.out.println(root);
            } else {
                // Two distinct roots
                double sqrtD = Math.sqrt(discriminant);
                double root1 = (-B - sqrtD) / (2 * A);
                double root2 = (-B + sqrtD) / (2 * A);
                
                // Print in ascending order
                if (root1 > root2) {
                    double temp = root1;
                    root1 = root2;
                    root2 = temp;
                }
                
                System.out.println(2);
                System.out.println(root1);
                System.out.println(root2);
            }
        }
    }
}