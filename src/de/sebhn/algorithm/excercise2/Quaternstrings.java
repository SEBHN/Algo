package de.sebhn.algorithm.excercise2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Quaternstrings {

  public static void main(String[] args) {
    Quaternstrings.calculate(3);
  }

  public static void calculate(int n) {
    int radix = 4;

    List<String> possibleNumbers = new ArrayList<>();
    BigInteger maxNumber = new BigInteger("4").pow(n);
    System.out.println("maxnbr " + maxNumber);

    for (BigInteger i = BigInteger.ZERO; i.compareTo(maxNumber) < 0; i = i.add(BigInteger.ONE)) {
      String numberAsRadix4 = i.toString(radix);
      while (numberAsRadix4.length() < n) {
        numberAsRadix4 = "0" + numberAsRadix4;
      }
      possibleNumbers.add(numberAsRadix4);
    }

    for (String number : possibleNumbers) {
      System.out.println(number);
    }
    System.out.println("Amount of possibleNumbers " + possibleNumbers.size());
  }
}
