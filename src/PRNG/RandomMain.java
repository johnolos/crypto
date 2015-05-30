package PRNG;

import java.util.HashMap;

public class RandomMain {

    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();
        int numberOfTrue = 0;
        int numberOfJavaTrue = 0;
        int numberOfFalse = 0;
        int numberOfJavaFalse = 0;
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            boolean b1 = random.randomBoolean();
            boolean b2 = rand.nextBoolean();
            if(b1 == true)
                numberOfTrue++;
            else
                numberOfFalse++;
            if(b2 == true)
                numberOfJavaTrue++;
            else
                numberOfJavaFalse++;
        }
        System.out.printf("Number of trues: %d%n", numberOfTrue);
        System.out.printf("Number of false: %d%n", numberOfFalse);

        System.out.printf("Number of java true: %d%n", numberOfJavaTrue);
        System.out.printf("Number of java false: %d%n", numberOfJavaFalse);

        int range = (int)Math.pow(2,3);

        int[] intArray = new int[range];
        for(int i = 0; i < range; i++) {
            intArray[i] = 0;
        }

        for(int i = 0; i < 1000; i++) {
            int number = random.randomInt(3);
            intArray[number]++;
        }

        for(int i = 0; i < range; i++) {
            System.out.printf("Number %d: %d times.%n", i, intArray[i]);
        }
    }
}
