package de.sebhn.algorithm.excersise4;

import java.math.BigInteger;

/**
 * Prints all path to a certain n
 */
public class PathNodes {

  private static int sumOfPaths;

  public static void main(String[] args) {
    int n;
    if (args.length == 0) {
      System.out.println("No arguments - use default n=2");
      n = 2;
    } else {
      try {
        n = Integer.parseInt(args[0]);
      } catch (NumberFormatException nfe) {
        System.out.println("Argument is not a number, use default n=2. Error-Message: " + nfe);
        n = 2;
      }
    }

    sumOfPaths = 0;
    System.out.println("n=" + n);
    calculatePaths(n, n, 0, "");
    System.out.println("sum: " + sumOfPaths);
  }

  private static BigInteger calculatePaths(int x, int y, int step, String path) {
    if (x < 0 || y < 0) {
      return BigInteger.ZERO;
    } else if (x == 0 && y == 0) {
      sumOfPaths += path.length() + 1;
      return BigInteger.ONE;
    }

    BigInteger numberOfPaths = BigInteger.ZERO;

    if (y < x) {
      numberOfPaths = numberOfPaths.add(calculatePaths(x - 1, y, 0, "R" + path)); // right
    }
    if (y <= x) {
      numberOfPaths = numberOfPaths.add(calculatePaths(x, y - 1, 0, "U" + path)); // up
    }
    if (y >= x) {
      numberOfPaths = numberOfPaths.add(calculatePaths(x - 1, y - 1, 0, "F" + path)); // upRight
    }
    if (y > x + 1 && step != 2) {
      numberOfPaths = numberOfPaths.add(calculatePaths(x + 1, y - 1, 1, "L" + path)); // upLeft
    }
    if (y >= x && step != 1) {
      numberOfPaths = numberOfPaths.add(calculatePaths(x - 1, y + 1, 2, "D" + path)); // downRight
    }

    return numberOfPaths;
  }

}
