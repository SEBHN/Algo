package de.sebhn.algorithm.excersise5;

/**
 * Class DLXNode represents a matrix element of the cover matrix with value 1 links go to up down
 * left right neigbors, and column header can also be used as colm header or root of column headers
 * matrix is sparsely coded try to do all operations very efficiently see:
 * http://en.wikipedia.org/wiki/Dancing_Links http://arxiv.org/abs/cs/0011047
 */
class DLXNode { // represents 1 element or header

  DLXNode C; // reference to column-header
  DLXNode L, R, U, D; // left, right, up, down references
  int var;
  int posH;
  int posV;

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
    h.var = -1;
    h.posH = -1;
    h.posV = -1;
    DLXNode node = h;
    addHeader(15);

    do {
      node = node.R;
      System.out.println(node.getVar());
      cnt++;
    } while (cnt < 25);
  }

  /**
   * create headers
   */
  public static void addHeader(int n) {
    DLXNode tempNode;
    int x = 0;
    for (int i = 0; i < n; i++) {
      DLXNode node = new DLXNode();
      node.var = x++; // add index to headers
      node.posH = -1; // set horizontal position to -1 to signal header
      node.posV = -1; // set vertical position to -1 to signal header
      if (h.L == h) {
        // connect header to node and node to header
        h.L = node;
        h.R = node;
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

  private static void addNode(int posH, int posV) {
    DLXNode node = new DLXNode();
  }

  /**
   * delete later
   * 
   * @return head index
   */
  public int getVar() {
    return var;
  }
}
