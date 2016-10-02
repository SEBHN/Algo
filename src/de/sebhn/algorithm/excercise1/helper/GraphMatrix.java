package de.sebhn.algorithm.excercise1.helper;

import java.util.Stack;
import java.util.stream.Collectors;

public class GraphMatrix {

  private final int[][] graph;
  private final Stack<Integer> stack;

  public GraphMatrix(int n) {
    graph = new int[n][n];
    stack = new Stack<Integer>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = 0;
      }
    }
  }

  public void addEdge(int source, int target) {
    graph[source][target] = 1;
  }


  public int[] getAdjacentNodes(int source) {
    int[] row = graph[source];
    int[] adjNodes = null;
    int nodeCount = 0;

    for (int i = 0; i < row.length; i++) {
      if (row[i] == 1) {
        nodeCount++;
      }
    }

    adjNodes = new int[nodeCount];
    nodeCount = 0;

    for (int i = 0; i < row.length; i++) {
      if (row[i] == 1) {
        adjNodes[nodeCount] = i;
        nodeCount++;
      }
    }

    return adjNodes;
  }

  public void findAllPath(int source, int target, boolean[] visited,
      PositionToIntConverter positionToIntConverter) {
    stack.add(source);

    if (source == target) {
      printStack(stack, positionToIntConverter);
    }

    if (!visited[source]) {
      visited[source] = true;
    }

    int[] adjNodes = getAdjacentNodes(source);


    if (adjNodes.length > 0) {
      for (int i = 0; i < adjNodes.length; i++) {
        int adjacentNode = adjNodes[i];
        if (!visited[adjacentNode]) {
          findAllPath(adjacentNode, target, visited, positionToIntConverter);
        }
      }
    }

    visited[source] = false;
    stack.remove(stack.size() - 1);
  }

  private void printStack(Stack<Integer> stack, PositionToIntConverter positionToIntConverter) {
    // System.out.println(stack.size() + " Paths");
    String path = stack.stream() //
        .map(pathElement -> positionToIntConverter.deConvert(pathElement).toString()) //
        .collect(Collectors.joining("->", "Path: ", ""));
    System.out.println(path);
  }
}
