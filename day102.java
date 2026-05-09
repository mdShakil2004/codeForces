class Solution {
    
    public int countSpanTree(int n, int[][] edges) {
        
        // Special case
        if (n == 1) return 1;

        long[][] lap = new long[n][n];

        // Build Laplacian Matrix
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            lap[u][u]++;
            lap[v][v]++;

            lap[u][v]--;
            lap[v][u]--;
        }

        // Create cofactor matrix (remove last row & column)
        long[][] mat = new long[n - 1][n - 1];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mat[i][j] = lap[i][j];
            }
        }

        return (int)Math.round(determinant(mat, n - 1));
    }

    // Determinant using Gaussian Elimination
    private double determinant(long[][] matrix, int n) {

        double[][] a = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = matrix[i][j];
            }
        }

        double det = 1;

        for (int i = 0; i < n; i++) {

            int pivot = i;

            // Find pivot row
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(a[j][i]) > Math.abs(a[pivot][i])) {
                    pivot = j;
                }
            }

            // If pivot is zero
            if (Math.abs(a[pivot][i]) < 1e-9)
                return 0;

            // Swap rows
            if (pivot != i) {
                double[] temp = a[i];
                a[i] = a[pivot];
                a[pivot] = temp;
                det *= -1;
            }

            det *= a[i][i];

            // Eliminate below
            for (int j = i + 1; j < n; j++) {

                double factor = a[j][i] / a[i][i];

                for (int k = i; k < n; k++) {
                    a[j][k] -= factor * a[i][k];
                }
            }
        }

        return det;
    }
}
