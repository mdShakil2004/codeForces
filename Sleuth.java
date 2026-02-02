// Sleuth


import java.util.Scanner;

public class Sleuth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String question = sc.nextLine();
        
        // Find the last letter (ignoring spaces and question mark)
        char lastLetter = ' ';
        for (int i = question.length() - 1; i >= 0; i--) {
            char c = question.charAt(i);
            if (Character.isLetter(c)) {
                lastLetter = c;
                break;
            }
        }
        
        // Convert to uppercase for easier checking
        lastLetter = Character.toUpperCase(lastLetter);
        
        // Check if it's a vowel
        if (lastLetter == 'A' || lastLetter == 'E' || lastLetter == 'I' || 
            lastLetter == 'O' || lastLetter == 'U' || lastLetter == 'Y') {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
        sc.close();
    }
}