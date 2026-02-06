



import java.util.*;

public class Seats {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            
            // Count initial students (1's in the string)
            int initialStudents = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    initialStudents++;
                }
            }
            
            // Find all gaps of consecutive empty seats
            int studentsToAdd = 0;
            int gapLength = 0;
            
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    gapLength++;
                } else {
                    // End of a gap
                    if (gapLength > 0) {
                        studentsToAdd += gapLength / 2;
                        gapLength = 0;
                    }
                }
            }
            
            // Don't forget the last gap if string ends with 0's
            if (gapLength > 0) {
                studentsToAdd += gapLength / 2;
            }
            
            System.out.println(initialStudents + studentsToAdd);
        }
        
        sc.close();
    }
}