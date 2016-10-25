package de.sebhn.algorithm.excercise2;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Calculates from any double array the smallest product
 * 
 * @author Phi Long Tran <191624> & Manuel Wessner <191711>
 */
public class MinProduct {

  private static int indexStart = 0;
  private static int indexEnd = 0;


  public static void main(String[] args) throws Exception {
    double[] someRandomArray;
    if (args.length == 0) {
      someRandomArray = someRandomArray();
    } else {
      someRandomArray = new double[args.length];
      for (int i = 0; i < args.length; i++) {
        someRandomArray[i] = Double.valueOf(args[i]);
      }
    }
    calculateMinProduct(someRandomArray);
  }

  /**
   * Calculates from any double array the smallest product
   * 
   * @param someRandomArray
   * @return smallest product
   */
  public static double calculateMinProduct(double[] someRandomArray) throws Exception {
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


      if (current_min_prod > 1) {
        current_min_prod = 1;
        indexEnd = i - 1;
      }

      result = Math.min(result, current_min_prod);
      if (current_min_prod <= result) {
        indexEnd = i;
      }


      previous_max_prod = current_max_prod;
      previous_min_prod = current_min_prod;
    }
    indexStart = determineIndexStart(someRandomArray, indexEnd, result);

    String factors = IntStream.range(indexStart, indexEnd + 1) // start from indexFrom
        .mapToObj(i -> String.valueOf(Double.valueOf(someRandomArray[i]))) // assign value to String
        .collect(Collectors.joining("*", " calculated from ", ""));
    System.out.println("result: " + result + factors);
    return result;
  }

  /**
   * Goes the arrays back from a given index to find the startIndex by comparing the result
   * 
   * @throws Exception
   */
  private static int determineIndexStart(double[] someRandomArray, int indexEnd, double result)
      throws Exception {
    double newResult = 1;
    for (int i = indexEnd; i >= 0; i--) {
      newResult *= someRandomArray[i];
      if (newResult == result) {
        return i;
      }
    }
    return 0;
    // throw new Exception("Shouldnt come here");
  }

  public static double[] someRandomArray() {
    double[] randomArray = new double[10];
    randomArray[0] = 1;
    randomArray[1] = 13;
    randomArray[2] = 4.5;
    randomArray[3] = 7;
    randomArray[4] = 98;
    randomArray[5] = 33.5;
    randomArray[6] = 3;
    randomArray[7] = 64;
    randomArray[8] = 8;
    randomArray[9] = 2;
    return randomArray;
  }
}
