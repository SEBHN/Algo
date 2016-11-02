package de.sebhn.algorithm.excersise4;

import java.util.Arrays;

/**
 * Solves a custom traveling sales person problem
 * 
 * @author Phi Long Tran <191624>
 * @author Dimitri Kapcikov <191510>
 * @author Manuel Wessner <191711>
 */
public class Turns {

  public static void main(String[] args) {
    Perm perm = new Perm(17);
    int[] next = {};
    boolean hasNext = true;
    while (hasNext) {
      next = perm.getNext();
      hasNext = next != null;
      if (hasNext) {
        System.out.println(Arrays.toString(next));
      }
    }
  }
}
