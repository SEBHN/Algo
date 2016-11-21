package de.sebhn.algorithm.excersise5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class DLXNode represents a matrix element of the cover matrix with value 1 links go to up down
 * left right neigbors, and column header can also be used as column header or root of column
 * headers matrix is sparsely coded try to do all operations very efficiently.
 */
class DLXNode { // represents 1 element or header
  DLXNode C; // reference to column-header
  DLXNode L, R, U, D; // left, right, up, down references

  DLXNode() {
    C = L = R = U = D = this;
  } // supports circular lists
}


public class PentominoZIPDLX {
  static DLXNode h, kopfTeil[];
  static long cnt;
  static int n;

  /**
   * Zahl wird von der Konsole eingelesen. Mit dieser Zahl wird dann das Programm gestartet.
   */
  public PentominoZIPDLX() {
    cnt = 0;
    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      System.out.print("Gib dein n ein: ");
      n = Integer.parseInt(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (n > 0) {
      matrixKonfigurieren();
      System.out.println("\n\nn=" + n + "\t\ta(" + n + ")=" + cnt);
    } else if (n < 0) {
      System.out.println("Bitte nur positive Werte eingeben!");
    } else {
      System.out.println("\n\nn=" + n + "\t\ta(" + n + ")= 1");
    }
  }

  /**
   * @param i Ist der aktuelle Index aus der For-Schleife von der Methode "kopfteilVerschieben".
   * @param mod Kann je nach Pentomino den Fall 1 oder 2 Annehmen.
   * @return boolean
   */
  public static boolean ueberpruefeModulo(int i, int mod) {
    return i % 6 + mod < 6 ? true : false;
  }

  /**
   * @param t Parameter ist ein int-Array und beinhaltet die notwendigen Zahlen, um den Pentomino zu
   *        beschreiben.
   * @param mod mod==1 bedeutet, dass in der Modulo Funktion, der Modulo von 5+1 genommen wird.
   *        mod==2 bedeutet, dass in der Modulo Funktion, der Modulo von 5+2 genommen wird.
   * @param plus4 Wenn plus4 true ist, dann wird der Index der For-Schleife um 5 anstatt um 1
   *        erhöht.
   */
  public static void kopfteilVerschieben(int[] t, int mod, boolean plus4) {
    boolean moduloErgebnis = true;

    for (int i = 0; i + t[5] <= 6 * n - 1; i++) {
      DLXNode[] dlxArray = {new DLXNode(), new DLXNode(), new DLXNode(), new DLXNode(),
          new DLXNode(), new DLXNode()};

      if (mod >= 1) {
        moduloErgebnis = ueberpruefeModulo(i, mod);
      }

      if (moduloErgebnis) {
        for (int m = 0; m < dlxArray.length; m++) {
          dlxArray[m].U = kopfTeil[i + t[m]].U;
          dlxArray[m].D = kopfTeil[i + t[m]];

          kopfTeil[i + t[m]].U.D = dlxArray[m];
          kopfTeil[i + t[m]].U = dlxArray[m];
          dlxArray[m].C = kopfTeil[i + t[m]];
          dlxArray[m].R = dlxArray[m + 1 > 4 ? 0 : m + 1];
          dlxArray[m].L = dlxArray[m - 1 < 0 ? 4 : m - 1];
        }
      }
      if (plus4) {
        i += 4;
      }
    }
  }

  /**
   * Der Matrix werden initiale Einstellungen mitgegeben und der Kopfteil wird erstellt und
   * zugewiesen.
   */
  public static void matrixKonfigurieren() {
    h = new DLXNode();
    kopfTeil = new DLXNode[6 * n];
    kopfTeil[0] = new DLXNode();

    for (int i = 1; i < 6 * n; i++) {
      kopfTeil[i] = new DLXNode();
      kopfTeil[i].L = kopfTeil[i - 1];
      kopfTeil[i].R = kopfTeil[0];
      kopfTeil[i - 1].R = kopfTeil[i];
      kopfTeil[0].L = kopfTeil[i];
    }
    h.R = kopfTeil[0];
    h.L = kopfTeil[kopfTeil.length - 1];
    kopfTeil[0].L = h;
    kopfTeil[kopfTeil.length - 1].R = h;

    // I1-Pentomino
    // kopfteilVerschieben(new int[] {0, 5, 10, 15, 20}, 0, false);
    // L-Pentomino (vertical, right)
    kopfteilVerschieben(new int[] {0, 1, 2, 3, 9}, 0, false);

    // I2-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 2, 3, 4}, 0, true);

    // P1-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 5, 6, 10}, 1, false);

    // P2-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 5, 6, 11}, 1, false);

    // P3-Pentomino
    kopfteilVerschieben(new int[] {0, 5, 6, 10, 11}, 1, false);

    // P4-Pentomino
    kopfteilVerschieben(new int[] {1, 5, 6, 10, 11}, 1, false);

    // P5-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 2, 6, 7}, 2, false);

    // P6-Pentomino
    kopfteilVerschieben(new int[] {1, 2, 5, 6, 7}, 2, false);

    // P7-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 5, 6, 7}, 2, false);

    // P8-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 2, 5, 6}, 2, false);

    // Z1-Pentomino
    kopfteilVerschieben(new int[] {0, 5, 6, 7, 12}, 2, false);

    // Z2-Pentomino
    kopfteilVerschieben(new int[] {2, 5, 6, 7, 10}, 2, false);

    // Z3-Pentomino
    kopfteilVerschieben(new int[] {0, 1, 6, 11, 12}, 2, false);

    // Z4-Pentomino
    kopfteilVerschieben(new int[] {1, 2, 6, 10, 11}, 2, false);

    sucheUndZaehle(0);
  }

  /**
   * search tries to find and count all complete coverings of the DLX matrix. Is a recursive,
   * depth-first, backtracking algorithm that finds all solutions to the exact cover problem encoded
   * in the DLX matrix. each time all columns are covered, static long cnt is increased
   * 
   * @param int k: number of level
   */
  public static void sucheUndZaehle(int k) { // finds & counts solutions
    if (h.R == h) {
      cnt++;
      return;
    } // if empty: count & done
    DLXNode c = h.R; // choose next column c
    cover(c); // remove c from columns
    for (DLXNode r = c.D; r != c; r = r.D) { // forall rows with 1 in c
      for (DLXNode j = r.R; j != r; j = j.R) // forall 1-elements in row
        cover(j.C);
      sucheUndZaehle(k + 1);
      for (DLXNode j = r.L; j != r; j = j.L) // forall 1-elements in row
        uncover(j.C); // backtrack: un-remove
    }
    uncover(c); // un-remove c to columns
  }

  /**
   * cover "covers" a column c of the DLX matrix column c will no longer be found in the column list
   * rows i with 1 element in column c will no longer be found in other column lists than c so
   * column c and rows i are invisible after execution of cover
   * 
   * @param DLXNode c: kopfTeil element of column that has to be covered
   *
   */
  public static void cover(DLXNode c) { // remove column c
    c.R.L = c.L; // remove header
    c.L.R = c.R; // .. from row list
    for (DLXNode i = c.D; i != c; i = i.D) // forall rows with 1
      for (DLXNode j = i.R; i != j; j = j.R) { // forall elem in row
        j.D.U = j.U; // remove row element
        j.U.D = j.D; // .. from column list
      }
  }

  /**
   * uncover "uncovers" a column c of the DLX matrix all operations of cover are undone so column c
   * and rows i are visible again after execution of uncover
   * 
   * @param DLXNode c: kopfTeil element of column that has to be uncovered
   */
  public static void uncover(DLXNode c) {// undo remove col c
    for (DLXNode i = c.U; i != c; i = i.U) // forall rows with 1
      for (DLXNode j = i.L; i != j; j = j.L) { // forall elem in row
        j.D.U = j; // un-remove row elem
        j.U.D = j; // .. to column list
      }
    c.R.L = c; // un-remove header
    c.L.R = c; // .. to row list
  }

  public static void main(String[] args) {
    new PentominoZIPDLX();
  }
}
