package de.sebhn.algorithm.excercise1.helper;

public class HelperStarter {

  public static void main(String[] args) {
    Grid grid = new Grid(3);
    GraphMatrix matrix = GridVisitor.createPossibleEdges(grid);
    GridVisitor.findPath(grid, matrix);
  }
}
