import java.util.*;

class Solution {
    int minEdgesReq(int n, int[][] edges) {

        int m = edges.length;

        // Not enough edges to connect all vertices
        if (m < n - 1)
            return -1;

        // Build graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                components++;
                dfs(i, adj, vis);
            }
        }

        return components - 1;
    }

    void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;

        for (int next : adj.get(node)) {
            if (!vis[next]) {
                dfs(next, adj, vis);
            }
        }
    }
}
