import java.util.*;

class FiniteAutomaton {
    private int numStates;
    private Set<Character> alphabet;
    private int initialState;
    private Set<Integer> acceptingStates;
    private Map<Integer, Map<Character, Integer>> transitions;

    public FiniteAutomaton(int numStates, Set<Character> alphabet, int initialState, Set<Integer> acceptingStates) {
        this.numStates = numStates;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.acceptingStates = acceptingStates;
        this.transitions = new HashMap<>();
        for (int i = 1; i <= numStates; i++) {
            transitions.put(i, new HashMap<>());
        }
    }

    public void addTransition(int fromState, char symbol, int toState) {
        transitions.get(fromState).put(symbol, toState);
    }

    public boolean validateString(String input) {
        int currentState = initialState;
        for (char symbol : input.toCharArray()) {
            if (!transitions.get(currentState).containsKey(symbol)) {
                return false; // Invalid transition
            }
            currentState = transitions.get(currentState).get(symbol);
        }
        return acceptingStates.contains(currentState);
    }
}

public class prac2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of input symbols
        System.out.print("Enter Number of Input Symbols: ");
        int numSymbols = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Set<Character> alphabet = new HashSet<>();

        // Read input symbols
        System.out.print("Input Symbols: ");
        for (char ch : scanner.nextLine().replaceAll(" ", "").toCharArray()) {
            alphabet.add(ch);
        }

        // Read number of states
        System.out.print("Enter number of states: ");
        int numStates = scanner.nextInt();

        // Read initial state
        System.out.print("Enter initial state: ");
        int initialState = scanner.nextInt();

        // Read number of accepting states
        System.out.print("Enter number of accepting states: ");
        int numAccepting = scanner.nextInt();
        Set<Integer> acceptingStates = new HashSet<>();

        // Read accepting states
        System.out.print("Accepting states: ");
        for (int i = 0; i < numAccepting; i++) {
            acceptingStates.add(scanner.nextInt());
        }

        // Create FA
        FiniteAutomaton fa = new FiniteAutomaton(numStates, alphabet, initialState, acceptingStates);

        // Read number of transitions
        System.out.print("Enter number of transitions: ");
        int numTransitions = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Read transitions
        System.out.println("Enter transition table (format: state input_symbol next_state): ");
        for (int i = 0; i < numTransitions; i++) {
            int fromState = scanner.nextInt();
            char symbol = scanner.next().charAt(0);
            int toState = scanner.nextInt();
            fa.addTransition(fromState, symbol, toState);
        }

        // Read input string
        System.out.print("Input string: ");
        String inputString = scanner.next();

        // Validate input string
        if (fa.validateString(inputString)) {
            System.out.println("Valid String");
        } else {
            System.out.println("Invalid String");
        }

        scanner.close();
    }
}
