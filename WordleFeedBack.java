package WordleUI;

import java.awt.Color;

public class WordleFeedBack {

    private static final int WORD_LENGTH = 5;
    
    // Metodo para formar la palabra

    public static Color[] generarColores(String guess, String secretWord) {

       Color[] feedbackColors = new Color[WORD_LENGTH];

        for (int i = 0; i < WORD_LENGTH; i++) {
            char letraGuess = guess.charAt(i);
            char letraSecret = secretWord.charAt(i);

            if (letraGuess == letraSecret) {
                feedbackColors[i] = Color.GREEN;
            } else if (secretWord.contains(String.valueOf(letraGuess))) {
                feedbackColors[i] = Color.YELLOW;
            } else {
                feedbackColors[i] = Color.LIGHT_GRAY;
            }
        }

        return feedbackColors;
    }
}

    

