//Controls the overall flow of the Code Cracker game.
   //Tracking the number of attempts remaining
   //Coordinating between CodeGenerator, FeedbackEngine, and PlayerInput
   //Determining and announcing win/loss state
 
public class GameManager {

    private static final int MAX_ATTEMPTS = 10;

    private final CodeGenerator   codeGenerator;
    private final FeedbackEngine  feedbackEngine;
    private final PlayerInput     playerInput;

    private char[] secretCode;
    private int    attemptsUsed;
    private boolean gameOver;
    private boolean playerWon;

    public GameManager(CodeGenerator codeGenerator,
                       FeedbackEngine feedbackEngine,
                       PlayerInput playerInput) {
        this.codeGenerator  = codeGenerator;
        this.feedbackEngine = feedbackEngine;
        this.playerInput    = playerInput;
    }

    /**
     * Initialises and runs a full game session.
     */
    public void startGame() {
        secretCode   = codeGenerator.generateCode();
        attemptsUsed = 0;
        gameOver     = false;
        playerWon    = false;

        printWelcome();

        while (!gameOver) {
            int attemptsLeft = MAX_ATTEMPTS - attemptsUsed;
            System.out.printf("%nAttempt %d / %d%n", attemptsUsed + 1, MAX_ATTEMPTS);
            System.out.printf("Attempts remaining: %d%n", attemptsLeft);

            char[] guess = playerInput.getValidGuess();
            attemptsUsed++;

            FeedbackEngine.Feedback feedback = feedbackEngine.evaluate(secretCode, guess);
            printFeedback(guess, feedback);

            if (feedback.isWin(CodeGenerator.getCodeLength())) {
                playerWon = true;
                gameOver  = true;
            } else if (attemptsUsed >= MAX_ATTEMPTS) {
                gameOver = true;
            }
        }

        printEndGame();
    }

  
    // Display helpers 
    private void printWelcome() {
        System.out.println("===========================================");
        System.out.println("        Welcome to CODE CRACKER!          ");
        System.out.println("===========================================");
        System.out.println("A secret 4-character code has been generated.");
        System.out.println("Valid characters: A  B  C  D  E  F");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to crack the code.");
        System.out.println("-------------------------------------------");
        System.out.println("Feedback key:");
        System.out.println("  Exact   = correct letter, correct position");
        System.out.println("  Partial = correct letter, wrong position");
        System.out.println("===========================================");
    }

    private void printFeedback(char[] guess, FeedbackEngine.Feedback feedback) {
        System.out.println("-------------------------------------------");
        System.out.printf("  Your guess : %s%n", new String(guess));
        System.out.printf("  Exact      : %d%n", feedback.getExactMatches());
        System.out.printf("  Partial    : %d%n", feedback.getPartialMatches());
        System.out.println("-------------------------------------------");
    }

    private void printEndGame() {
        System.out.println("%n===========================================");
        if (playerWon) {
            System.out.println("  *** CONGRATULATIONS — YOU CRACKED IT! ***");
            System.out.printf ("  Solved in %d attempt%s.%n",
                    attemptsUsed, attemptsUsed == 1 ? "" : "s");
        } else {
            System.out.println("  GAME OVER — Better luck next time!");
        }
        System.out.printf("  The secret code was: %s%n", new String(secretCode));
        System.out.println("===========================================");
    }
}
