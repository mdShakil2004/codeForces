import java.util.*;

class Solution {
    public int shortestPath(int V, int src, int dest, int[][] edges) {

        int extra = 0;
        for (int[] e : edges) {
            if (e[2] == 2) extra++;
        }

        int totalNodes = V + extra;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            adj.add(new ArrayList<>());
        }

        int newNode = V;

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            if (w == 1) {
                adj.get(u).add(v);
                adj.get(v).add(u);
            } else {
                adj.get(u).add(newNode);
                adj.get(newNode).add(u);

                adj.get(newNode).add(v);
                adj.get(v).add(newNode);

                newNode++;
            }
        }

        int[] dist = new int[totalNodes];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        dist[src] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                }
            }
        }

        return dist[dest];
    }
}
