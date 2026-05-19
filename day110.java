import java.util.*;

class Solution {
    public int minSteps(int[] arr, int start, int end) {
        
        if(start == end) return 0;
        
        Queue<int[]> q = new LinkedList<>();
        int[] dist = new int[1000];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[start] = 0;
        q.offer(new int[]{start, 0});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            int node = curr[0];
            int steps = curr[1];
            
            for(int num : arr) {
                int next = (node * num) % 1000;
                
                if(steps + 1 < dist[next]) {
                    dist[next] = steps + 1;
                    
                    if(next == end) {
                        return steps + 1;
                    }
                    
                    q.offer(new int[]{next, steps + 1});
                }
            }
        }
        
        return -1;
    }
}
