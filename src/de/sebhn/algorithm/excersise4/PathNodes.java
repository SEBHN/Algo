package de.sebhn.algorithm.excersise4;

import java.math.BigInteger;

/**
 * Prints all path to a certain n
 */
public class PathNodes {

  public static void main(String[] args) {
    int n = 2;
    BigInteger numberOfPaths = b(n, n, 0, "");
    System.out.println(numberOfPaths);
  }

  private static BigInteger b(int x, int y, int t, String s) {
    if (x < 0 || y < 0) {
      return BigInteger.ZERO;
    } else if (x == 0 && y == 0) {
      System.out.println(" " + s + "."); // print path
      return BigInteger.ONE;
    }

    BigInteger r = BigInteger.ZERO;

    if (y < x) {
      r = r.add(b(x - 1, y, 0, "R" + s)); // right
    }
    if (y <= x) {
      r = r.add(b(x, y - 1, 0, "U" + s)); // up
    }
    if (y >= x) {
      r = r.add(b(x - 1, y - 1, 0, "F" + s)); // upRight
    }
    if (y > x + 1 && t != 2) {
      r = r.add(b(x + 1, y - 1, 1, "L" + s)); // upLeft
    }
    if (y >= x && t != 1) {
      r = r.add(b(x - 1, y + 1, 2, "D" + s)); // downRight
    }

    return r;
  }

}
