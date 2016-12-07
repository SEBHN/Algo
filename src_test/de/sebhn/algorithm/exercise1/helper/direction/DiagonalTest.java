package de.sebhn.algorithm.exercise1.helper.direction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.sebhn.algorithm.exercise1.helper.Position;
import de.sebhn.algorithm.exercise1.helper.direction.Diagonal;

public class DiagonalTest {

  @Test
  public void testUpAllowedToWalk() {
    assertTrue(Diagonal.isUnderOrOn(new Position(1, 1)));
    assertTrue(Diagonal.isUnderOrOn(new Position(2, 2)));
    assertTrue(Diagonal.isUnderOrOn(new Position(3, 3)));
    assertTrue(Diagonal.isUnderOrOn(new Position(3, 3)));
    assertFalse(Diagonal.isUnderOrOn(new Position(1, 2)));
  }

}
