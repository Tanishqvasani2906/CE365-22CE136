import java.util.Scanner;

public class RecursiveDescentParser {
    private static String inputString;
    private static int index = 0;

    // Grammar:
    // S → XyzS | Yab
    // X → cd | cp
    // Y → pX | qX | Yg

    private static boolean S() {
        int startIndex = index;

        // Case 1: S → XyzS
        if (X() && match("yz") && S()) {
            return true;
        }
        index = startIndex;

        // Case 2: S → Yab
        if (Y() && match("ab")) {
            return true;
        }

        return false;
    }

    private static boolean X() {
        return match("cd") || match("cp");
    }

    private static boolean Y() {
        int startIndex = index;

        // Case 1: Y → pX or Y → qX
        if ((match("p") || match("q")) && X()) {
            return true;
        }
        // Case 2: Y → Yg
        index = startIndex;
        if (match("g")) {
            if (Y()) {
                return true;
            }
            index = startIndex;
        }
        return false;
    }

    private static boolean match(String token) {
        if (index + token.length() <= inputString.length() &&
                inputString.substring(index, index + token.length()).equals(token)) {
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
        boolean result = S();
        if (result && index == inputString.length()) {
            System.out.println("Valid String ");
        } else {
            System.out.println("Invalid String");
        }
    }
}
