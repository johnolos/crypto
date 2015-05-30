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

    }
}