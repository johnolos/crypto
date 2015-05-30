package PRNG;


public class Random {

    private long time;
    private long seed;


    public Random() {
        this(System.nanoTime());
    }

    public Random(long seed) {
        this.time = System.currentTimeMillis();
        this.seed = seed;
    }


    private double generate() {
        return 0.0;
    }


    /**
     * Produces a pseudo random number in the range between 0 and up to but not included n.
     * @param n Number in range of [0,n)
     * @return Random integer.
     */
    public int randomInt(int n) {
        return 0;
    }


}
