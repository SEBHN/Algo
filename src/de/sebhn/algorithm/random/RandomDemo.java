package de.sebhn.algorithm.random;

import java.security.SecureRandom;
import java.util.Random;

public class RandomDemo {


  private long x;


  RandomDemo(long x) {
    this.x = x;
  }

  public static void main(String[] args) {
    int inputValue = 100030;
    System.out.println("Input-Value: " + inputValue);
    RandomDemo randomDemo = new RandomDemo(inputValue);
    long randomValue = randomDemo.naive();
    System.out.println("Random value naive: " + randomValue);
    System.out.println("Random value LCG (java): " + randomDemo.javaRandom());
    System.out.println("Random value SecureRandom (java): " + randomDemo.secureRandom());
    System.out.println("Random value XORShift: " + randomDemo.randomLong());
  }

  public long naive() {
    long coinThrows = x;
    long amountOfHead = 0L;
    Random random = new Random();

    for (long i = 0; i < coinThrows; i++) {
      boolean isHead = random.nextInt(2) == 1;
      if (isHead) {
        amountOfHead++;
      }
    }
    return amountOfHead;
  }

  /**
   * LCG - Linear congruential generator
   * 
   * @return random by javas {@link Random}
   */
  public long javaRandom() {
    Random random = new Random();
    return random.nextLong();
  }

  public long secureRandom() {
    return new SecureRandom().nextLong();
  }

  /**
   * XORShift random number genrator
   * 
   * @return
   */
  public long randomLong() {
    x ^= System.nanoTime();
    x ^= (x << 21);
    x ^= (x >>> 35);
    x ^= (x << 4);
    return x;
  }
}
