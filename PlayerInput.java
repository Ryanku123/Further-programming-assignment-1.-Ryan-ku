import java.util.Scanner;


//Handles all player input from the console.
// Validates guesses: must be exactly 4 characters long
// and only contain the letters A, B, C, D, E, F.
//The program will NOT crash on invalid input.
  
  public class PlayerInput {
  
  private final Scanner scanner;
  private final int codeLength;
  private final char[] validChars;
  
  public PlayerInput(Scanner scanner, int codeLength, char[] validChars) {
  this.scanner    = scanner;
  this.codeLength = codeLength;
  this.validChars = validChars;
  }
  
 
  //Prompts the player to enter a guess and validates it.
  //Loops until a valid guess is provided.
  // @return a valid char[] guess of the correct length
    
    public char[] getValidGuess() {
    while (true) {
    System.out.print(“Enter your guess: “);
    String input = scanner.nextLine().trim().toUpperCase();
    
    ```
     if (input.length() != codeLength) {
         System.out.printf(
             "  [!] Your guess must be exactly %d characters. You entered %d. Try again.%n",
             codeLength, input.length()
         );
         continue;
     }
    
     if (!containsOnlyValidChars(input)) {
         System.out.printf(
             "  [!] Only the letters %s are allowed. Try again.%n",
             buildValidCharsString()
         );
         continue;
     }
    
     return input.toCharArray();
    ```
    
    }
    }
  
 
  //Checks whether every character in the input belongs to the valid set.
    
    private boolean containsOnlyValidChars(String input) {
    for (char c : input.toCharArray()) {
    boolean found = false;
    for (char valid : validChars) {
    if (c == valid) {
    found = true;
    break;
    }
    }
    if (!found) return false;
    }
    return true;
    }
  
  //Builds a human-readable list of valid characters (e.g. “A, B, C, D, E, F”).

    private String buildValidCharsString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < validChars.length; i++) {
    sb.append(validChars[i]);
    if (i < validChars.length - 1) sb.append(”, “);
    }
    return sb.toString();
    }
  
  public void close() {
  scanner.close();
  }
  }
