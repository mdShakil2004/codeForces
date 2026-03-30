import java.util.*;

class Solution {
    public int minCost(int[][] houses) {
        int n = houses.length;
        
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // {cost, index}
        pq.offer(new int[]{0, 0});
        
        int totalCost = 0;
        int count = 0;
        
        while (count < n) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int u = curr[1];
            
            if (visited[u]) continue;
            
            visited[u] = true;
            totalCost += cost;
            count++;
            
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int dist = Math.abs(houses[u][0] - houses[v][0]) +
                               Math.abs(houses[u][1] - houses[v][1]);
                    
                    pq.offer(new int[]{dist, v});
                }
            }
        }
        
        return totalCost;
    }
}
