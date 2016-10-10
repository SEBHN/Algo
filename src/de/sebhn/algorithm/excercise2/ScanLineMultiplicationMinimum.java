package de.sebhn.algorithm.excercise2;

import static java.lang.Math.min;

import java.util.Arrays;

public class ScanLineMultiplicationMinimum {


  public static void main(String[] args) {
    double scanMin = 1;
    double bisMin = 1;
    double[] someRandomArray = someRandomArray();
    for (int i = 0; i < someRandomArray.length; i++) {
      double myDouble = someRandomArray[i];
      System.out.println("calc" + scanMin + "*" + myDouble);
      scanMin *= myDouble;
      if (scanMin > 1) {
        if (!hasNegativeNumberInRemainingArray(someRandomArray, i)) {
          scanMin /= myDouble;
        }
      }
      System.out.println("bisMin" + bisMin + " scanMin:" + scanMin);
      bisMin = min(scanMin, bisMin);
    }
    System.out.println("Array: " + Arrays.toString(someRandomArray));
  }

  private static boolean hasNegativeNumberInRemainingArray(double[] arrayToCheck,
      int currentPosition) {
    for (int j = currentPosition; j < arrayToCheck.length; j++) {
      double aPossibleNegative = arrayToCheck[currentPosition];
      if (aPossibleNegative < 0) {
        return true;
      }
    }
    return false;
  }

  public static double[] someRandomArray() {
    double[] randomArray = new double[10];
    randomArray[0] = 1;
    randomArray[1] = -13;
    randomArray[2] = 4.5;
    randomArray[3] = 7;
    randomArray[4] = 98;
    randomArray[5] = -33.5;
    randomArray[6] = 3;
    randomArray[7] = 64;
    randomArray[8] = 8;
    randomArray[9] = -2;
    return randomArray;
  }

  public static double[] someNegativeArray() {
    double[] randomArray = new double[10];
    randomArray[0] = -1;
    randomArray[1] = -2;
    randomArray[2] = -3;
    randomArray[3] = -4;
    randomArray[4] = -5;
    randomArray[5] = -6;
    randomArray[6] = -7;
    randomArray[7] = -8;
    randomArray[8] = -9;
    randomArray[9] = -10;
    return randomArray;
  }
}
