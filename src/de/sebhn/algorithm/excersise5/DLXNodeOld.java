package de.sebhn.algorithm.excersise5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class DLXNode represents a matrix element of the cover matrix with value 1 links go to up down
 * left right neigbors, and column header can also be used as colm header or root of column headers
 * matrix is sparsely coded try to do all operations very efficiently see:
 * http://en.wikipedia.org/wiki/Dancing_Links http://arxiv.org/abs/cs/0011047
 */
class DLXNodeOld { // represents 1 element or header

  DLXNodeOld C; // reference to column-header
  DLXNodeOld L, R, U, D; // left, right, up, down references
  int posH;
  int posV;
  static int indexLength;
  static DLXNodeOld[] headers;
  static int matrixLine;
  static int maxNumber;
  static int n;

  DLXNodeOld() {
    C = L = R = U = D = this;
  } // supports circular lists


  /**
   * search tries to find and count all complete coverings of the DLX matrix. Is a recursive,
   * depth-first, backtracking algorithm that finds all solutions to the exact cover problem encoded
   * in the DLX matrix. each time all columns are covered, static long cnt is increased
   * 
   * @param int k: number of level
   *
   */
  static int cnt;
  static DLXNodeOld h;

  public static void search(int k) { // finds & counts solutions
    if (h.R == h) {
      cnt++;
      return;
    } // if empty: count & done
    DLXNodeOld c = h.R; // choose next column c
    cover(c); // remove c from columns
    for (DLXNodeOld r = c.D; r != c; r = r.D) { // forall rows with 1 in c
      for (DLXNodeOld j = r.R; j != r; j = j.R) // forall 1-elements in row
        cover(j.C); // remove column
      search(k + 1); // recursion
      for (DLXNodeOld j = r.L; j != r; j = j.L) // forall 1-elements in row
        uncover(j.C); // backtrack: un-remove
    }
    uncover(c); // un-remove c to columns
  }

  /**
   * cover "covers" a column c of the DLX matrix column c will no longer be found in the column list
   * rows i with 1 element in column c will no longer be found in other column lists than c so
   * column c and rows i are invisible after execution of cover
   * 
   * @param DLXNodeOld c: header element of column that has to be covered
   *
   */
  public static void cover(DLXNodeOld c) { // remove column c
    c.R.L = c.L; // remove header
    c.L.R = c.R; // .. from row list
    for (DLXNodeOld i = c.D; i != c; i = i.D) // forall rows with 1
      for (DLXNodeOld j = i.R; i != j; j = j.R) { // forall elem in row
        j.D.U = j.U; // remove row element
        j.U.D = j.D; // .. from column list
      }
  }

  /**
   * uncover "uncovers" a column c of the DLX matrix all operations of cover are undone so column c
   * and rows i are visible again after execution of uncover
   * 
   * @param DLXNodeOld c: header element of column that has to be uncovered
   *
   */
  public static void uncover(DLXNodeOld c) {// undo remove col c
    for (DLXNodeOld i = c.U; i != c; i = i.U) // forall rows with 1
      for (DLXNodeOld j = i.L; i != j; j = j.L) { // forall elem in row
        j.D.U = j; // un-remove row elem
        j.U.D = j; // .. to column list
      }
    c.R.L = c; // un-remove header
    c.L.R = c; // .. to row list
  }

  public static void main(String[] args) {
    cnt = 0; // set counter for solutions to zero
    h = new DLXNodeOld(); // create header
    h.posH = 0;
    h.posV = 0;

    n = 3;

    matrixLine = 1;
    maxNumber = n * 6;

    addHeader(maxNumber);

    /*
     * for (int i = 0; i < amountOfHeaders; i++) { DLXNodeOld columnNode = gotoIndex(i);
     * System.out.println("Header-Column: " + columnNode.posH); System.out.println("Row x: " +
     * columnNode.posV); DLXNodeOld verticaclNode = columnNode.D; System.out.println("Row o: " +
     * verticaclNode.posV); do { verticaclNode = verticaclNode.D; // if (verticaclNode.posV != -1) {
     * System.out.println("Row i: " + verticaclNode.posV); // } } while
     * (!columnNode.equals(verticaclNode)); }
     */


    long start = System.nanoTime();
    calcCross();
    // calcMono();
    // calcU_UP();
    // calcU_DOWN();
    // calcU_LEFT();
    // calcU_RIGHT();
    // calcL_R0();
    // calcL_R1();
    // calcL_R2();
    // calcL_R3();

    long ende = System.nanoTime();
    System.out.println((ende - start) / 10000 + "ms for matrix generation");

    // DLXNodeOld node = h.R;
    // for (int i = 0; i < maxNumber + 1; i++) {
    // System.out.print(node.posV + " " + node.posH + " | ");
    // node = node.R;
    // }

    start = System.nanoTime();
    search(0);
    ende = System.nanoTime();
    System.out.println((ende - start) / 1000000000 + "s for search");
    System.out.println(cnt);
  }

  /**
   * create headers
   */
  public static void addHeader(int n) {
    indexLength = n;
    headers = new DLXNodeOld[n + 1];
    headers[0] = h;
    DLXNodeOld tempNode;
    int x = 0;
    for (int i = 0; i < n; i++) {
      DLXNodeOld node = new DLXNodeOld();
      node.posH = ++x; // set index to header
      node.posV = 0; // set vertical position to 0 to signal header
      headers[x] = node;
      if (h.L == h) {
        // connect header to node
        h.L = node;
        h.R = node;
        // connect node to header
        node.L = h;
        node.R = h;
      } else {
        tempNode = h.L; // goto last header
        tempNode.R = node; // connect old last node to new node
        node.L = tempNode; // connect new node to old last node
        node.R = h; // connect last new node to header
        h.L = node; // connect header to last new node
      }
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + posH;
    result = prime * result + posV;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    DLXNodeOld other = (DLXNodeOld) obj;

    if (posH != other.posH) {
      return false;
    } else if (posV != other.posV) {
      return false;
    }
    return true;
  }

  private static DLXNodeOld gotoIndex(int posH) {
    DLXNodeOld node = new DLXNodeOld();
    node = h.R;
    for (int i = 0; i < posH; i++) {
      node = node.R;
    }
    return node;
  }

  private static DLXNodeOld gotoHeaderIndex(int posH) {
    return headers[posH];
  }

  private static void addNode(int posV, int posH) {
    System.out.println("add node: posV=" + posV + " posH=" + posH);
    DLXNodeOld node = new DLXNodeOld();
    DLXNodeOld temp = gotoHeaderIndex(posH); // goto header index
    node.posH = posH;
    node.posV = posV;
    node.C = temp; // direct link to header
    if (temp.D == node.C) { // add node if there are no other nodes
      temp.U = node;
      temp.D = node;
      node.U = temp;
      node.D = temp;
      connectRight(node);
      connectLeft(node);
      return;
    } else {
      for (int i = 0; i < 6; i++) {
        temp = temp.D;
        if (temp.posV < posV) {
          temp = temp.D;
          if (temp == temp.C) { // add node if its the highest index
            temp = temp.U;
            temp.D = node;
            node.U = temp;
            node.D = node.C;
            node.C.U = node;
            connectRight(node);
            connectLeft(node);
            return;
          }
        }
        if (temp.posV > posV) {
          temp = temp.U;
          node.D = temp.D; // connect node to higher node
          temp.D.U = node; // connect higher node to node
          temp.D = node; // connect lower node to node
          node.U = temp; // connect node to lower node}
          connectRight(node);
          connectLeft(node);
          return;
        }
      }
    }
  }

  public static void connectRight(DLXNodeOld node) {
    // connection to the right
    int posV = node.posV;
    DLXNodeOld temp = node;
    for (int i = 0; i < indexLength; i++) {
      temp = temp.C.R;
      while (temp.D != temp.C) {
        temp = temp.D;
        if (temp.posV == posV) {
          node.R = temp;
          temp.L = node;
          // System.out.println("connected right " + node.posV + " " + node.posH + " mit " +
          // temp.posV
          // + " " + temp.posH);
          return;
        } else {
        }
      }
    }
  }

  public static void connectLeft(DLXNodeOld node) {
    // connection to the right
    int posV = node.posV;
    DLXNodeOld temp = node;
    for (int i = 0; i < indexLength; i++) {
      temp = temp.C.L;
      while (temp.D != temp.C) {
        temp = temp.D;
        if (temp.posV == posV) {
          temp.R = node;
          node.L = temp;
          // System.out.println("connected left " + node.posV + " " + node.posH + " mit " +
          // temp.posV
          // + " " + temp.posH);
          return;
        } else {
        }
      }
    }
  }

  public static void calcCross() {
    int a = 2;
    int b = 7;
    int c = 8;
    int d = 9;
    int e = 14;

    List<Integer> figure = Arrays.asList(a, b, c, d, e);
    int width = 3;
    int height = 3;
    calculateFiguresPosition(figure, height, n - width);

  }

  public static void calcU_UP() {
    if (n > 2) {
      calculateFiguresPosition(Arrays.asList(1, 2, 13, 8, 14), 4);
    }
  }

  public static void calcU_DOWN() {
    if (n > 2) {
      calculateFiguresPosition(Arrays.asList(1, 2, 7, 13, 14), 4);
    }
  }

  public static void calcU_LEFT() {
    if (n > 1) {
      calculateFiguresPosition(Arrays.asList(1, 3, 7, 9, 8), 3);
    }
  }

  public static void calcU_RIGHT() {
    if (n > 1) {
      calculateFiguresPosition(Arrays.asList(1, 2, 3, 9, 7), 3);
    }
  }

  public static void calcL_R0() {
    if (n > 1) {
      calculateFiguresPosition(Arrays.asList(1, 2, 3, 4, 10), 2);
    }
  }

  public static void calcL_R1() {
    if (n > 3) {
      calculateFiguresPosition(Arrays.asList(2, 8, 14, 20, 19), 4);
    }
  }

  public static void calcL_R2() {
    if (n > 1) {
      calculateFiguresPosition(Arrays.asList(1, 7, 8, 9, 10), 2);
    }
  }

  public static void calcL_R3() {
    if (n > 3) {
      calculateFiguresPosition(Arrays.asList(1, 2, 13, 7, 19), 4);
    }
  }



  public static void calcMono() {
    for (int i = 0; i < maxNumber; i++) {
      addNode(matrixLine, i + 1);
      matrixLine++;
    }
  }

  private static void calculateFiguresPosition(List<Integer> figures, int height, int width) {
    if (width > n || width < 0) {
      System.out.println("no positions, cant fit figure");
      return;
    }
    for (Integer integer : figures) {
      addNode(matrixLine, integer);
    }
    matrixLine++;

    for (int i = 0; i <= height; i++) {
      ArrayList<Integer> plusSixFigures = new ArrayList<>(figures);
      for (int g = 0; g < width; g++) {
        for (int j = 0; j < plusSixFigures.size(); j++) {
          Integer current = plusSixFigures.get(j);
          current += 6;
          plusSixFigures.set(j, current);

          addNode(matrixLine, current);
        }
        matrixLine++;
      }


      for (int j = 0; j < figures.size(); j++) {
        int elementPlusOne = figures.get(j) + 1;
        figures.set(j, elementPlusOne);
        addNode(matrixLine, elementPlusOne);
      }
      matrixLine++;
    }
  }

  private static void calculateFiguresPosition(List<Integer> figures, int downShifts) {
    for (Integer integer : figures) {
      addNode(matrixLine, integer);
    }
    matrixLine++;

    for (int i = 0; i <= downShifts; i++) {
      ArrayList<Integer> plusSixFigures = new ArrayList<>(figures);
      int currentMaxHorizontal = plusSixFigures.get(plusSixFigures.size() - 1) + 6;
      while (currentMaxHorizontal <= maxNumber) {
        for (int j = 0; j < plusSixFigures.size(); j++) {
          Integer current = plusSixFigures.get(j);
          current += 6;
          plusSixFigures.set(j, current);

          addNode(matrixLine, current);
        }
        matrixLine++;
        currentMaxHorizontal = plusSixFigures.get(plusSixFigures.size() - 1) + 6;
      }

      int currentMaxVertical = figures.get(figures.size() - 2) + 1;
      boolean hasNotReachedVerticalEnd = currentMaxVertical < 13;
      for (int j = 0; j < figures.size() && hasNotReachedVerticalEnd; j++) {
        int elementPlusOne = figures.get(j) + 1;
        figures.set(j, elementPlusOne);
        addNode(matrixLine, elementPlusOne);
      }
      if (hasNotReachedVerticalEnd) {
        matrixLine++;
      }
    }
  }

}
