class Solution {
    public boolean checkElements(int start, int end, int[] arr) {
        // Store all array elements in a HashSet
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        
        for (int num : arr) {
            set.add(num);
        }
        
        // Check every element in range [start, end]
        for (int i = start; i <= end; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }
        
        return true;
    }
}
