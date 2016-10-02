package de.sebhn.algorithm.excercise1.helper;

import java.util.Objects;

/**
 * This class is responsible for the coordinates of the buttons
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class Position {

  // Instance variables
  private int positionX;
  private int positionY;

  // Constructor
  public Position(int positionX, int positionY) {
    // if (positionX < 0 || positionY < 0) {
    // throw new InvalidParameterException(
    // "Position cant be negative x, y " + positionX + positionY);
    // }
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public Position getPosition() {
    return this;
  }

  public int getX() {
    return positionX;
  }

  public int getY() {
    return positionY;
  }

  @Override
  public String toString() {
    return "" + positionX + "," + positionY;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Position) {
      Position other = (Position) obj;
      return getX() == other.getX() && getY() == other.getY();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(positionX, positionY);
  }

  public boolean isEqualsOrBiggerThan(Position other) {
    return getX() >= other.getX() && getY() >= other.getY();
  }

  public boolean isEqualsOrSmallerThan(Position other) {
    return getX() <= other.getX() && getY() <= other.getY();
  }
}
