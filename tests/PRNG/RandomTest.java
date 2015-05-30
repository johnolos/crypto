package PRNG;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    }


    @Test
    public void testInt() throws Exception {

        int numberOfIterations = 1000;
        int numberOfBits = 4;
        int expected = numberOfIterations / numberOfBits;
        int offset = expected / numberOfBits;

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
            assertEquals(expected, freq, offset);
        }

    }
}