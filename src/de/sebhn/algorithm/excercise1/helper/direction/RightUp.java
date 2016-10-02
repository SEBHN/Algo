package de.sebhn.algorithm.excercise1.helper.direction;


import de.sebhn.algorithm.excercise1.helper.Grid;
import de.sebhn.algorithm.excercise1.helper.Position;

public class RightUp implements Walkable {

  public static Position getNewPosition(Position currentPosition, Grid grid) {
    Position newPosition = new Position(currentPosition.getX() + 1, currentPosition.getY() + 1);
    return newPosition;
  }

  public static boolean isAllowedToWalk(Position currentPosition, Position newPosition, Grid grid) {
    return Diagonal.isAboveOrOn(newPosition) && Diagonal.isAboveOrOn(currentPosition)
        && grid.isInGrid(newPosition);
  }
}
