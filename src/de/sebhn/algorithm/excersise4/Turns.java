package de.sebhn.algorithm.excersise4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Solves a custom traveling sales person problem
 * 
 * @author Phi Long Tran <191624>
 * @author Dimitri Kapcikov <191510>
 * @author Manuel Wessner <191711>
 */
public class Turns {

  public boolean[] visitedCity;
  public ArrayList<int[]> routes = new ArrayList<>();
  public int[] route;
  private int amountOfCitiesToVisit;
  private int distance;

  Turns(int amountOfCitiesToVisit, int distance) {
    this.amountOfCitiesToVisit = amountOfCitiesToVisit;
    this.distance = distance;
    this.visitedCity = new boolean[amountOfCitiesToVisit];
    this.route = new int[amountOfCitiesToVisit];
    Arrays.fill(visitedCity, false);
    Arrays.fill(route, 0);
  }

  public static void main(String[] args) {
    Turns turns = createTurnsWithuserInput();
    turns.run();
  }

  void run() {
    calculateNeighbour(0, 1);
    printWays();
    printAmountOfWays();
  }

  private void printAmountOfWays() {
    System.out.println("There are " + routes.size() + " stops");
  }

  private void printWays() {
    System.out.println("The ways/permutations are: ");
    for (int[] route : routes) {
      System.out.println(Arrays.toString(route));
    }
  }

  private static Turns createTurnsWithuserInput() {
    int amountOfCities = -1;
    int distance = -1;
    System.out.println("How many cities should be visited?");
    try (Scanner sc = new Scanner(System.in)) {
      while (amountOfCities < 0) {
        amountOfCities = readInputFromuser(sc);
      }
      System.out.println("How far should the cities be apart of each other?");
      while (distance < 0) {
        distance = readInputFromuser(sc);
      }
    }
    return new Turns(amountOfCities, distance);
  }

  private static int readInputFromuser(Scanner sc) {
    int toBeRead = -1;
    try {
      toBeRead = Integer.parseInt(sc.next());
    } catch (NumberFormatException nfe) {
      System.out.println("Please retry with a valid input. " + nfe);
    }
    return toBeRead;
  }

  private void calculateNeighbour(int cityNumber, int travelNumber) {
    route[travelNumber - 1] = cityNumber;
    if (travelNumber == amountOfCitiesToVisit && cityNumber >= distance) {
      routes.add(route.clone());
      return;
    }
    visitedCity[cityNumber] = true;
    for (int i = 0; i < amountOfCitiesToVisit; i++) {
      if (Math.abs(cityNumber - i) >= distance && !visitedCity[i]) {
        calculateNeighbour(i, travelNumber + 1);
      }
    }

    visitedCity[cityNumber] = false;
  }
}
