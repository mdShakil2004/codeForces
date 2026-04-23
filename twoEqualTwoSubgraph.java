class Solution {
public boolean canSplit(int arr[]) {
int total = 0;

    // Step 1: calculate total sum
    for (int num : arr) {
        total += num;
    }
    
    // Step 2: if total sum is odd, can't split
    if (total % 2 != 0) {
        return false;
    }
    
    int prefixSum = 0;
    int target = total / 2;
    
    // Step 3: find prefix with sum = total/2
    for (int num : arr) {
        prefixSum += num;
        
        if (prefixSum == target) {
            return true;
        }
    }
    
    return false;
}


}
