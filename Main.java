//Literally main class handling everything
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CodeGenerator  codeGenerator  = new CodeGenerator();
        FeedbackEngine feedbackEngine = new FeedbackEngine(CodeGenerator.getCodeLength());
        PlayerInput    playerInput    = new PlayerInput(
                scanner,
                CodeGenerator.getCodeLength(),
                CodeGenerator.getValidChars()
        );

        GameManager gameManager = new GameManager(codeGenerator, feedbackEngine, playerInput);

        boolean playAgain = true;
        while (playAgain) {
            gameManager.startGame();
            System.out.print("\nPlay again? (Y/N): ");
            String response = scanner.nextLine().trim().toUpperCase();
            playAgain = response.equals("Y");
        }

        playerInput.close();
        System.out.println("Thanks for playing Code Cracker. Goodbye!");
    }
}
