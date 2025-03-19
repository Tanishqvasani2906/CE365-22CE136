import java.util.Scanner;

public class RecursiveDescentParser {
    private static String inputString;
    private static int index = 0;
    private static boolean isValid = true;

    // S → xyzS | Yab
    // X → cd | cp
    // Y → pX | qX | Yg

    private static void S() {
        if (match("xyz")) {
            S();
        } else if (Y()) {
            if (match("ab")) {
                return;
            }
        }
        isValid = false;
    }

    private static boolean X() {
        return match("cd") || match("cp");
    }

    private static boolean Y() {
        if (match("p") || match("q")) {
            return X();
        } else if (Y() && match("g")) {
            return true;
        }
        return false;
    }

    private static boolean match(String token) {
        if (index + token.length() <= inputString.length() && inputString.substring(index, index + token.length()).equals(token)) {
            index += token.length();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        inputString = scanner.nextLine();
        scanner.close();
        
        index = 0;
        isValid = true;
        S();
        
        if (isValid && index == inputString.length()) {
            System.out.println("Valid String");
        } else {
            System.out.println("Invalid String");
        }
    }
}
