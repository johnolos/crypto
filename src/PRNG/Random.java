package PRNG;

import java.util.concurrent.atomic.AtomicLong;

public class Random {

    private AtomicLong seed;

    private static final long multiplier = 2685821657736338717L;
    private static final long scrambleShift = 0x13L;

    /**
     * Creates a new Random generator. This constructor creates an instance of a
     * Random generator to a value which is very unlikely to distinct from other
     * instances created by the same constructor.
     */
    public Random() {
        this(System.currentTimeMillis() + System.nanoTime());
    }

    /**
     * Creates a new Random generator based on the input seed given with it's
     * parameter.
     * @param seed Initial seed.
     */
    public Random(long seed) {
        this.seed = new AtomicLong(scrambleSeed(seed));
    }

    /**
     * Scramble seed input to create unique and distributed seed.
     * @param seed Long number input used as seed.
     * @return scrambled long input seed.
     */
    private static long scrambleSeed(long seed) {
        return (seed ^ multiplier) >> scrambleShift;
    }

    /**
     * Generate a new seed used when generating a pseudo random number generator.
     * The current implementation is using XORSHIFT* algorithm to create new seed
     * state from current seed state.
     */
    private void nextSeed() {
        long seed = this.seed.get();
        seed ^= seed >> 12;
        seed ^= seed << 25;
        seed ^= seed >> 27;
        setSeed(seed);
    }

    /**
     * Generate a pseudo random number of 32 bytes. Then the generator selects number
     * of bits from most significant bit to least significant bit.
     * Produces a random signed integer between [0,2.147.483.647] (31 bits) which is
     * the range of a normal 32 bit integer.
     * @return Returns a pseudo random integer.
     */
    private int generate(int bits) {
        assert bits >= 1 && bits <= 31;
        nextSeed();
        return extractBits(this.seed.get() * multiplier, 0, bits);
    }

    /**
     * Extracts the least significant bits from begin to end [begin,end)
     * by creating a bit mask on the bits you want to extract.
     * Further, it shifts value {@code bits} right and performs
     * a bitwise AND operation with the mask we created.
     * @param value The long number where bits are to be extracted.
     * @param begin Number of start range of bits to be extracted (inclusive).
     * @param end Number of end range of bits to be extracted (exclusive).
     * @return bits from value in range [begin, end).
     */
    private int extractBits(long value, int begin, int end) {
        assert end >= 1 && end <= 31;
        assert begin >= 0 && begin <= 30;
        assert end > begin;
        long mask = (1L << (end - begin)) - 1;
        long bits = (value >> begin) & mask;
        return (int)bits;
    }


    /**
     * Produces a pseudo random number in the range between 0 and up to but not included n.
     * @param n Upper bound of random number range.
     * @return Returns a pseudo random number in range [0, n).
     */
    public int randomInt(int n){
        assert n > 0;
        assert n < Integer.MAX_VALUE;
        return randomInt() % n;
    }

    /**
     * Produces a pseudo random integer of 31 bits.
     * @return Returns a pseudo random.
     */
    public int randomInt() {
        return generate(31);
    }

    /**
     * Produces a pseudo random boolean.
     * @return Returns a random boolean.
     */
    public boolean randomBoolean() {
        return generate(1) == 0;
    }

    /**
     * Produces a pseudo random number in the range between base and up to but not included n.
     * @param base Base number of the range to find random number.
     * @param n Upper bound of random number range.
     * @return Returns a pseudo random number in range [base, n).
     */
    public int randomInt(int base, int n) {
        assert base > 0;
        assert n >= base;
        return (randomInt() % (n - base)) + base;
    }

    public long randomLong() {
        return ((long)(generate(32)) << 32) + generate(32);
    }

    /**
     * Set state seed to current instanced.
     * The algorithm will scramble the seed given.
     * @param seed Seed to be used.
     */
    public void setSeed(long seed) {
        this.seed.set(scrambleSeed(seed));
    }

}
