import java.util.*;
import java.io.*;

public class hidden_message {
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    
    static class DSU {
        int[] parent, rank;
        int components;
        
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            components = n;
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
            components--;
            return true;
        }
        
        int getComponents() {
            return components;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            
            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                String[] uvw = br.readLine().split(" ");
                int u = Integer.parseInt(uvw[0]);
                int v = Integer.parseInt(uvw[1]);
                int w = Integer.parseInt(uvw[2]);
                edges[i] = new Edge(u, v, w);
            }
            
            Arrays.sort(edges);
            
            long result = solve(n, m, edges);
            System.out.println(result);
        }
    }
    
    static long solve(int n, int m, Edge[] edges) {
        if (m < n) return -1;
        
        long minSum = Long.MAX_VALUE;
        
        // Try all combinations of n-1 edges
        boolean found = false;
        findMinNonTree(edges, 0, 0, 0, n, new boolean[m], new long[1], new boolean[1]);
        
        // More efficient approach: iterate through possible edge exclusions
        for (int excluded = 0; excluded < m; excluded++) {
            DSU dsu = new DSU(n);
            long sum = 0;
            int count = 0;
            
            for (int i = 0; i < m && count < n - 1; i++) {
                if (i == excluded) continue;
                sum += edges[i].w;
                dsu.union(edges[i].u, edges[i].v);
                count++;
            }
            
            if (count == n - 1 && dsu.getComponents() > 1) {
                minSum = Math.min(minSum, sum);
                found = true;
            }
        }
        
        return found ? minSum : -1;
    }
    
    static void findMinNonTree(Edge[] edges, int idx, int count, long sum, int n, boolean[] used, long[] minSum, boolean[] found) {
        // Optimization: prune if current sum already exceeds minimum
        if (minSum[0] != 0 && sum >= minSum[0]) return;
        
        if (count == n - 1) {
            DSU dsu = new DSU(n);
            for (int i = 0; i < edges.length; i++) {
                if (used[i]) {
                    dsu.union(edges[i].u, edges[i].v);
                }
            }
            
            if (dsu.getComponents() > 1) {
                if (!found[0] || sum < minSum[0]) {
                    minSum[0] = sum;
                    found[0] = true;
                }
            }
            return;
        }
        
        if (idx >= edges.length) return;
        
        // Include current edge
        used[idx] = true;
        findMinNonTree(edges, idx + 1, count + 1, sum + edges[idx].w, n, used, minSum, found);
        used[idx] = false;
        
        // Exclude current edge
        findMinNonTree(edges, idx + 1, count, sum, n, used, minSum, found);
    }
}