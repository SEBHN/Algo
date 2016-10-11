package de.sebhn.algorithm.excercise2;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Quaternstrings {

  public static void main(String[] args) throws Exception {
    // Quaternstrings.calculate(3);
    Quaternstrings.writeFileWithAllNumbers(30);
  }

  public static void writeFileWithAllNumbers(int n) throws IOException {
    int radix = 4;
    BigInteger maxNumber = new BigInteger("4").pow(n);
    Path path = Paths.get("C:\\Temp\\numbers.txt");
    for (BigInteger i = BigInteger.ZERO; i.compareTo(maxNumber) < 0; i = i.add(BigInteger.ONE)) {
      String numberAsRadix4 = i.toString(radix);
      Files.write(path, numberAsRadix4.getBytes());
    }
  }

  public static void calculate(int n) {
    int radix = 4;
    BigInteger maxNumber = new BigInteger("4").pow(n);
    BigInteger counter = BigInteger.ZERO;
    System.out.println("n=" + n);
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

  private static String addLeadingZeros(int length, String numberAsRadix4) {
    while (numberAsRadix4.length() < length) {
      numberAsRadix4 = "0" + numberAsRadix4;
    }
    return numberAsRadix4;
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
        int currentCharAsNumberPlusOne = Character.getNumericValue(currentChar) + 1;
        int nextCharAsNumber = Character.getNumericValue(nextChar);
        if (nextCharAsNumber == currentCharAsNumberPlusOne) {
          print = false;
          break;
        }
      }
    }
    return print;
  }

  private static boolean hasNextIndex(char[] numbers, int nextIndex) {
    return nextIndex < numbers.length;
  }
}
