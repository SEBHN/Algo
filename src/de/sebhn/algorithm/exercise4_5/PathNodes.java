package de.sebhn.algorithm.exercise4_5;

import java.math.BigInteger;

/**
 * Calculates the sum of all visited points
 * 
 * @author Phi Long Tran <191624>
 * @author Dimitri Kapcikov <191510>
 * @author Manuel Wessner <191711>
 */
public class PathNodes {

  private static BigInteger sumOfPaths;

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
    sumOfPaths = BigInteger.ZERO;
    System.out.println("n=" + n);
    calculatePaths(n, n, 0, "");
    System.out.println("sum: " + sumOfPaths);
  }

  private static void calculatePaths(int x, int y, int step, String path) {
    if (x < 0 || y < 0) {
      return;
    } else if (x == 0 && y == 0) {
      long sum = path.length() + 1;
      sumOfPaths = sumOfPaths.add(BigInteger.valueOf(sum));
      return;
    }


    if (y < x) {
      calculatePaths(x - 1, y, 0, "R" + path); // right
    }
    if (y <= x) {
      calculatePaths(x, y - 1, 0, "U" + path); // up
    }
    if (y >= x) {
      calculatePaths(x - 1, y - 1, 0, "X" + path); // upRight
    }
    if (y > x + 1 && step != 2) {
      calculatePaths(x + 1, y - 1, 1, "Y" + path); // upLeft
    }
    if (y >= x && step != 1) {
      calculatePaths(x - 1, y + 1, 2, "D" + path); // downRight
    }

  }

}
