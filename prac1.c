#include <stdio.h>
#include <string.h>

int main()
{
    char str[100]; // Array to store the string
    int valid = 1; // Flag to check if the string contains only 'a' and 'b'
    int found = 0; // Variable to count occurrences of the pattern "a*bb"

    printf("Enter your input String: ");
    scanf("%99s", str); // Read the input string, limiting input to 99 characters

    int length = strlen(str); // Calculate the length of the input string

    // Validate that the string contains only 'a' and 'b'
    for (int i = 0; i < length; i++)
    {
        if (str[i] != 'a' && str[i] != 'b')
        {
            valid = 0; // Mark the string as invalid
            break;
        }
    }

    if (!valid) // If the string contains invalid characters
    {
        printf("\nThe given string is invalid because it contains characters other than 'a' and 'b'.\n");
        return 0; // Exit the program
    }

    // Check for the "a*bb" pattern
    int i = 0;
    while (i < length)
    {
        // Skip all consecutive 'a's
        while (i < length && str[i] == 'a')
        {
            i++;
        }

        // Check if "bb" follows
        if (i < length - 1 && str[i] == 'b' && str[i + 1] == 'b')
        {
            found++;
            i += 2; // Move past the "bb"
        }
        else
        {
            // If a single 'b' is found or an invalid pattern occurs, break
            if (i < length && str[i] == 'b')
            {
                valid = 0;
                break;
            }
            i++;
        }
    }

    // If the pattern is found exactly once, it's valid; otherwise, it's invalid
    if (found == 1 && valid)
    {
        printf("\nValid String\n");
    }
    else
    {
        printf("\nInvalid String\n");
    }

    return 0;
}
