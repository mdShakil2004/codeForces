


import java.util.*;
import java.io.*;

public class message {
    static class Edge implements Comparable<Edge> {
        int u, v;
        long w;
        int idx;
        
        Edge(int u, int v, long w, int idx) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.idx = idx;
        }
        
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }
    }
    
    static class DSU {
        int[] p;
        
        DSU(int n) {
            p = new int[n + 1];
            for (int i = 0; i <= n; i++) p[i] = i;
        }
        
        int find(int x) {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        }
        
        boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;
            p[px] = py;
            return true;
        }
        
        int countComponents(int n) {
            Set<Integer> roots = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                roots.add(find(i));
            }
            return roots.size();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
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
                long w = Long.parseLong(uvw[2]);
                edges[i] = new Edge(u, v, w, i);
            }
            
            Arrays.sort(edges);
            
            long ans = solve(n, m, edges);
            pw.println(ans == Long.MAX_VALUE ? -1 : ans);
        }
        
        pw.close();
    }
    
    static long solve(int n, int m, Edge[] edges) {
        if (m < n) return -1; // Can't even have n-1 edges
        
        long ans = Long.MAX_VALUE;
        int target = n - 1;
        
        // Try skipping each edge and taking next n-1 edges
        for (int skip = 0; skip <= m - target; skip++) {
            DSU dsu = new DSU(n);
            long sum = 0;
            
            for (int i = skip; i < skip + target && i < m; i++) {
                sum += edges[i].w;
                dsu.union(edges[i].u, edges[i].v);
            }
            
            // Check if NOT a tree (more than 1 component)
            if (dsu.countComponents(n) > 1) {
                ans = Math.min(ans, sum);
            }
        }
        
        return ans;
    }
}