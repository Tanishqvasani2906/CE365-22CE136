import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class prac3_Lexer {
    private List<prac3_Token> tokens = new ArrayList<>();
    private int line = 1;

    public List<prac3_Token> tokenize(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String lineText = reader.readLine();
        while (lineText != null) {
            tokenizeLine(lineText);
            line++;
            lineText = reader.readLine(); // Read the next line
        }
        reader.close();
        return tokens;
    }

    private void tokenizeLine(String lineText) {
        int pos = 0;
        while (pos < lineText.length()) {
            char current = lineText.charAt(pos);

            // Skip whitespace
            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            // Match each token type using regex
            boolean matched = false;
            for (Map.Entry<String, String> entry : prac3_LexerConfig.TOKEN_PATTERNS.entrySet()) {
                String type = entry.getKey();
                String pattern = entry.getValue();
                Pattern regex = Pattern.compile("^" + pattern);
                Matcher matcher = regex.matcher(lineText.substring(pos));

                if (matcher.find()) {
                    String value = matcher.group();
                    tokens.add(new prac3_Token(type, value, line));
                    pos += value.length(); // Move the position forward
                    matched = true;
                    break;
                }
            }

            // Handle unknown tokens
            if (!matched) {
                System.err.println("Lexical Error at line " + line + ": Unknown token '" + current + "'");
                pos++; // Move the position forward to avoid infinite loop
            }
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("test.c");
            prac3_Lexer lexer = new prac3_Lexer();
            lexer.tokenize(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
