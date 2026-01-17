import java.util.*;

public class Safe {
    static int n, m;
    static int[][] attempts;
    static int[] responses;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        
        attempts = new int[m][n];
        responses = new int[m];
        
        for (int i = 0; i < m; i++) {
            String s = sc.next();
            for (int j = 0; j < n; j++) {
                attempts[i][j] = s.charAt(j) - '0';
            }
            responses[i] = sc.nextInt();
        }
        
        int mid = n / 2;
        
        // Map: matching pattern -> count
        // matching pattern is encoded as an array of m integers
        Map<String, Long> leftMap = new HashMap<>();
        
        // Generate all possibilities for left half
        int leftSize = mid;
        for (int mask = 0; mask < (1 << leftSize); mask++) {
            int[] matches = new int[m];
            for (int i = 0; i < m; i++) {
                int count = 0;
                for (int j = 0; j < leftSize; j++) {
                    int bit = (mask >> j) & 1;
                    if (bit == attempts[i][j]) {
                        count++;
                    }
                }
                matches[i] = count;
            }
            String key = Arrays.toString(matches);
            leftMap.put(key, leftMap.getOrDefault(key, 0L) + 1);
        }
        
        // Generate all possibilities for right half and count compatible ones
        long result = 0;
        int rightSize = n - mid;
        for (int mask = 0; mask < (1 << rightSize); mask++) {
            int[] matches = new int[m];
            for (int i = 0; i < m; i++) {
                int count = 0;
                for (int j = 0; j < rightSize; j++) {
                    int bit = (mask >> j) & 1;
                    if (bit == attempts[i][mid + j]) {
                        count++;
                    }
                }
                matches[i] = count;
            }
            
            // We need leftMatches[i] + matches[i] = responses[i]
            int[] needed = new int[m];
            boolean valid = true;
            for (int i = 0; i < m; i++) {
                needed[i] = responses[i] - matches[i];
                if (needed[i] < 0 || needed[i] > leftSize) {
                    valid = false;
                    break;
                }
            }
            
            if (valid) {
                String key = Arrays.toString(needed);
                result += leftMap.getOrDefault(key, 0L);
            }
        }
        
        System.out.println(result);
    }
}