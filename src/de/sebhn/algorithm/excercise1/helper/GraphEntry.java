package de.sebhn.algorithm.excercise1.helper;

public class GraphEntry {

  Position position;
  boolean visited;

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean isVisited() {
    return visited;
  }



}
