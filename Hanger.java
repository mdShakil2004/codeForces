import java.util.*;
import java.io.*;

public class Hanger {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int q = Integer.parseInt(firstLine[1]);
        
        TreeSet<Integer> occupied = new TreeSet<>();
        Map<Integer, Integer> employeeToHook = new HashMap<>();
        
        for (int i = 0; i < q; i++) {
            String[] request = br.readLine().split(" ");
            
            if (request[0].equals("0")) {
                // Director's query
                int left = Integer.parseInt(request[1]);
                int right = Integer.parseInt(request[2]);
                
                // Count occupied hooks in range [left, right]
                int count = occupied.subSet(left, true, right, true).size();
                out.println(count);
            } else {
                // Employee arrival/departure
                int employeeId = Integer.parseInt(request[0]);
                
                if (employeeToHook.containsKey(employeeId)) {
                    // Employee is leaving
                    int hook = employeeToHook.remove(employeeId);
                    occupied.remove(hook);
                } else {
                    // Employee is arriving - find the hook
                    int hook = findHook(n, occupied);
                    occupied.add(hook);
                    employeeToHook.put(employeeId, hook);
                }
            }
        }
        
        out.close();
    }
    
    static int findHook(int n, TreeSet<Integer> occupied) {
        if (occupied.isEmpty()) {
            // If even number of hooks, choose right-middle
            return (n + 1) / 2 + (n % 2 == 0 ? 1 : 0);
        }
        
        int maxLen = 0;
        int bestHook = 0;
        
        // Check segment before first occupied hook
        Integer first = occupied.first();
        if (first > 1) {
            int len = first - 1;
            if (len >= maxLen) {
                maxLen = len;
                int mid = len / 2;
                if (len % 2 == 0) mid++; // right-middle for even
                bestHook = mid;
            }
        }
        
        // Check segments between occupied hooks
        Integer prev = null;
        for (Integer curr : occupied) {
            if (prev != null) {
                int len = curr - prev - 1;
                if (len > 0 && len >= maxLen) {
                    maxLen = len;
                    int mid = (prev + curr) / 2;
                    if ((curr - prev - 1) % 2 == 0) mid++; // right-middle for even
                    bestHook = mid;
                }
            }
            prev = curr;
        }
        
        // Check segment after last occupied hook
        Integer last = occupied.last();
        if (last < n) {
            int len = n - last;
            if (len >= maxLen) {
                maxLen = len;
                int mid = len / 2;
                if (len % 2 == 0) mid++; // right-middle for even
                bestHook = last + mid;
            }
        }
        
        return bestHook;
    }
}