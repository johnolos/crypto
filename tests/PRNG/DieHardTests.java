package PRNG;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Implementation of Diehard Tests to test randomness.
 */
public class DieHardTests {

    //private Random random;
    java.util.Random random = new java.util.Random();

    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Before
    public void setUp() throws Exception {
        random = new java.util.Random();
    }

    @Test
    public void testBirthdaySpacing() throws Exception {
        int n = (int)Math.pow(2, 24);
        int m = (int)Math.pow(2, 10);

        boolean[] birthdays = new boolean[n];

        for(int i = 0; i < m; i++) {
            int num = random.nextInt(n);
            birthdays[num] = true;
        }

        List<Integer> listOfSpaceNumbers = new ArrayList<>();
        int lastNumber = 0;
        int currentNumber;
        for(int i = 0; i < n; i++) {
            if(birthdays[i]) {
                currentNumber = i;
                listOfSpaceNumbers.add(currentNumber - lastNumber);
                lastNumber = currentNumber;
            }
        }

        double sumOfSpaceNumbers = 0;
        for(int i : listOfSpaceNumbers) {
            sumOfSpaceNumbers+=i;
        }

        double mean = sumOfSpaceNumbers / listOfSpaceNumbers.size();

        System.out.printf("Sum: %f.%n", sumOfSpaceNumbers);
        System.out.printf("Mean of spacing: %f.%n", mean);
        double expected = Math.pow(m, 3.0) / (4 * n);
        assertEquals(expected, mean, 0.0);
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
            x = random.nextInt(l);
            y = random.nextInt(l);
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