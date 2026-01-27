// Dijkstra


import java.util.*;
import java.io.*;

public class Dijkstra {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Node implements Comparable<Node> {
        int vertex;
        long dist;
        
        Node(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        
        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int w = Integer.parseInt(edge[2]);
            
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }
        
        // Dijkstra's algorithm
        long[] dist = new long[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(parent, -1);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            long d = current.dist;
            
            if (d > dist[u]) continue;
            
            for (Edge edge : graph[u]) {
                int v = edge.to;
                long newDist = dist[u] + edge.weight;
                
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    parent[v] = u;
                    pq.offer(new Node(v, newDist));
                }
            }
        }
        
        // Check if path exists
        if (dist[n] == Long.MAX_VALUE) {
            pw.println(-1);
        } else {
            // Reconstruct path
            List<Integer> path = new ArrayList<>();
            int current = n;
            while (current != -1) {
                path.add(current);
                current = parent[current];
            }
            Collections.reverse(path);
            
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) pw.print(" ");
                pw.print(path.get(i));
            }
            pw.println();
        }
        
        pw.close();
        br.close();
    }
}