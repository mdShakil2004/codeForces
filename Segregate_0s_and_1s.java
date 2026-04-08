// Segregate 0s and 1s
class Solution {
    void segregate0and1(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Move left pointer until we find 1
            while (left < right && arr[left] == 0) {
                left++;
            }

            // Move right pointer until we find 0
            while (left < right && arr[right] == 1) {
                right--;
            }

            // Swap
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        }
    }
}
