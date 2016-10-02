package de.sebhn.algorithm.excercise1.helper;

public class StarterHelper {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int n = 3;
    Grid grid = new Grid(n);
    System.out.println("n=" + n);
    GraphMatrix matrix = GridVisitor.createPossibleEdges(grid);
    GridVisitor.findPath(grid, matrix);
    System.out.println("duration: " + (System.currentTimeMillis() - start));
  }
}
