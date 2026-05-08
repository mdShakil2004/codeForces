class Solution {
    
    public List<String> validParenthesis(String s) {
        List<String> ans = new ArrayList<>();
        
        // Use BFS to ensure minimum removals
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        q.offer(s);
        visited.add(s);
        
        boolean found = false;
        
        while (!q.isEmpty()) {
            String curr = q.poll();
            
            // Check if current string is valid
            if (isValid(curr)) {
                ans.add(curr);
                found = true;
            }
            
            // If valid string found at this level,
            // do not generate next level
            if (found) continue;
            
            // Generate all possible strings
            // by removing one parenthesis
            for (int i = 0; i < curr.length(); i++) {
                
                char ch = curr.charAt(i);
                
                // Only remove parentheses
                if (ch != '(' && ch != ')') continue;
                
                String next = curr.substring(0, i) + curr.substring(i + 1);
                
                if (!visited.contains(next)) {
                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        
        Collections.sort(ans);
        return ans;
    }
    
    // Function to check valid parentheses
    private boolean isValid(String s) {
        int count = 0;
        
        for (char ch : s.toCharArray()) {
            
            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;
            }
            
            // More closing brackets
            if (count < 0) return false;
        }
        
        return count == 0;
    }
}
