import java.util.*;

class Solution {
    static int timer;

    public static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] isArt = new boolean[V];

        timer = 0;

        // Handle disconnected graph
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, -1, vis, tin, low, adj, isArt);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArt[i]) res.add(i);
        }

        if (res.size() == 0) {
            res.add(-1);
        }

        return res;
    }

    static void dfs(int node, int parent, boolean[] vis, int[] tin, int[] low,
                    ArrayList<ArrayList<Integer>> adj, boolean[] isArt) {

        vis[node] = true;
        tin[node] = low[node] = timer++;
        int child = 0;

        for (int neigh : adj.get(node)) {

            if (neigh == parent) continue;

            if (!vis[neigh]) {
                dfs(neigh, node, vis, tin, low, adj, isArt);

                low[node] = Math.min(low[node], low[neigh]);

                // Articulation condition
                if (low[neigh] >= tin[node] && parent != -1) {
                    isArt[node] = true;
                }

                child++;
            } else {
                // Back edge
                low[node] = Math.min(low[node], tin[neigh]);
            }
        }

        // Root case
        if (parent == -1 && child > 1) {
            isArt[node] = true;
        }
    }
}
