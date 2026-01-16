import java.util.Scanner;

public class Letter_problem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); // consume newline
        
        char[][] grid = new char[n][m];
        
        // Read the grid
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        
        // Find bounds of shaded area
        int minRow = n, maxRow = -1;
        int minCol = m, maxCol = -1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '*') {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }
        
        // Output the minimum bounding rectangle
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        
        sc.close();
    }
}