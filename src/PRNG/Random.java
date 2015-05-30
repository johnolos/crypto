package PRNG;


import java.util.concurrent.atomic.AtomicLong;

public class Random {

    private AtomicLong seed;

    java.util.Random random = new java.util.Random();

    private static final long multiplier = 0x5575L;
    private static final long scrambleShift = 0x13L;
    private static final long nextShift = 0x53F131L;
    private static final long adder = 0x17L;


    /**
     * Creates a new Random generator. This constructor creates an instance of a
     * Random generator to a value which is very unlikely to distinct from other
     * instances created by the same constructor.
     */
    public Random() {
        this(System.nanoTime());
    }

    /**
     * Creates a new Random generator based on the input seed given with it's
     * parameter.
     * @param seed Initial seed.
     */
    public Random(long seed) {
        this.seed = new AtomicLong(scrambleSeed(seed));
    }

    private static long scrambleSeed(long seed) {
        return (seed ^ multiplier) >> scrambleShift;
    }

    private long nextSeed() {
        return (seed.get() * multiplier + adder) | nextShift;
    }


    /**
     * Generate a pseudo random precision number between 0.0 and 1.0.
     * @return Returns a pseudo random double.
     */
    private int generate(int bits) {
        return random.nextInt((int)Math.pow(2, bits));

/*        long oldSeed, newSeed;
        AtomicLong seed = this.seed;
        do {
            oldSeed = seed.get();
            newSeed = nextSeed();
        } while(!seed.compareAndSet(oldSeed, newSeed));
        int number = (int)newSeed >>> (52 - bits);
        int number2 = number % ((int)Math.pow(2,bits));
        return number2;*/
    }


    /**
     * Produces a pseudo random number in the range between 0 and up to but not included n.
     * @param n Upper bound of random number range.
     * @return Returns a pseudo random number in range [0, n).
     */
    public int randomInt(int n){
        assert n > 0;
        return generate(n);
    }

    /**
     * Produces a pseudo random integer.
     * @return Returns a pseudo random.
     */
    public int randomInt() {
        return generate(32);
    }

    public boolean randomBoolean() {
        return generate(1) == 0;
    }

    /**
     * Produces a pseudo random number in the range between base and up to but not included n.
     * @param base Base number of the range to find random number.
     * @param n Upper bound of random number range.
     * @return Returns a pseudo random number in range [base, n).
     */
    public int randomInt(int base, int n) throws IllegalRangeException {
        if(base < 0) {
            throw new IllegalRangeException("Base was negative");
        } else if(n <= base) {
            throw new IllegalRangeException("Upper bound was lower or equal to base");
        }

        return (int)(generate(32)*(n-base) + base);
    }


    public void setSeed(long seed) {
        this.seed.set(seed);
    }


    public class RandomException extends Exception {
        public RandomException(String msg) {
            super(msg);
        }
    }

    public class IllegalRangeException extends RandomException {
        public IllegalRangeException(String msg) {
            super(msg);
        }
    }


}
