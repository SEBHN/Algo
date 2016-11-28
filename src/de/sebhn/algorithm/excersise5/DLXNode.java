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
class DLXNode { // represents 1 element or header

  DLXNode C; // reference to column-header
  DLXNode L, R, U, D; // left, right, up, down references
  int posH;
  int posV;
  static int indexLength;
  static DLXNode[] headers;
  static int matrixLine;
  static int maxNumber;
  static int n;

  DLXNode() {
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
  static DLXNode h;

  public static void search(int k) { // finds & counts solutions
    if (h.R == h) {
      cnt++;
      return;
    } // if empty: count & done
    DLXNode c = h.R; // choose next column c
    cover(c); // remove c from columns
    for (DLXNode r = c.D; r != c; r = r.D) { // forall rows with 1 in c
      for (DLXNode j = r.R; j != r; j = j.R) // forall 1-elements in row
        cover(j.C); // remove column
      search(k + 1); // recursion
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
   * @param DLXNode c: header element of column that has to be covered
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
   * @param DLXNode c: header element of column that has to be uncovered
   *
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
    cnt = 0; // set counter for solutions to zero
    h = new DLXNode(); // create header
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
    calcMono();
    calcU_UP();
    calcU_DOWN();
    calcU_LEFT();
    calcU_RIGHT();
    calcL_R0();
    calcL_R1();
    calcL_R2();
    calcL_R3();
    calcL_R4();
    calcL_R5();
    calcL_R6();
    calcL_R7();

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
    headers = new DLXNode[n + 1];
    headers[0] = h;
    DLXNode tempNode;
    int x = 0;
    for (int i = 0; i < n; i++) {
      DLXNode node = new DLXNode();
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
    DLXNode other = (DLXNode) obj;

    if (posH != other.posH) {
      return false;
    } else if (posV != other.posV) {
      return false;
    }
    return true;
  }

  private static DLXNode gotoIndex(int posH) {
    DLXNode node = new DLXNode();
    node = h.R;
    for (int i = 0; i < posH; i++) {
      node = node.R;
    }
    return node;
  }

  private static DLXNode gotoHeaderIndex(int posH) {
    return headers[posH];
  }

  private static void addNode(int posV, int posH) {
    System.out.println("add node: posV=" + posV + " posH=" + posH);
    DLXNode node = new DLXNode();
    DLXNode temp = gotoHeaderIndex(posH); // goto header index
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

  public static void connectRight(DLXNode node) {
    // connection to the right
    int posV = node.posV;
    DLXNode temp = node;
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

  public static void connectLeft(DLXNode node) {
    // connection to the right
    int posV = node.posV;
    DLXNode temp = node;
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
    calculateFiguresPosition(figure, height, width);

  }

  public static void calcU_UP() {
    calculateFiguresPosition(Arrays.asList(1, 2, 8, 13, 14), 4, 3);
  }

  public static void calcU_DOWN() {
    calculateFiguresPosition(Arrays.asList(1, 2, 7, 13, 14), 4, 3);
  }

  public static void calcU_LEFT() {
    calculateFiguresPosition(Arrays.asList(1, 3, 7, 9, 8), 3, 2);
  }

  public static void calcU_RIGHT() {
    calculateFiguresPosition(Arrays.asList(1, 2, 3, 9, 7), 3, 2);
  }

  /**
   * |<br>
   * |<br>
   * |_<br>
   */
  public static void calcL_R0() {
    calculateFiguresPosition(Arrays.asList(1, 2, 3, 4, 10), 2, 2);
  }

  /**
   * ___|
   */
  public static void calcL_R1() {
    calculateFiguresPosition(Arrays.asList(2, 8, 14, 20, 19), 4, 4);
  }


  /**
   * _<br>
   * .|<br>
   * .|<br>
   * .|<br>
   * .|
   */
  public static void calcL_R2() {
    calculateFiguresPosition(Arrays.asList(1, 7, 8, 9, 10), 2, 2);
  }

  /**
   * .____<br>
   * |
   */
  public static void calcL_R3() {
    calculateFiguresPosition(Arrays.asList(1, 2, 13, 7, 19), 4, 4);
  }

  /**
   * .|<br>
   * .|<br>
   * _|<br>
   */
  public static void calcL_R4() {
    calculateFiguresPosition(Arrays.asList(7, 8, 9, 10, 4), 2, 2);
  }

  /**
   * ____<br>
   * ****|
   */
  public static void calcL_R5() {
    calculateFiguresPosition(Arrays.asList(1, 7, 13, 19, 20), 4, 4);
  }

  /**
   * |___
   */
  public static void calcL_R6() {
    calculateFiguresPosition(Arrays.asList(1, 2, 8, 14, 20), 4, 4);
  }

  /**
   * ._<br>
   * |<br>
   * |<br>
   * |
   */
  public static void calcL_R7() {
    calculateFiguresPosition(Arrays.asList(1, 2, 3, 4, 7), 2, 2);
  }



  public static void calcMono() {
    for (int i = 0; i < maxNumber; i++) {
      addNode(matrixLine, i + 1);
      matrixLine++;
    }
  }

  private static void calculateFiguresPosition(List<Integer> figures, int downShifts, int width) {
    int shiftsRight = n - width;
    if (shiftsRight > n || shiftsRight < 0) {
      System.out.println("no positions, cant fit figure");
      return;
    }
    insertFigure(figures);
    for (int i = 0; i < downShifts; i++) {
      List<Integer> plusSixFigures = new ArrayList<>(figures);
      shiftRight(shiftsRight, plusSixFigures);
      shiftDown(figures);
    }
    shiftRight(shiftsRight, figures);
  }

  private static void shiftRight(int shiftsRight, List<Integer> plusSixFigures) {
    for (int g = 0; g < shiftsRight; g++) {
      shiftOneRight(plusSixFigures);
    }
  }

  private static void insertFigure(List<Integer> figures) {
    DLXNode[] array = new DLXNode[5];
    int arrayIndex = 0;
    for (Integer integer : figures) {
      // addNode(matrixLine, integer);
      array[arrayIndex] = createNode(matrixLine, integer);
      arrayIndex++;
    }
    createLine(array);
    matrixLine++;
  }

  private static void shiftDown(List<Integer> figures) {
    DLXNode[] array = new DLXNode[5];
    int arrayIndex = 0;
    for (int j = 0; j < figures.size(); j++) {
      int elementPlusOne = figures.get(j) + 1;
      figures.set(j, elementPlusOne);
      // addNode(matrixLine, elementPlusOne);
      array[arrayIndex] = createNode(matrixLine, elementPlusOne);
      arrayIndex++;
    }
    createLine(array);
    matrixLine++;
  }

  private static void shiftOneRight(List<Integer> plusSixFigures) {
    DLXNode[] array = new DLXNode[5];
    int arrayIndex = 0;
    for (int j = 0; j < plusSixFigures.size(); j++) {
      Integer current = plusSixFigures.get(j);
      current += 6;
      plusSixFigures.set(j, current);

      // addNode(matrixLine, current);

      array[arrayIndex] = createNode(matrixLine, current);
      arrayIndex++;
    }
    createLine(array);
    matrixLine++;
  }

  private static void calculateFiguresPosition(List<Integer> figures, int downShifts) {
    insertFigure(figures);
    DLXNode[] al = new DLXNode[5];
    int arrayIndex = 0;

    for (int i = 0; i <= downShifts; i++) {
      ArrayList<Integer> plusSixFigures = new ArrayList<>(figures);
      int currentMaxHorizontal = plusSixFigures.get(plusSixFigures.size() - 1) + 6;
      while (currentMaxHorizontal <= maxNumber) {
        shiftOneRight(plusSixFigures);
        currentMaxHorizontal = plusSixFigures.get(plusSixFigures.size() - 1) + 6;
      }

      int currentMaxVertical = figures.get(figures.size() - 2) + 1;
      boolean hasNotReachedVerticalEnd = currentMaxVertical < 13;
      for (int j = 0; j < figures.size() && hasNotReachedVerticalEnd; j++) {
        int elementPlusOne = figures.get(j) + 1;
        figures.set(j, elementPlusOne);
        // addNode(matrixLine, elementPlusOne);
        al[arrayIndex] = createNode(matrixLine, elementPlusOne);
        arrayIndex++;
      }
      if (hasNotReachedVerticalEnd) {
        matrixLine++;
        createLine(al);
      }
      al = new DLXNode[5];
    }
  }

  private static DLXNode createNode(int posV, int posH) {
    System.out.println("add node: posV=" + posV + " posH=" + posH);
    DLXNode node = new DLXNode();
    node.posV = posV;
    node.posH = posH;
    return node;
  }

  private static void createLine(DLXNode[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i].C = gotoHeaderIndex(array[i].posH);
      array[i].U = gotoHeaderIndex(array[i].posH).U;
      gotoHeaderIndex(array[i].posH).U.D = array[i];
      array[i].D = gotoHeaderIndex(array[i].posH);
      gotoHeaderIndex(array[i].posH).U = array[i];
      if (i == 0) {
        array[i].L = array[array.length - 1];
        array[i].R = array[i + 1];
      } else if (i == array.length - 1) {
        array[i].L = array[i - 1];
        array[i].R = array[0];
      } else {
        array[i].L = array[i - 1];
        array[i].R = array[i + 1];
      }
    }
  }
}
