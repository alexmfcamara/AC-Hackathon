package maindalorian.game;

import java.util.Random;

public class RandomGenerator {

    public static int getRandomNumber(int max)
    {
        Random random = new Random();

        return random.nextInt(max);
    }
}
