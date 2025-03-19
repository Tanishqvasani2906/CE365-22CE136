%{
    #include <stdio.h>
    #include <string.h>
    int count = 0;
    int sentence_count = 0;
    int empty_line = 0;
    int has_content = 0;  // Flag to track if we have actual content
%}

%%

^[\n] {
     if(++empty_line >= 2) return 0; ECHO;
    }
[Tt][Aa][Nn][Ii][Ss][Hh]  { count++; printf("tanishq"); has_content = 1; }
[.!?]              { if(has_content) sentence_count++; }
[a-zA-Z0-9]        { has_content = 1; ECHO; }
.                  { printf("%s", yytext); }
\n                 { empty_line = 0; has_content = 0; ECHO; }

%%

int main() {
printf("Enter the input:");
yylex();
printf("Count of times 'tanishq' appears: %d\n", count);
printf("Count of sentences: %d\n", sentence_count);
return 0;
}
int yywrap() {
return 1;
}