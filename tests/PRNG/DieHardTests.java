package PRNG;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Implementation of Diehard Tests to test randomness.
 */
public class DieHardTests {

    private Random random;
//    java.util.Random random = new java.util.Random();

    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    @Before
    public void setUp() throws Exception {
//        random = new java.util.Random();
    	random = new Random();
    }
    
    class tuple{
    	int up;
		int down;
		tuple(int up, int down){
			this.up = up;
			this.down = down;
		}
    }
    
    @Test
    public void testRuns(){
    	ArrayList<Integer> sequence = new ArrayList<Integer>();
    	
    	int sequenceLength = 100000;
    	
    	for(int i = 0; i < sequenceLength; i++){
    		sequence.add(random.randomInt());
    	}
    	
    	tuple t = findRuns(sequence);
    	float ratio = t.up/(float)t.down;
    	System.out.println("UP: " + t.up + "\nDOWN: " + t.down + "\nRatio: " + ratio);
    	assertEquals(1.0, ratio ,0.001);
    }
    
    public tuple findRuns(ArrayList<Integer> seq){
    	int dir;
    	int countUp = 0;
    	int countDown = 0;
		if(seq.get(1) > seq.get(0))
			dir = -1;
		else if(seq.get(1) < seq.get(0))
			dir = 1;
		else
			dir = 0;
		for(int i = 1; i < seq.size(); i++){
			if(seq.get(i) > seq.get(i-1)){
				if(dir == -1)
					countUp++;
				dir = 1;
			}
			else if(seq.get(i) < seq.get(i-1)){
				if(dir == 1)
					countDown++;
				dir = -1;
			}
			else
				dir = 0;
		}
		tuple t = new tuple(countUp,countDown);
		return t;
    }
    
    @Test
    public void testMinimumDistance() throws Exception {
        int numberOfTests = 10;
        double minimalDistanceSquared = 0;
        for(int i = 0; i < numberOfTests; i++) {
            if(i % 20 == 0) {
                System.out.printf("Iteration: %d.%n", i);
            }
            minimalDistanceSquared += minimalDistanceTest();
        }
        double mean = minimalDistanceSquared / numberOfTests;
        System.out.printf("Mean minimal distance: %f.%n", mean);
        assertEquals(0.995, mean, 0.2);
    }


    private double minimalDistanceTest() {
        int n = 8000;
        int l = 10000;

        List<Point> points = new ArrayList<>();
        int x, y;
        for(int i = 0; i < n; i++) {
//            x = random.nextInt(l);
//            y = random.nextInt(l);
        	x = random.randomInt(l);
        	y = random.randomInt(l);
            points.add(new Point(x,y));
        }

        double minDist = 2000;
        for(int i = 0; i < points.size(); i++) {
            for(int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double distance = (Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y));
                if(minDist > distance) {
                    minDist = distance;
                }
            }
        }

        return Math.pow(minDist,2);
    }
}