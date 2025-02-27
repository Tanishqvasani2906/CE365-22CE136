import java.util.Scanner;

public class prac6 {
    static String inputString;
    static int i = 0;
    static int f = 0;

    // S → ( L ) | a
    // L → S L’
    // L’ → , S L’ | ε
    public static void S() {
        if (inputString.charAt(i) == 'a') {
            i++;
        } else if (inputString.charAt(i) == '(') {
            i++;
            L();
            if (inputString.charAt(i) == ')') {
                i++;
            } else {
                f = 1;
            }
        } else {
            f = 1;
        }

    }

    // L → S L’
    public static void L() {
        S();
        L_prime();
    }

    // L’ → , S L’ | ε
    public static void L_prime() {
        if (inputString.charAt(i) == ',') {
            i++;
            S();
            L_prime();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string :");
        inputString = sc.nextLine();
        sc.close();
        i = 0;
        f = 0;
        S();
        if (f == 0 && i == inputString.length()) {
            System.out.println("Valid String");
        } else {
            System.out.println("Invalid String");
        }
    }
}
