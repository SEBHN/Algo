package de.sebhn.algorithm.excercise1.helper.direction;


import de.sebhn.algorithm.excercise1.helper.Grid;
import de.sebhn.algorithm.excercise1.helper.Position;

public class RightUp implements Walkable {

  public static Position getNewPosition(Position currentPosition, Grid grid) {
    Position newPosition = calculateNewPosition(currentPosition);
    if (isAllowedToWalk(newPosition)) {
      return newPosition;
    }
    return currentPosition;
  }

  private static Position calculateNewPosition(Position currentPosition) {
    Position newPosition = new Position(currentPosition.getX() + 1, currentPosition.getY() + 1);
    return newPosition;
  }

  public static boolean isAllowedToWalk(Position newPosition) {
    return Diagonal.isAboveOrOn(newPosition);
  }



}
