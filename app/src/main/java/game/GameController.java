package game;

import java.util.Random;

/**
 * Created by Ann on 07.10.2017.
 */

public class GameController {
    public int score;

    private Random rnd;
    private int digitsSum;
    private int numberOfDigits;

    public GameController(int numberOfDigits){
        score = 0;
        rnd = new Random();
        this.numberOfDigits = numberOfDigits;
    }

    public String generateDigitSequence(){
        digitsSum = 0;
        StringBuilder number = new StringBuilder("");
        for(int i = 0; i < numberOfDigits; i++){
            int digit = rnd.nextInt(10);
            number.append(digit);
            digitsSum += digit;
        }

        return new String(number);
    }

    public int getDigitSum() {
        return digitsSum;
    }
}
