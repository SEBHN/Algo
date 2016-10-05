package de.sebhn.algorithm.excercise2;

import static java.lang.Math.min;

import java.util.Arrays;

public class ScanLineMultiplicationMinimum {


  public static void main(String[] args) {
    double bisMin = Double.MAX_VALUE;
    double[] array = somePositiveArray();
    double scanMin = getSmallestNumber(array);
    for (int i = 1; i < array.length; i++) {
      double myDouble = array[i];
      scanMin *= myDouble;
      if (scanMin > 1 && i > 1) {
        if (!hasNegativeNumberInRemainingArray(array, i)) {
          scanMin /= myDouble;
        }
      }
      System.out.println("bisMin" + bisMin + " scanMin:" + scanMin);
      bisMin = min(scanMin, bisMin);
    }
    System.out.println("Array: " + Arrays.toString(array));
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

  private static double getSmallestNumber(double[] array) {
    Double smallest = null;
    for (int i = 0; i < array.length; i++) {
      double value = array[i];
      if (smallest == null) {
        smallest = Double.valueOf(value);
      } else if (smallest.doubleValue() > value) {
        smallest = Double.valueOf(value);
      }
    }
    return smallest.doubleValue();
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
    double[] randomArray = new double[11];
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
    randomArray[10] = -11;
    return randomArray;
  }

  public static double[] somePositiveArray() {
    double[] randomArray = new double[11];
    randomArray[0] = 10000;
    randomArray[1] = 2;
    randomArray[2] = 3;
    randomArray[3] = 4;
    randomArray[4] = 5;
    randomArray[5] = 6;
    randomArray[6] = 7;
    randomArray[7] = 8;
    randomArray[8] = 9;
    randomArray[9] = 10;
    randomArray[10] = 11;
    return randomArray;
  }
}
