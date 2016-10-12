package de.sebhn.algorithm.excercise2;

import java.math.BigInteger;

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
    Quaternstrings.calculate(2);
  }

  public static BigInteger calculate(int n) {
    return new Quaternstrings(n).calculate();
  }

  private BigInteger calculate() {
    for (int i = 1; i < n; i++) {
      addInPlace();
    }
    BigInteger amountOfPaths = getAmountOfPaths();
    System.out.println("Amount of paths: " + amountOfPaths);
    return amountOfPaths;
  }

  private BigInteger getAmountOfPaths() {
    BigInteger amountOfPaths = a0.add(a1).add(a2).add(a3);
    return amountOfPaths;
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
