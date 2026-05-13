import java.util.*;

class Solution {
    
    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                dfs(nei, adj, vis);
            }
        }
    }
    
    public int findMotherVertex(int V, int[][] edges) {
        
        // Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }
        
        boolean[] vis = new boolean[V];
        int candidate = -1;
        
        // Find potential mother vertex
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis);
                candidate = i;
            }
        }
        
        // Verify candidate
        vis = new boolean[V];
        dfs(candidate, adj, vis);
        
        for (boolean reached : vis) {
            if (!reached) {
                return -1;
            }
        }
        
        // Need smallest mother vertex
        // Check all vertices smaller than candidate
        for (int i = 0; i < candidate; i++) {
            vis = new boolean[V];
            dfs(i, adj, vis);
            
            boolean ok = true;
            
            for (boolean reached : vis) {
                if (!reached) {
                    ok = false;
                    break;
                }
            }
            
            if (ok) {
                return i;
            }
        }
        
        return candidate;
    }
}
