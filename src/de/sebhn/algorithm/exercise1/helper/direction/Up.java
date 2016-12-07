package de.sebhn.algorithm.exercise1.helper.direction;


import de.sebhn.algorithm.exercise1.helper.Grid;
import de.sebhn.algorithm.exercise1.helper.Position;

public class Up implements Walkable {

  public static Position getNewPosition(Position currentPosition, Grid grid) {
    Position newPosition = new Position(currentPosition.getX(), currentPosition.getY() + 1);
    return newPosition;
  }

  public static boolean isAllowedToWalk(Position currentPosition, Position newPosition, Grid grid) {
    return Diagonal.isUnderOrOn(newPosition) && Diagonal.isUnderOrOn(currentPosition)
        && grid.isInGrid(newPosition);
  }
}
