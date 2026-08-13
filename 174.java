class Solution {
    static class Pair {
        int v, wt;

        Pair(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }
    }

    public void dfs(int node, ArrayList<ArrayList<Pair>> adj,
                    boolean[] vis, Stack<Integer> st) {
        vis[node] = true;

        for (Pair p : adj.get(node)) {
            if (!vis[p.v]) {
                dfs(p.v, adj, vis, st);
            }
        }

        st.push(node);
    }

    public int[] maxDistance(int V, int src, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Pair>());
        }

        for (ArrayList<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);
            int w = e.get(2);
            adj.get(u).add(new Pair(v, w));
        }

        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis, st);
            }
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[src] = 0;

        while (!st.isEmpty()) {
            int u = st.pop();

            if (dist[u] == Integer.MIN_VALUE)
                continue;

            for (Pair p : adj.get(u)) {
                if (dist[p.v] < dist[u] + p.wt) {
                    dist[p.v] = dist[u] + p.wt;
                }
            }
        }

        return dist;
    }
}
