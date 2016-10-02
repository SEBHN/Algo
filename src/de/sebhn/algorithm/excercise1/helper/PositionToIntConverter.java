package de.sebhn.algorithm.excercise1.helper;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class PositionToIntConverter {

  private HashMap<Position, Integer> integer2PositionMap;

  public PositionToIntConverter(Grid grid) {
    int gridSize = grid.getSize();
    integer2PositionMap = new HashMap<>(gridSize * gridSize);
    for (int i = 0, counter = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        Position pos = new Position(i, j);
        integer2PositionMap.put(pos, Integer.valueOf(counter));
        counter++;
      }
    }
  }

  int convert(Position pos) {
    return integer2PositionMap.get(pos);
  }

  Position deConvert(int value) {
    Set<Entry<Position, Integer>> entrySet = integer2PositionMap.entrySet();
    for (Entry<Position, Integer> entry : entrySet) {
      if (entry.getValue().intValue() == value) {
        return entry.getKey();
      }
    }
    return null;
  }
}
