import java.util.*;

class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        
        // Step 1: Create graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Step 2: Indegree array
        int[] indegree = new int[n];
        
        // Step 3: Build graph
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            
            graph.get(prereq).add(course);
            indegree[course]++;
        }
        
        // Step 4: Queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        
        // Add courses with no prerequisites
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Step 5: Process nodes
        int count = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            
            for (int next : graph.get(curr)) {
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // Step 6: Check if all courses processed
        return count == n;
    }
}
