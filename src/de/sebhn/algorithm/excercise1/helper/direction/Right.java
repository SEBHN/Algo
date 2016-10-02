package de.sebhn.algorithm.excercise1.helper.direction;


import de.sebhn.algorithm.excercise1.helper.Grid;
import de.sebhn.algorithm.excercise1.helper.Position;

public class Right implements Walkable {

  public static Position getNewPosition(Position currentPosition, Grid grid) {
    Position newPosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
    return newPosition;
  }

  public static boolean isAllowedToWalk(Position currentPosition, Position newPosition, Grid grid) {
    return Diagonal.isUnderOrOn(newPosition) && Diagonal.isUnderOrOn(currentPosition)
        && grid.isInGrid(newPosition);
  }
}
