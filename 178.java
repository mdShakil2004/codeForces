class Solution {
    public int minThrows(int n, int[] lad, int[] sn) {
        int total = n * n;

        // jump[i] = destination if cell i has a ladder/snake
        int[] jump = new int[total + 1];

        for (int i = 0; i < lad.length; i += 2) {
            jump[lad[i]] = lad[i + 1];
        }

        for (int i = 0; i < sn.length; i += 2) {
            jump[sn[i]] = sn[i + 1];
        }

        boolean[] visited = new boolean[total + 1];

        // Queue stores {cell, number of throws}
        int[][] queue = new int[total + 1][2];
        int front = 0, rear = 0;

        queue[rear][0] = 1;
        queue[rear][1] = 0;
        rear++;

        visited[1] = true;

        while (front < rear) {
            int cell = queue[front][0];
            int throwsCount = queue[front][1];
            front++;

            if (cell == total) {
                return throwsCount;
            }

            // Try every possible dice result: 1 to 6
            for (int dice = 1; dice <= 6; dice++) {
                int next = cell + dice;

                if (next > total) {
                    break;
                }

                // Take snake/ladder immediately
                if (jump[next] != 0) {
                    next = jump[next];
                }

                if (!visited[next]) {
                    visited[next] = true;

                    queue[rear][0] = next;
                    queue[rear][1] = throwsCount + 1;
                    rear++;
                }
            }
        }

        return -1;
    }
}
