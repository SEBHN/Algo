package de.sebhn.algorithm.exercise2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.sebhn.algorithm.exercise2.MinProduct;

public class MinProductTest {

  @Test
  public void testNegative() throws Exception {
    assertEquals("-3628800.0", String.valueOf(MinProduct.calculateMinProduct(someNegativeArray())));
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
