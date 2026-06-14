class Solution {
    public List<Integer> exitPoint(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int i = 0, j = 0;
        int dir = 0; // 0=Right, 1=Down, 2=Left, 3=Up

        while (true) {
            if (mat[i][j] == 1) {
                dir = (dir + 1) % 4; // turn right
                mat[i][j] = 0;       // update cell
            }

            int ni = i, nj = j;

            if (dir == 0) nj++;      // Right
            else if (dir == 1) ni++; // Down
            else if (dir == 2) nj--; // Left
            else ni--;               // Up

            // Check if moved outside the matrix
            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                return Arrays.asList(i, j);
            }

            i = ni;
            j = nj;
        }
    }
}
