package de.sebhn.algorithm.excersise4;

/**
 * 
 * @author Phi Long Tran <191624>
 * @author Dimitri Kapcikov <191510>
 * @author Manuel Wessner <191711>
 */
public class Perm extends Thread {
  private int[] a; // a Arbeitsarray
  private int max; // maximaler Index
  private boolean mayread = false; // Kontrolle

  Perm(int n) { // Konstruktor
    a = new int[n]; // Indices: 0 .. n-1
    max = n - 1; // Maximaler Index
    for (int i = 0; i <= max;) {
      a[i] = i++; // a fuellen
    }
    this.start(); // run-Methode beginnt zu laufen
  }

  public void run() {// die Arbeits-Methode
    perm(1); // a[0] bleibt fest; permutiere ab 1
    a = null; // Anzeige, dass fertig
    put(); // ausliefern
  }

  private void perm(int i) { // permutiere ab Index i
    if (i >= max) {
      boolean haveAllDifferences8 = hasPermutationDifferenceOf8();
      if (haveAllDifferences8) {
        put(); // eine Permutation fertig
      }
    } else {
      for (int j = i; j <= max; j++) { // jedes nach Vorne
        swap(i, j); // vertauschen
        perm(i + 1); // und Rekursion
      }
      int h = a[i]; // restauriere Array
      System.arraycopy(a, i + 1, a, i, max - i); // shift links
      a[max] = h;
    }
  }

  private boolean hasPermutationDifferenceOf8() {
    boolean haveAllDifferences8 = true;
    int testDifference = 8;
    for (int j = a.length - 1; j >= 0; j--) {
      int nextIndex = j - 1;
      int lastElement = a[a.length - 1];
      if (lastElement < testDifference) {
        haveAllDifferences8 = false;
        break;
      }
      if (nextIndex >= 0) {
        int entry = a[j];
        int nextEntry = a[nextIndex];

        int difference = Math.abs(entry - nextEntry);
        if (difference < testDifference) {
          haveAllDifferences8 = false;
          break;
        }
      }
    }
    return haveAllDifferences8;
  }

  private void swap(int i, int j) { // tausche a[i] <-> a[j]
    if (i != j) {
      int h = a[i];
      a[i] = a[j];
      a[j] = h;
    }
  }

  synchronized int[] getNext() { // liefert naechste Permutation
    mayread = false; // a darf geaendert werden
    notify(); // wecke anderen Thread
    try {
      while (!mayread) {
        wait(); // non busy waiting
      }
    } catch (InterruptedException e) {
    }
    return a; // liefere Permutationsarray
  }

  private synchronized void put() { // uebergebe array
    mayread = true; // a wird gelesen
    notify(); // wecke anderen Thread
    try {
      if (a != null) {
        while (mayread) {
          wait(); // non busy waiting
        }
      }
    } catch (InterruptedException e) {
    }
  }
}
