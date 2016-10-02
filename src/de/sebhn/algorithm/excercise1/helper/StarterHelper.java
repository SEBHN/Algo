package de.sebhn.algorithm.excercise1.helper;

public class StarterHelper {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    Grid grid = new Grid(3);
    GraphMatrix matrix = GridVisitor.createPossibleEdges(grid);
    GridVisitor.findPath(grid, matrix);
    System.out.println("duration: " + (System.currentTimeMillis() - start));
  }
}
