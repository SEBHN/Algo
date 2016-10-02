package de.sebhn.algorithm.excercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import de.sebhn.algorithm.excercise1.helper.PositionToIntConverter;

public class OutputCounter {

  private final List<Integer> intPositions;
  private final PositionToIntConverter positionToIntConverter;
  private int size;

  public OutputCounter(PositionToIntConverter positionToIntConverter) {
    this.positionToIntConverter = positionToIntConverter;
    intPositions = new ArrayList<>();
  }

  public void addStack(Stack<Integer> stack) {
    intPositions.addAll(stack);
    size++;
  }

  public void printSize() {
    System.out.println("Possible Paths: " + size);
  }

  public void printPaths(int targetNumber) {
    String path = intPositions.stream()
        .map(pathElement -> positionToIntConverter.deConvert(pathElement).toString()) //
        .collect(Collectors.joining("->"));
    String endPath = targetNumber + "," + targetNumber;
    path = path.replace(endPath + "->", endPath + "\n");
    System.out.println("Paths: \n" + path);
  }
}
