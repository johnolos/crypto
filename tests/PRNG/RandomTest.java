package PRNG;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomTest {

    private Random random;
    private Random randomSeeded;

    @Before
    public void setUp() throws Exception {
        random = new Random();
        randomSeeded = new Random((System.nanoTime() % 1024) + System.currentTimeMillis());
    }


    @Test
    public void testRandomInt() throws Exception {

        int numberOfIterations = 100000;
        int numberOfBits = 4;
        int numberOfValues = (int)Math.pow(2, numberOfBits);
        int expected = numberOfIterations / numberOfValues;
        double offset = expected / (1.70*numberOfValues);

        int range = (int)Math.pow(2,numberOfBits);

        int[] intArray = new int[range];
        for(int i = 0; i < range; i++) {
            intArray[i] = 0;
        }

        for(int i = 0; i < numberOfIterations; i++) {
            int number = random.randomInt(numberOfBits);
            intArray[number]++;
        }

        for(int i = 0; i < range; i++) {
            int freq = intArray[i];
            System.out.printf("Expected: %d Frequency: %d Offset: %f.%n", expected, freq, offset);
            assertEquals(expected, freq, offset);
        }

        /*
        for(int i = 0; i < range; i++) {
            int number = random.randomInt(0, i);
        }
        */
    }


    @Test
    public void testRandomLong() throws Exception {
        System.out.println(random.randomInt(32));
    }
}