import java.util.*;

public class tower {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt(), d = sc.nextInt();
            int maxPerTower = 1 + d / m;
            System.out.println((n + maxPerTower - 1) / maxPerTower);
        }
    }
}