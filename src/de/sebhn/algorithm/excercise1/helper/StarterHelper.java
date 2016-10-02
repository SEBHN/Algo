package de.sebhn.algorithm.excercise1.helper;

import de.sebhn.algorithm.excercise1.OutputCounter;

public class StarterHelper {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int n = 3;
    Grid grid = new Grid(n);
    System.out.println("n=" + n);
    GraphMatrix matrix = GridVisitor.createPossibleEdges(grid);
    OutputCounter out = GridVisitor.findPath(grid, matrix);
    out.printSize();
    // outputCounter.printPaths(grid.getTargetNumber()); // uncomment to show paths
    System.out.println("duration: " + (System.currentTimeMillis() - start));
  }
}
