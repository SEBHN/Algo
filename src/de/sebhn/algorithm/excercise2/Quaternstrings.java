package de.sebhn.algorithm.excercise2;

import java.math.BigInteger;

public class Quaternstrings {

  private int n;
  private BigInteger[] currentPairs;

  public Quaternstrings(int n) {
    this.n = n;
    this.currentPairs =
        new BigInteger[] {BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ONE};
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
    BigInteger a0 = currentPairs[0];
    BigInteger a1 = currentPairs[1];
    BigInteger a2 = currentPairs[2];
    BigInteger a3 = currentPairs[3];

    BigInteger amountOfPaths = a0.add(a1).add(a2).add(a3);
    return amountOfPaths;
  }

  private void addInPlace() {
    BigInteger a0 = currentPairs[0];
    BigInteger a1 = currentPairs[1];
    BigInteger a2 = currentPairs[2];
    BigInteger a3 = currentPairs[3];

    BigInteger newa0 = a0.add(a2).add(a3); // exclude 1
    BigInteger newa1 = a1.add(a0).add(a3); // exclude 2
    BigInteger newa2 = a2.add(a0).add(a1); // exclude 3
    BigInteger newa3 = a3.add(a0).add(a1).add(a2); // every number allowed

    currentPairs[0] = newa0;
    currentPairs[1] = newa1;
    currentPairs[2] = newa2;
    currentPairs[3] = newa3;
  }
}
