import java.util.Map;

public class prac3_LexerConfig {
    public static final Map<String, String> TOKEN_PATTERNS = Map.of(
            "KEYWORD", "\\b(int|float|if|else|return|for|while)\\b",
            "IDENTIFIER", "[a-zA-Z_][a-zA-Z0-9_]*",
            "NUMBER", "\\b\\d+(\\.\\d+)?\\b",
            "STRING", "\".*?\"",
            "CHAR", "'.?'",
            "OPERATOR", "(==|<=|>=|!=|=|<|>|\\+|\\-|\\*|/|%)",
            "DELIMITER", "[;\\{\\}\\(\\),]",
            "COMMENT_SINGLE", "//.*",
            "COMMENT_MULTI", "/\\*.*?\\*/");
}