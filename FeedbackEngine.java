//Calculates feedback for a player's guess compared to actual secret code.
   // Exact match:   correct character in the correct position.
   // Partial match: correct character present but in the wrong position.
  // Duplicate handling: each character in the secret code and guess can only be "used" once during matching, preventing double-counting.
 
public class FeedbackEngine {

    private final int codeLength;

    public FeedbackEngine(int codeLength) {
        this.codeLength = codeLength;
    }

    /**
     * Computes feedback for the given guess against the secret code.
     * @param secret the secret code
     * @param guess  the player's guess
     * @return a Feedback object containing exact and partial match counts
     */
    public Feedback evaluate(char[] secret, char[] guess) {
        int exactMatches = 0;
        int partialMatches = 0;

        boolean[] secretUsed = new boolean[codeLength];
        boolean[] guessUsed  = new boolean[codeLength];

        // First pass: count exact matches
        for (int i = 0; i < codeLength; i++) {
            if (guess[i] == secret[i]) {
                exactMatches++;
                secretUsed[i] = true;
                guessUsed[i]  = true;
            }
        }

        // Second pass: count partial matches (skip already-matched positions)
        for (int i = 0; i < codeLength; i++) {
            if (guessUsed[i]) continue; // already an exact match

            for (int j = 0; j < codeLength; j++) {
                if (secretUsed[j]) continue; // secret char already used

                if (guess[i] == secret[j]) {
                    partialMatches++;
                    secretUsed[j] = true;
                    break;
                }
            }
        }

        return new Feedback(exactMatches, partialMatches);
    }

    /**
     * Simple data container for feedback results.
     */
    public static class Feedback {
        private final int exactMatches;
        private final int partialMatches;

        public Feedback(int exactMatches, int partialMatches) {
            this.exactMatches   = exactMatches;
            this.partialMatches = partialMatches;
        }

        public int getExactMatches()   { return exactMatches; }
        public int getPartialMatches() { return partialMatches; }

        public boolean isWin(int codeLength) {
            return exactMatches == codeLength;
        }
    }
}
