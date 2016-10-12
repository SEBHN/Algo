package de.sebhn.algorithm.excercise3;

import java.math.BigInteger;
import java.util.HashMap;

public class fibonacci {
  static int betrag[] = {1, 3, 7, 31, 47};
  static int n = betrag.length; // Anzahl Muenzen

  static BigInteger w[][]; // Tabelle

  public static void main(String[] args) {
    for (int i = 0; i < 37; i++) {
      int g = F(i).intValue(); // g lesen
      int gPlusOne = g + 1;
      w = new BigInteger[gPlusOne][n]; // w dimensionieren
      for (int j = 0; j < gPlusOne; j++) {
        for (int l = 0; l < n; l++) {
          w[j][l] = BigInteger.ZERO;
        }
      }
      System.out.println(
          "Den Betrag " + g + " kann man auf " + w(g, n - 1) + " verschiedene Arten wecheln.");
    }
  }

  static HashMap<Integer, BigInteger> F = new HashMap<>(); // dynamic table

  public static BigInteger F(int n) { // method
    if (n <= 1) {
      return BigInteger.valueOf(n);
    } // base case
    BigInteger m = F.get(n); // lookup
    if (m != null)
      return m; // sucessful
    m = F(n - 1).add(F(n - 2)); // recursion
    F.put(n, m); // store
    return m; // finish
  }


  static BigInteger w(int g, int i) { // Methode
    return g < 0 ? BigInteger.ZERO
        : i == 0 ? (g % betrag[0] == 0 ? BigInteger.ONE : BigInteger.ZERO)
            : !w[g][i].toString().equals("0") ? w[g][i]
                : (w[g][i] = w(g, i - 1).add(w(g - betrag[i], i)));
  }
}
