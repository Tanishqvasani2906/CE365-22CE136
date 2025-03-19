import java.util.*;

public class practical7 {
    static Map<String, List<List<String>>> grammar = new HashMap<>();
    static Map<String, Set<String>> first = new HashMap<>();
    static Map<String, Set<String>> follow = new HashMap<>();

    public static void main(String[] args) {
        // Step 1: Define the fixed grammar
        grammar.put("S", Arrays.asList(Arrays.asList("A", "B", "C"), Arrays.asList("D")));
        grammar.put("A", Arrays.asList(Arrays.asList("a"), Arrays.asList("ε")));
        grammar.put("B", Arrays.asList(Arrays.asList("b"), Arrays.asList("ε")));
        grammar.put("C", Arrays.asList(Arrays.asList("(", "S", ")"), Arrays.asList("c")));
        grammar.put("D", Arrays.asList(Arrays.asList("A", "C")));

        // Step 2: Initialize FIRST and FOLLOW sets
        initializeFirstAndFollow();

        // Step 3: Compute FIRST sets
        for (String nonTerminal : grammar.keySet()) {
            computeFirst(nonTerminal);
        }

        // Step 4: Compute FOLLOW sets
        follow.get("S").add("$"); // Start symbol always contains $
        for (String nonTerminal : grammar.keySet()) {
            computeFollow(nonTerminal);
        }

        // Step 5: Print results
        System.out.println("FIRST Sets:");
        printSet(first);
        System.out.println("\nFOLLOW Sets:");
        printSet(follow);
    }

    // ✅ Initialize FIRST and FOLLOW sets for non-terminals and terminals
    static void initializeFirstAndFollow() {
        Set<String> terminals = new HashSet<>();

        for (String key : grammar.keySet()) {
            first.put(key, new HashSet<>()); // Initialize FIRST for non-terminals
            follow.put(key, new HashSet<>()); // Initialize FOLLOW for non-terminals

            for (List<String> production : grammar.get(key)) {
                for (String symbol : production) {
                    if (!grammar.containsKey(symbol) && !symbol.equals("ε")) {
                        terminals.add(symbol); // Collect terminals
                    }
                }
            }
        }

        // Initialize FIRST sets for terminals
        for (String terminal : terminals) {
            first.put(terminal, new HashSet<>(Collections.singleton(terminal)));
        }

        // Initialize FIRST for ε explicitly
        first.put("ε", new HashSet<>(Collections.singleton("ε")));
    }

    // ✅ Corrected Compute FIRST Set
    static Set<String> computeFirst(String symbol) {
        if (first.containsKey(symbol) && !first.get(symbol).isEmpty()) {
            return first.get(symbol); // Already computed
        }

        Set<String> result = new HashSet<>();

        if (!grammar.containsKey(symbol)) { // Terminal case
            result.add(symbol);
            return result;
        }

        for (List<String> production : grammar.get(symbol)) {
            boolean containsEpsilon = true;

            for (String token : production) {
                Set<String> tokenFirst = computeFirst(token);
                result.addAll(tokenFirst);
                if (!tokenFirst.contains("ε")) {
                    containsEpsilon = false;
                    break;
                }
            }

            if (containsEpsilon) {
                result.add("ε");
            }
        }

        first.put(symbol, result);
        return result;
    }

    // ✅ Compute FOLLOW set iteratively
    static void computeFollow(String symbol) {
        for (Map.Entry<String, List<List<String>>> entry : grammar.entrySet()) {
            String key = entry.getKey();
            for (List<String> production : entry.getValue()) {
                for (int i = 0; i < production.size(); i++) {
                    if (production.get(i).equals(symbol)) {
                        if (i + 1 < production.size()) { // If there is a next symbol
                            Set<String> nextFirst = computeFirst(production.get(i + 1));
                            follow.get(symbol).addAll(nextFirst);
                            follow.get(symbol).remove("ε");
                        }

                        // If at end or next contains ε, inherit FOLLOW of parent
                        if (i + 1 == production.size() || computeFirst(production.get(i + 1)).contains("ε")) {
                            follow.get(symbol).addAll(follow.get(key));
                        }
                    }
                }
            }
        }
    }

    // ✅ Utility function to print sets
    static void printSet(Map<String, Set<String>> setMap) {
        for (Map.Entry<String, Set<String>> entry : setMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
