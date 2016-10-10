package de.sebhn.algorithm.excercise2;

import java.util.ArrayList;
import java.util.List;

public class Quaternstrings {

  public static void main(String[] args) {
    Quaternstrings.calculate(3);
  }

  public static void calculate(int n) {
    int radix = 4;
    String zeroFormat = "%0" + n + "d";

    List<String> possibleNumbers = new ArrayList<>();

    double maxNumber = Math.pow(4, n);

    for (int i = 0; i < maxNumber; i++) {
      String numberAsRadix4 = Integer.toString(i, radix);
      String numberWithZeroPrefix = String.format(zeroFormat, Integer.parseInt(numberAsRadix4));
      possibleNumbers.add(numberWithZeroPrefix);
    }

    for (String number : possibleNumbers) {
      System.out.println(number);
    }
  }
}
