import java.util.*;
import java.io.*;

public class secrat_massage{
    static class Edge implements Comparable<Edge> {
        int u, v;
        long w;
        
        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        
        public int compareTo(Edge other) {
            return Long.compare(this.w, other.w);
        }
    }
    
    static class DSU {
        int[] parent, rank;
        
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;
            
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
            return true;
        }
    }
    
    static List<Edge>[] tree;
    static long[] maxOnPath;
    static boolean[] visited;
    
    static boolean dfs(int u, int target, int parent, long currentMax) {
        if (u == target) {
            maxOnPath[0] = currentMax;
            return true;
        }
        
        visited[u] = true;
        for (Edge e : tree[u]) {
            int v = (e.u == u) ? e.v : e.u;
            if (v != parent && !visited[v]) {
                if (dfs(v, target, u, Math.max(currentMax, e.w))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static long findMaxOnPath(int u, int v, int n) {
        visited = new boolean[n + 1];
        maxOnPath[0] = 0;
        dfs(u, v, -1, 0);
        return maxOnPath[0];
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            
            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                line = br.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                long w = Long.parseLong(line[2]);
                edges[i] = new Edge(u, v, w);
            }
            
            // Build MST
            Arrays.sort(edges);
            DSU dsu = new DSU(n);
            List<Edge> mstEdges = new ArrayList<>();
            List<Edge> nonMstEdges = new ArrayList<>();
            long mstSum = 0;
            
            for (Edge e : edges) {
                if (dsu.union(e.u, e.v)) {
                    mstEdges.add(e);
                    mstSum += e.w;
                } else {
                    nonMstEdges.add(e);
                }
            }
            
            // Check if MST exists
            if (mstEdges.size() != n - 1) {
                pw.println(-1);
                continue;
            }
            
            // Build tree adjacency list
            tree = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }
            for (Edge e : mstEdges) {
                tree[e.u].add(e);
                tree[e.v].add(e);
            }
            
            maxOnPath = new long[1];
            long answer = Long.MAX_VALUE;
            
            // Try each non-MST edge
            for (Edge e : nonMstEdges) {
                long maxEdge = findMaxOnPath(e.u, e.v, n);
                long newSum = mstSum - maxEdge + e.w;
                answer = Math.min(answer, newSum);
            }
            
            if (answer == Long.MAX_VALUE) {
                pw.println(-1);
            } else {
                pw.println(answer);
            }
        }
        
        pw.close();
    }
}