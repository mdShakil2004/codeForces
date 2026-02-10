import java.util.*;
import java.io.*;
// pizza_delevery problem
public class pizza_delevery {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            String[] first = br.readLine().split(" ");
            int n = Integer.parseInt(first[0]);
            int Ax = Integer.parseInt(first[1]);
            int Ay = Integer.parseInt(first[2]);
            int Bx = Integer.parseInt(first[3]);
            int By = Integer.parseInt(first[4]);
            
            String[] xVals = br.readLine().split(" ");
            String[] yVals = br.readLine().split(" ");
            
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(Integer.parseInt(xVals[i]), Integer.parseInt(yVals[i]));
            }
            
            // Sort by x coordinate
            Arrays.sort(points, (a, b) -> Integer.compare(a.x, b.x));
            
            // dp[i][j] = min cost to deliver first i pizzas, currently at position j (0 to i-1)
            long[][] dp = new long[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], Long.MAX_VALUE / 2);
            }
            
            // Base case: start at point A, delivered 0 pizzas
            dp[0][0] = 0;
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (dp[i][j] >= Long.MAX_VALUE / 2) continue;
                    
                    // Current position
                    int curX = (j == 0) ? Ax : points[j - 1].x;
                    int curY = (j == 0) ? Ay : points[j - 1].y;
                    
                    // Go to next point (i+1)
                    int nextX = points[i].x;
                    int nextY = points[i].y;
                    
                    long cost = Math.abs(nextX - curX) + Math.abs(nextY - curY);
                    dp[i + 1][i + 1] = Math.min(dp[i + 1][i + 1], dp[i][j] + cost);
                }
            }
            
            // Calculate final answer: from each ending position to B
            long ans = Long.MAX_VALUE;
            for (int j = 0; j <= n; j++) {
                if (dp[n][j] >= Long.MAX_VALUE / 2) continue;
                
                int curX = (j == 0) ? Ax : points[j - 1].x;
                int curY = (j == 0) ? Ay : points[j - 1].y;
                
                long cost = Math.abs(Bx - curX) + Math.abs(By - curY);
                ans = Math.min(ans, dp[n][j] + cost);
            }
            
            pw.println(ans);
        }
        
        pw.close();
    }
}