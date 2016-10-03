package de.sebhn.algorithm.excercise1.helper;

public class StarterHelper {

  public static void main(String[] args) {
    for (int n = 0; n < 20; n++) {
      long start = System.currentTimeMillis();
      Grid grid = new Grid(n);
      System.out.println("n=" + n);
      GraphMatrix matrix = GridVisitor.createPossibleEdges(grid);
      OutputCounter out = GridVisitor.findPath(grid, matrix);
      out.printSize();
      // out.printPaths(); // uncomment to show paths
      System.out.println("duration: " + (System.currentTimeMillis() - start));
    }
  }
}
