package de.sebhn.algorithm.exercise4_5;

/**
 * 
 * @author Phi Long Tran <191624>
 * @author Dimitri Kapcikov <191510>
 * @author Manuel Wessner <191711>
 */
public class Perm extends Thread {
  static int runs;

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

  @Override
  public void run() {// die Arbeits-Methode
    perm(0); // a[0] bleibt fest; permutiere ab 1
    a = null; // Anzeige, dass fertig
    put(); // ausliefern
  }

  public static void main(String[] args) {
    runs = 0;
    Perm perm = new Perm(10);
    int[] next = perm.getNext();
    while (next != null) {
      // System.out.println(Arrays.toString(next));
      next = perm.getNext();
    }
    System.out.println(runs);
  }


  private void perm(int i) { // permutiere ab Index i
    runs++;
    if (i >= max) {
      put(); // eine Permutation fertig
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
