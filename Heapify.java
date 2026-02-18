// Heapify.java

// Heapify.java

import java.util.*;
import java.io.*;

public class Heapify {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) a[i] = Integer.parseInt(st.nextToken());
            
            boolean[] visited = new boolean[n + 1];
            boolean ok = true;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    // build chain
                    List<Integer> chain = new ArrayList<>();
                    long pos = i;
                    while (pos <= n) {
                        chain.add((int)pos);
                        visited[(int)pos] = true;
                        pos *= 2;
                    }
                    // collect values at these positions
                    Set<Integer> vals = new HashSet<>();
                    for (int p : chain) vals.add(a[p]);
                    // check if vals == set of positions
                    for (int p : chain) {
                        if (!vals.contains(p)) { ok = false; break; }
                    }
                    if (!ok) break;
                }
            }
            sb.append(ok ? "YES" : "NO").append('\n');
        }
        System.out.print(sb);
    }
}