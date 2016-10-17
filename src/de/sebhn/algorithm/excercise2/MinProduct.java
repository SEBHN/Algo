package de.sebhn.algorithm.excercise2;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinProduct {

  private static int indexStart = 0;
  private static int indexEnd = 0;


  public static void main(String[] args) throws Exception {
    double[] someRandomArray;
    if (args.length == 0) {
      someRandomArray = someOtherRandomArray();
    } else {
      someRandomArray = new double[args.length];
      for (int i = 0; i < args.length; i++) {
        someRandomArray[i] = Double.valueOf(args[i]);
      }
    }
    calculateMinProduct(someRandomArray);
  }

  public static void calculateMinProduct(double[] someRandomArray) throws Exception {
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
      // System.out.println("new min " + current_min_prod + " new max " + current_max_prod);


      // momentane max < momentan min = vorzeichenwechsel //wechsel von min/max
      if (current_min_prod > 1) {
        current_min_prod = 1;
        indexEnd = i - 1;
      }

      result = Math.min(result, current_min_prod);
      if (current_min_prod <= result) {
        indexEnd = i;
        // System.out.println(i);
      }


      previous_max_prod = current_max_prod;
      previous_min_prod = current_min_prod;
      // System.out.println(result);
    }
    indexStart = determineIndexStart(someRandomArray, indexEnd, result);

    String factors = IntStream.range(indexStart, indexEnd + 1) // start from indexFrom
        .mapToObj(i -> String.valueOf(Double.valueOf(someRandomArray[i]))) // assign value to String
        .collect(Collectors.joining("*", " calculated from ", ""));
    System.out.println("result: " + result + factors);
  }

  private static int determineIndexStart(double[] someRandomArray, int indexEnd, double result)
      throws Exception {
    double newResult = 1;
    for (int i = indexEnd; i >= 0; i--) {
      newResult *= someRandomArray[i];
      if (newResult == result) {
        return i;
      }
    }
    throw new Exception("Shouldnt come here");
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
    randomArray[0] = 0.2;
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
    randomArray[0] = 2;
    randomArray[1] = -30;
    randomArray[2] = 300;
    randomArray[3] = 3;
    randomArray[4] = 3;
    randomArray[5] = -30;
    randomArray[6] = 3;
    randomArray[7] = 3;
    randomArray[8] = 3;
    randomArray[9] = 10000000;
    return randomArray;
  }

}
