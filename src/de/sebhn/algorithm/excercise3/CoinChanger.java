package de.sebhn.algorithm.excercise3;

import java.math.BigInteger;
import java.util.Arrays;

public class CoinChanger {


  static BigInteger countWays(int S[], int m, int n) {
    // Time complexity of this function: O(mn)
    // Space Complexity of this function: O(n)

    // table[i] will be storing the number of solutions
    // for value i. We need n+1 rows as the table is
    // constructed in bottom up manner using the base
    // case (n = 0)
    BigInteger[] table = new BigInteger[n + 1];
    // Initialize all table values as 0
    Arrays.fill(table, BigInteger.ZERO); // O(n)

    // Base case (If given value is 0)
    table[0] = BigInteger.ONE;

    // Pick all coins one by one and update the table[]
    // values after the index greater than or equal to
    // the value of the picked coin
    for (int i = 0; i < m; i++) {
      for (int j = S[i]; j <= n; j++) {
        BigInteger otherValue = table[j - S[i]];
        table[j] = table[j].add(otherValue);
      }
      System.out.print("");
    }


    return table[n];
  }

  // Driver Function to test above function
  public static void main(String args[]) {
    int arr[] = {1, 3, 7, 31, 47};
    int m = arr.length;



    for (int i = 33; i <= 36; i++) {
      int fibonacciNumber = Fibonacci.of(i);
      System.out.println("cycle: " + i + " number: " + fibonacciNumber + " are following possible "
          + countWays(arr, m - 1, fibonacciNumber));
    }
    // try {
    // ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("test.ser"));
    // os.writeObject(table);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
  }
}
