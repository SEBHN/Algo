package de.sebhn.algorithm.excercise1.helper.direction;


import de.sebhn.algorithm.excercise1.helper.Grid;
import de.sebhn.algorithm.excercise1.helper.Position;

public class Right implements Walkable {

  public static Position getNewPosition(Position currentPosition, Grid grid) {
    Position newPosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
    if (isAllowedToWalk(newPosition)) {
      return newPosition;
    }
    return currentPosition;
  }

  public static boolean isAllowedToWalk(Position newPosition) {
    return Diagonal.isUnderOrOn(newPosition);
  }

}
