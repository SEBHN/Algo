package de.sebhn.algorithm.exercise3;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Solves the change making problem in the best possible way
 *
 */
public class CoinChanger {

  /**
   * Demonstrates function with a given set of coins and the first 40 fibonacci numbers as value
   * 
   * @param args
   */
  public static void main(String args[]) {
    int coins[] = {1, 3, 7, 31, 47};

    for (int i = 0; i <= 40; i++) {
      int fibonacciNumber = Fibonacci.of(i).intValue();
      System.out.println("cycle: " + i + " with fibonacci-number: " + fibonacciNumber
          + " are following combinations possible: " + maxCoins(fibonacciNumber, coins));
    }
  }

  private static BigInteger maxCoins(int n, int[] coins) {
    BigInteger[] results = new BigInteger[n + 1]; // initialize array
    Arrays.fill(results, BigInteger.ZERO); // will array with 0
    results[0] = BigInteger.ONE;
    for (int i = 0; i < coins.length; i++) { // for each coin
      for (int j = coins[i]; j <= n; j++) {
        results[j] = results[j].add(results[j - coins[i]]);
      }
    }

    return results[n];
  }
}
