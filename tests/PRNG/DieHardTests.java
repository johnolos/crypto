package PRNG;

import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Implementation of Diehard Tests to test randomness.
 */
public class DieHardTests {

    private Random random;

    @Before
    public void setUp() throws Exception {
        random = new Random();
    }
}