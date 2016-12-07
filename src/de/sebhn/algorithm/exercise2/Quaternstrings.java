package de.sebhn.algorithm.exercise2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Find all numbers for a specific pattern <br>
 * Disallowed numbers are: i,i+1
 * 
 * @author Phi Long Tran <191624> & Manuel Wessner <191711>
 */
public class Quaternstrings {

  private int n;
  private BigInteger a0;
  private BigInteger a1;
  private BigInteger a2;
  private BigInteger a3;

  public Quaternstrings(int n) {
    this.n = n;
    a0 = a1 = a2 = a3 = BigInteger.ONE;
    System.out.println("n=" + n);
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Bitte geben sie eine Zahl ein: ");
    int n;
    try (Scanner s = new Scanner(System.in)) {
      n = Integer.parseInt(s.next());
    }
    Quaternstrings.calculate(n);
  }

  public static BigInteger calculate(int n) {
    return new Quaternstrings(n).calculate();
  }

  /**
   * Calculates all possible combinations
   * 
   * @return calculated number of combinations
   */
  private BigInteger calculate() {
    for (int i = 1; i < n; i++) {
      addInPlace();
    }
    BigInteger combinations = getAmountOfPossibleCombinations();
    System.out.println("Amount of possible combinations: " + combinations);
    return combinations;
  }

  private BigInteger getAmountOfPossibleCombinations() {
    BigInteger possibleCombinations = a0.add(a1).add(a2).add(a3);
    return possibleCombinations;
  }

  private void addInPlace() {
    BigInteger newa0 = a0.add(a2).add(a3); // exclude 1
    BigInteger newa1 = a1.add(a0).add(a3); // exclude 2
    BigInteger newa2 = a2.add(a0).add(a1); // exclude 3
    BigInteger newa3 = a3.add(a0).add(a1).add(a2); // every number allowed

    a0 = newa0;
    a1 = newa1;
    a2 = newa2;
    a3 = newa3;
  }
}
