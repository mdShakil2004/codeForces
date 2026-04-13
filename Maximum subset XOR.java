// Maximum subset XOR


class Solution {
    public static int maxSubsetXOR(int arr[], int N) {
        int index = 0;

        // Build XOR basis
        for (int bit = 31; bit >= 0; bit--) {
            int maxIndex = -1;

            // Find element with current bit set
            for (int i = index; i < N; i++) {
                if (((arr[i] >> bit) & 1) == 1) {
                    maxIndex = i;
                    break;
                }
            }

            // If no such element, skip
            if (maxIndex == -1) continue;

            // Swap to current index
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;

            // Eliminate this bit from all others
            for (int i = 0; i < N; i++) {
                if (i != index && ((arr[i] >> bit) & 1) == 1) {
                    arr[i] ^= arr[index];
                }
            }

            index++;
        }

        // Get maximum XOR from basis
        int result = 0;
        for (int i = 0; i < index; i++) {
            result = Math.max(result, result ^ arr[i]);
        }

        return result;
    }
}
