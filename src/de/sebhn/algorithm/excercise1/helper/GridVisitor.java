package de.sebhn.algorithm.excercise1.helper;

import de.sebhn.algorithm.excercise1.helper.direction.LeftUp;
import de.sebhn.algorithm.excercise1.helper.direction.Right;
import de.sebhn.algorithm.excercise1.helper.direction.RightDown;
import de.sebhn.algorithm.excercise1.helper.direction.RightUp;
import de.sebhn.algorithm.excercise1.helper.direction.Up;

public class GridVisitor {

  public static void findPath(Grid grid) {
    Position startingPoint = new Position(0, 0);
    System.out.println("Target " + grid.getTargetNumber());
    Position currentPosition = startingPoint;
    // TODO: find other ways, than just rightup

    while (currentPosition.getX() != grid.getTargetNumber()
        && currentPosition.getY() != grid.getTargetNumber()) {
      if (RightUp.isAllowedToWalk(RightUp.getNewPosition(currentPosition, grid))) {
        grid.visit(currentPosition.getX(), currentPosition.getY());
        currentPosition = RightUp.getNewPosition(currentPosition, grid);
        grid.visit(currentPosition.getX(), currentPosition.getY());
      } else if (RightDown.isAllowedToWalk(RightDown.getNewPosition(currentPosition, grid))) {
        grid.visit(currentPosition.getX(), currentPosition.getY());
        currentPosition = RightDown.getNewPosition(currentPosition, grid);
        grid.visit(currentPosition.getX(), currentPosition.getY());
      } else if (LeftUp.isAllowedToWalk(LeftUp.getNewPosition(currentPosition, grid))) {
        grid.visit(currentPosition.getX(), currentPosition.getY());
        currentPosition = LeftUp.getNewPosition(currentPosition, grid);
        grid.visit(currentPosition.getX(), currentPosition.getY());
      } else if (Up.isAllowedToWalk(Up.getNewPosition(currentPosition, grid))) {
        grid.visit(currentPosition.getX(), currentPosition.getY());
        currentPosition = Up.getNewPosition(currentPosition, grid);
        grid.visit(currentPosition.getX(), currentPosition.getY());
      } else if (Right.isAllowedToWalk(Right.getNewPosition(currentPosition, grid))) {
        grid.visit(currentPosition.getX(), currentPosition.getY());
        currentPosition = Right.getNewPosition(currentPosition, grid);
        grid.visit(currentPosition.getX(), currentPosition.getY());
      } else {
        System.out.println("fucking lost");
      }
    }
    System.out.println("Walked " + grid.getVisitedPaths());
  }
}
