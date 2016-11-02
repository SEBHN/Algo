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

  /**
   * n einlesen für n<8 abbrechen - nicht möglich n-dim. boolean-array anlegen -> eintragen welche
   * belegt wurden wenn abstand >= 8 dann neuen ort in int-array an best. stelle hinzufügen wenn
   * letzter ort mind. 8 abstand zu ausgangsort, dann array in arraylist speichern
   */
  public static int amountOfCities = -1;
  public boolean[] visitedCity;
  public ArrayList<int[]> listOfRoutes = new ArrayList<int[]>();
  public int[] route;
  public static int distance = -1;

  public static void main(String[] args) {

    System.out.println("Bitte geben Sie die Anzahl an Orte an, die besucht werden sollen.");
    Scanner sc = new Scanner(System.in);
    while (amountOfCities < 0) {
      try {
        int i = Integer.parseInt(sc.next());
        amountOfCities = i;
      } catch (Exception exception) {
        System.out.println("Bitte eine natürlich Zahl eingeben.");
      }
    }
    System.out.println("Bitte den Abstand angeben, der zwischen den Städteindizes liegen soll.");
    while (distance < 0) {
      try {
        int i = Integer.parseInt(sc.next());
        distance = i;
        sc.close();
      } catch (Exception exception) {
        System.out.println("Bitte eine natürlich Zahl eingeben.");
      }
    }

    new Turns();
  }

  public Turns() {
    visitedCity = new boolean[amountOfCities];
    route = new int[amountOfCities];
    for (int i = 0; i < amountOfCities; i++) {
      visitedCity[i] = false;
      route[i] = 0;
    }

    visitedCity[0] = true;
    route[0] = 0;
    calculateNeighbour(0, 1);
    System.out.println();
    for (int i = 0; i < listOfRoutes.size(); i++) {
      System.out.println(Arrays.toString(listOfRoutes.get(i)));
    }
    System.out.println("Es gab genau " + listOfRoutes.size() + " Rundreisen.");
  }

  public void calculateNeighbour(int cityNumber, int travelNumber) {
    route[travelNumber - 1] = cityNumber;
    if (travelNumber == amountOfCities && Math.abs(cityNumber) >= distance) {
      listOfRoutes.add(route.clone());
      return;
    }
    visitedCity[cityNumber] = true;
    for (int i = 0; i < amountOfCities; i++) {
      if (Math.abs(cityNumber - i) >= distance && !visitedCity[i]) {
        calculateNeighbour(i, travelNumber + 1);
      }
    }

    visitedCity[cityNumber] = false;
  }
}
