package de.sebhn.algorithm.excercise2;

import java.util.Arrays;

public class MinProduct {


  public static void main(String[] args) throws Exception {
    double[] someRandomArray = someOtherRandomArray();
    System.out.println("Array: " + Arrays.toString(someRandomArray));
    if (someRandomArray.length == 0) {
      throw new Exception("Please fill array");
    }
    double firstEntry = someRandomArray[0];
    double current_min_prod = firstEntry;
    double current_max_prod = firstEntry;
    double previous_min_prod = firstEntry;
    double previous_max_prod = firstEntry;
    double result = firstEntry;
    for (int i = 1; i < someRandomArray.length; i++) {
      double myDouble = someRandomArray[i];
      // System.out.println("calc min " + previous_min_prod + "*" + myDouble);
      // System.out.println("calc max " + previous_max_prod + "*" + myDouble);
      double temporaryMax = previous_max_prod * myDouble;
      double temporaryMin = previous_min_prod * myDouble;
      current_max_prod = Math.max(Math.max(temporaryMax, temporaryMin), myDouble);
      current_min_prod = Math.min(Math.min(temporaryMax, temporaryMin), myDouble);

      if (current_min_prod > 1) {
        current_min_prod = 1;
      }

      result = Math.min(result, current_min_prod);
      previous_max_prod = current_max_prod;
      previous_min_prod = current_min_prod;
      // System.out.println(result);
    }
    System.out.println("result: " + result);
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

  public static double[] somePositiveArray() {
    double[] randomArray = new double[10];
    randomArray[0] = 2;
    randomArray[1] = 3;
    randomArray[2] = 4;
    randomArray[3] = 5;
    randomArray[4] = 6;
    randomArray[5] = 7;
    randomArray[6] = 8;
    randomArray[7] = 9;
    randomArray[8] = 10;
    randomArray[9] = 11;
    return randomArray;
  }

  public static double[] someOtherRandomArray() {
    double[] randomArray = new double[10];
    randomArray[0] = -3;
    randomArray[1] = 3;
    randomArray[2] = 3;
    randomArray[3] = 3;
    randomArray[4] = 3;
    randomArray[5] = 3;
    randomArray[6] = 3;
    randomArray[7] = 3;
    randomArray[8] = 3;
    randomArray[9] = -4;
    return randomArray;
  }

}
