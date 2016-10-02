package de.sebhn.algorithm.excercise1.helper;

import java.util.Stack;

public class GraphMatrix {

  int[][] graph;

  public GraphMatrix(int n) {

    graph = new int[n][n];

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
      if (row[i] == 1)
        nodeCount++;
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

  Stack<Integer> stack = new Stack<Integer>();

  public void findAllPath(GraphMatrix graph, int source, int target, boolean[] visited) {
    stack.add(source);

    if (source == target) {
      printStack(stack);
    }

    if (visited[source] != true)
      visited[source] = true;

    int[] adjNodes = getAdjacentNodes(source);


    if (adjNodes.length > 0) {
      for (int i = 0; i < adjNodes.length; i++) {
        if (visited[adjNodes[i]] != true) {
          findAllPath(graph, adjNodes[i], target, visited);
        }
      }
    }

    visited[source] = false;
    stack.remove(stack.size() - 1);
  }

  private void printStack(Stack<Integer> stack) {
    System.out.println("Print Path ");
    for (Integer integer : stack) {
      System.out.println(integer);
    }
  }
}
