package de.sebhn.algorithm.excercise1.helper;

import de.sebhn.algorithm.excercise1.helper.direction.LeftUp;
import de.sebhn.algorithm.excercise1.helper.direction.Right;
import de.sebhn.algorithm.excercise1.helper.direction.RightDown;
import de.sebhn.algorithm.excercise1.helper.direction.RightUp;
import de.sebhn.algorithm.excercise1.helper.direction.Up;

public class GridVisitor {


  public static OutputCounter findPath(Grid grid, GraphMatrix matrix) {
    int startingPoint = grid.getConverter().convert(new Position(0, 0));
    int endPoint =
        grid.getConverter().convert(new Position(grid.getTargetNumber(), grid.getTargetNumber()));
    int size = grid.getSize();
    OutputCounter outputCounter = new OutputCounter(grid);
    matrix.findAllPath(startingPoint, endPoint, new boolean[size * size], outputCounter);
    return outputCounter;
  }

  public static GraphMatrix createPossibleEdges(Grid grid) {
    int gridSize = grid.getSize();
    GraphMatrix matrix = new GraphMatrix(gridSize * gridSize);
    PositionToIntConverter converter = grid.getConverter();
    boolean isGoneRightUp = false, isGoneRightDown = false, isGoneLeftUp = false, isGoneUp = false,
        isGoneRight = false;
    for (int y = 0; y < gridSize; y++) {
      for (int x = 0; x < gridSize; x++) {
        boolean walkedAllDirections = false;
        Position currentPosition = new Position(x, y);
        while (!walkedAllDirections) {
          if (RightUp.isAllowedToWalk(currentPosition,
              RightUp.getNewPosition(currentPosition, grid), grid) && !isGoneRightUp) {
            Position position = RightUp.getNewPosition(currentPosition, grid);
            matrix.addEdge(converter.convert(currentPosition), converter.convert(position));
            isGoneRightUp = true;
          } else if (RightDown.isAllowedToWalk(currentPosition,
              RightDown.getNewPosition(currentPosition, grid), grid) && !isGoneRightDown) {
            Position position = RightDown.getNewPosition(currentPosition, grid);
            matrix.addEdge(converter.convert(currentPosition), converter.convert(position));
            isGoneRightDown = true;
          } else if (LeftUp.isAllowedToWalk(LeftUp.getNewPosition(currentPosition, grid), grid,
              currentPosition) && !isGoneLeftUp) {
            Position position = LeftUp.getNewPosition(currentPosition, grid);
            matrix.addEdge(converter.convert(currentPosition), converter.convert(position));
            isGoneLeftUp = true;
          } else if (Up.isAllowedToWalk(currentPosition, Up.getNewPosition(currentPosition, grid),
              grid) && !isGoneUp) {
            Position position = Up.getNewPosition(currentPosition, grid);
            matrix.addEdge(converter.convert(currentPosition), converter.convert(position));
            isGoneUp = true;
          } else if (Right.isAllowedToWalk(currentPosition,
              Right.getNewPosition(currentPosition, grid), grid) && !isGoneRight) {
            Position position = Right.getNewPosition(currentPosition, grid);
            matrix.addEdge(converter.convert(currentPosition), converter.convert(position));
            isGoneRight = true;
          } else {
            // System.out.println(String.format(
            // "Possible directions on " + currentPosition
            // + " rightup=%s rightdown=%s leftup=%s up=%s right=%s",
            // isGoneRightUp, isGoneRightDown, isGoneLeftUp, isGoneUp, isGoneRight));
            isGoneRightUp = false;
            isGoneRightDown = false;
            isGoneLeftUp = false;
            isGoneUp = false;
            isGoneRight = false;
            walkedAllDirections = true;
          }
        }
      }
    }
    return matrix;
  }
}
