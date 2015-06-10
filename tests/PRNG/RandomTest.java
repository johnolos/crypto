package PRNG;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomTest {

    private Random random;

    @Before
    public void setUp() throws Exception {
        random = new Random();
    }

    @Test
    public void testRandomInt() throws Exception {
        int firstLimit = 40;

        for(int i = 0; i < 10000; i++) {
            int number = random.randomInt(firstLimit);
            assertTrue(number >= 0 && number <= firstLimit);
        }

        int base = 1024;
        int secondLimit = 2048;
        for(int i = 0; i < 10000; i++) {
            int number = random.randomInt(base, secondLimit);
            assertTrue(number >= base && number < secondLimit);
        }

    }

    @Test
    public void testRandomBoolean() throws Exception {
        int countTrue = 0;
        int iterations = 10000000;
        int i = 0;
        while(i < iterations) {
            if(random.randomBoolean())
                countTrue++;
            i++;
        }
        double percentageTrue = countTrue / (double)iterations;
        double percentageFalse = (iterations - countTrue) / (double)iterations;

        System.out.printf("Testing random boolean per %d iterations.%n", iterations);
        System.out.printf("Percentage of  true: %f.%n", percentageTrue);
        System.out.printf("Percentage of false: %f.%n", percentageFalse);

        assertEquals(0.50, percentageTrue, 0.001);
    }

}