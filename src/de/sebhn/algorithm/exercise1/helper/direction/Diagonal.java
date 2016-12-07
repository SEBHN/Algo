package de.sebhn.algorithm.exercise1.helper.direction;

import de.sebhn.algorithm.exercise1.helper.Position;

public class Diagonal {

  static boolean isAboveOrOn(Position newPos) {
    return newPos.getX() <= newPos.getY();
  }

  static boolean isUnderOrOn(Position newPos) {
    return newPos.getX() >= newPos.getY();
  }
}
