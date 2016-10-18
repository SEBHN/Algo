package de.sebhn.algorithm.excercise3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CoinChanger {


  static BigInteger countWays(int S[], int n) {
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
    int m = S.length;
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


    BigInteger[] results = readIfPossible();

    for (int i = 37; i <= 40; i++) {
      int fibonacciNumber = Fibonacci.of(i);
      System.out.println("cycle: " + i + " number: " + fibonacciNumber + " are following possible "
          + maxCoinsTest(fibonacciNumber, arr, results));
    }
    writeObject(results);
  }

  private static void writeObject(BigInteger[] results) {
    Path file = Paths.get("test.ser");
    try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(file));) {
      os.writeObject(results);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static BigInteger[] readIfPossible() {
    Path file = Paths.get("test.ser");
    if (Files.exists(file)) {
      try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
        BigInteger[] readObject = (BigInteger[]) ois.readObject();
        return readObject;
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    BigInteger[] results = new BigInteger[102334155 + 1];
    Arrays.fill(results, BigInteger.ZERO);
    results[0] = BigInteger.ONE;
    return results;
  }

  private static BigInteger maxCoins(int n, int[] coins) {
    BigInteger[] results = new BigInteger[n + 1];
    Arrays.fill(results, BigInteger.ZERO);
    results[0] = BigInteger.ONE;
    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= n; j++) {
        results[j] = results[j].add(results[j - coins[i]]);
      }
    }

    return results[n];
  }

  private static BigInteger maxCoinsTest(int n, int[] coins, BigInteger[] results) {
    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= n; j++) {
        results[j] = results[j].add(results[j - coins[i]]);
      }
    }

    return results[n];
  }
}
