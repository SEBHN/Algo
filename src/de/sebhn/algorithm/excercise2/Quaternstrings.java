package de.sebhn.algorithm.excercise2;

import java.math.BigInteger;

public class Quaternstrings {

  public static void main(String[] args) {
    Quaternstrings.calculate(1000);
  }

  public static void calculate(int n) {
    int radix = 4;
    BigInteger maxNumber = new BigInteger("4").pow(n);
    BigInteger counter = BigInteger.ZERO;
    System.out.println("maxnbr " + maxNumber);

    for (BigInteger i = BigInteger.ZERO; i.compareTo(maxNumber) < 0; i = i.add(BigInteger.ONE)) {
      String numberAsRadix4 = i.toString(radix);
      numberAsRadix4 = addLeadingZeros(n, numberAsRadix4);

      if (canBePrinted(numberAsRadix4)) {
        counter = counter.add(BigInteger.ONE);
        // System.out.println(numberAsRadix4);
      }
    }

    System.out.println("Amount of possibleNumbers " + counter);
  }

  // TODO: make following code faster
  private static boolean canBePrinted(String numberAsRadix4) {
    boolean print = true;
    char[] numbers = numberAsRadix4.toCharArray();
    for (int j = 0; j < numbers.length; j++) {
      char currentChar = numbers[j];
      int nextIndex = j + 1;
      if (hasNextIndex(numbers, nextIndex)) {
        char nextChar = numbers[nextIndex];
        int currentCharAsNumber = currentChar;
        int nextCharAsNumber = nextChar;
        if (nextCharAsNumber == currentCharAsNumber + 1) {
          print = false;
        }
      }
    }
    return print;
  }

  private static String addLeadingZeros(int length, String numberAsRadix4) {
    while (numberAsRadix4.length() < length) {
      numberAsRadix4 = "0" + numberAsRadix4;
    }
    return numberAsRadix4;
  }

  private static boolean hasNextIndex(char[] numbers, int nextIndex) {
    try {
      @SuppressWarnings("unused")
      char nextChar = numbers[nextIndex];
      return true;
    } catch (ArrayIndexOutOfBoundsException aio) {
      // ignore
      return false;
    }
  }
}
