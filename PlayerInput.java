import java.util.Random;

//Responsible for generating the secret code.
//duplicate allowed
//Only uses a predefined set of 6 characters: A, B, C, D, E, F.

public class CodeGenerator {

    private static final char[] VALID_CHARS = {'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int CODE_LENGTH = 4;
    private final Random random;

    public CodeGenerator() {
        this.random = new Random();
    }

    /**
     * Generates a random 4-character secret code.
     * @return char array representing the secret code
     */
    public char[] generateCode() {
        char[] code = new char[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = VALID_CHARS[random.nextInt(VALID_CHARS.length)];
        }
        return code;
    }

    public static char[] getValidChars() {
        return VALID_CHARS;
    }

    public static int getCodeLength() {
        return CODE_LENGTH;
    }
}
