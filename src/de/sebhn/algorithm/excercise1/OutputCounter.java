package de.sebhn.algorithm.excercise1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import de.sebhn.algorithm.excercise1.helper.Grid;
import de.sebhn.algorithm.excercise1.helper.PositionToIntConverter;

public class OutputCounter {

  private final List<Integer> intPositions;
  private final PositionToIntConverter positionToIntConverter;
  private BigInteger size;
  private boolean pathPrintingMakesSense;
  private Grid grid;

  public OutputCounter(Grid grid) {
    this.grid = grid;
    this.positionToIntConverter = grid.getConverter();
    pathPrintingMakesSense = grid.getSize() < 50;
    intPositions = new ArrayList<>();
  }

  public void addStack(Stack<Integer> stack) {
    if (pathPrintingMakesSense) {
      intPositions.addAll(stack);
    }
    size.add(BigInteger.ONE);
  }

  public void printSize() {
    System.out.println("Possible Paths: " + size);
  }

  public void printPaths() {
    if (pathPrintingMakesSense) {
      String path = intPositions.stream()
          .map(pathElement -> getPositionToIntConverter().deConvert(pathElement).toString()) //
          .collect(Collectors.joining("->"));
      String endPath = grid.getTargetNumber() + "," + grid.getTargetNumber();
      path = path.replace(endPath + "->", endPath + "\n");
      System.out.println("Paths: \n" + path);
    } else {
      System.out.println("too much paths to print");
    }
  }

  public PositionToIntConverter getPositionToIntConverter() {
    return positionToIntConverter;
  }
}
