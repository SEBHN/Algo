package de.sebhn.algorithm.excercise3;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Is responsible for calculating fibonacci numbers
 */
public class Fibonacci {

  private static HashMap<Integer, BigInteger> B = new HashMap<>(40); // table for storing already
                                                                     // calculated fibonacci number

  /**
   * calculates the 'n' fibonacci number
   * 
   * @param n - the n fibonacci number
   * @return fibonacci number
   */
  public static BigInteger of(int n) {
    if (n == 0) {
      return BigInteger.ZERO;
    } else if (n == 1) {
      return BigInteger.ONE;
    } else {
      BigInteger number = B.get(n); // lookup
      if (number != null) {
        return number; // return looked up value
      } else {
        BigInteger newNumber = of(n - 1).add(of(n - 2)); // calculate
        B.put(n, newNumber); // put new value
        return newNumber;
      }
    }
  }
}
