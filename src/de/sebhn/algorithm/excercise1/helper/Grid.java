package de.sebhn.algorithm.excercise1.helper;

import java.util.ArrayList;
import java.util.List;

public class Grid {

  private final int targetNumber;
  private final int size;
  private final int[][] internalGrid;
  private final List<Position> diagonal;
  private final PositionToIntConverter converter;

  public Grid(int targetNumber) {
    this.targetNumber = targetNumber;
    size = targetNumber + 1;
    diagonal = new ArrayList<>();
    internalGrid = new int[getSize()][getSize()];
    for (int y = 0; y < getSize(); y++) {
      for (int x = 0; x < getSize(); x++) {
        internalGrid[y][x] = 0;
        if (y == x) {
          getDiagonal().add(new Position(y, x));
        }
      }
    }
    converter = new PositionToIntConverter(this);
  }

  public void visit(int x, int y) {
    internalGrid[y][x] = 1;
  }

  public List<Position> getDiagonal() {
    return diagonal;
  }

  public List<Position> getVisitedPaths() {
    List<Position> visitedPaths = new ArrayList<>();
    for (int y = 0; y < getSize(); y++) {
      for (int x = 0; x < getSize(); x++) {
        boolean visited = internalGrid[y][x] == 1;
        if (visited) {
          visitedPaths.add(new Position(x, y));
        }
      }
    }
    return visitedPaths;
  }

  public int getTargetNumber() {
    return targetNumber;
  }

  public int[][] getInternalGrid() {
    return internalGrid;
  }

  public int getSize() {
    return size;
  }

  public PositionToIntConverter getConverter() {
    return converter;
  }

  public boolean isInGrid(Position position) {
    if (position.getX() > getTargetNumber() || position.getY() > getTargetNumber()) {
      return false;
    } else if (position.getX() < 0 || position.getY() < 0) {
      return false;
    }
    return true;
  }
}
