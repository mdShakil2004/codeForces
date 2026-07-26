import java.util.*;

class Solution {
    public ArrayList<ArrayList<Integer>> levelSort(int[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        int n = arr.length;
        int index = 0;
        int levelSize = 1;
        
        while (index < n) {
            ArrayList<Integer> level = new ArrayList<>();
            
            int count = Math.min(levelSize, n - index);
            for (int i = 0; i < count; i++) {
                level.add(arr[index++]);
            }
            
            Collections.sort(level);
            ans.add(level);
            
            levelSize *= 2;
        }
        
        return ans;
    }
}
