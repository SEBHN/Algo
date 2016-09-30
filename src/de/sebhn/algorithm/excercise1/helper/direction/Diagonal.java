package de.sebhn.algorithm.excercise1.helper.direction;

import de.sebhn.algorithm.excercise1.helper.Position;

public class Diagonal {

  static boolean isAboveOrOn(Position newPos) {
    return newPos.getX() <= newPos.getY();
  }

  static boolean isUnderOrOn(Position newPos) {
    return newPos.getX() >= newPos.getY();
  }
}
