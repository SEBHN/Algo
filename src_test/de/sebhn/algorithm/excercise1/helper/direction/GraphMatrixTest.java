package de.sebhn.algorithm.excercise1.helper.direction;

import de.sebhn.algorithm.excercise1.helper.GraphMatrix;

public class GraphMatrixTest {

  public static void main(String[] args) {

    GraphMatrix gm = new GraphMatrix(4);
    gm.addEdge(0, 1);
    gm.addEdge(0, 2);
    gm.addEdge(0, 3);
    gm.addEdge(1, 3);
    gm.addEdge(2, 0);
    gm.addEdge(2, 1);

    gm.findAllPath(gm, 2, 3, new boolean[10]);
    System.exit(0);
  }
}
