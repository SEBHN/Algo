package de.sebhn.algorithm.exercise8;

import java.util.Arrays;

public class QuickSort {


  public static void main(String[] args) {
    String stringToSort = "djiebgchaf";
    char[] sorted = quickSort(stringToSort.toCharArray(), 0, stringToSort.length() - 1);
    System.out.println(Arrays.toString(sorted));
  }

  private static char[] quickSort(char[] arrayToSort, int startIndex, int endIndex) {
    int leftIndex = startIndex;
    int rightIndex = endIndex;
    int midIndex = (leftIndex + rightIndex) / 2;

    preSortLeftMidRightWithBubbleSort(arrayToSort, leftIndex, rightIndex, midIndex);

    int diffEndAndStart = endIndex - startIndex;
    boolean isQuickSortNecesaryAfterBubbleSort = diffEndAndStart > 2;
    if (isQuickSortNecesaryAfterBubbleSort) { // big problem
      char w = arrayToSort[midIndex]; // divide:

      do {
        while (arrayToSort[leftIndex] < w) {
          leftIndex++; // search leftIndex
        }
        while (arrayToSort[rightIndex] > w) {
          rightIndex--; // search rightIndex
        }
        if (leftIndex <= rightIndex) {
          swap(arrayToSort, leftIndex, rightIndex); // swap
          leftIndex++;
          rightIndex--;
        }
      } while (leftIndex <= rightIndex); // until finished

      if (startIndex < rightIndex) {
        quickSort(arrayToSort, startIndex, rightIndex); // conquer left
      }
      if (leftIndex < endIndex) {
        quickSort(arrayToSort, leftIndex, endIndex); // conquer right
      }
    }
    return arrayToSort;
  }

  /**
   * Small problem
   */
  private static void preSortLeftMidRightWithBubbleSort(char[] arrayToSort, int leftIndex,
      int rightIndex, int mid) {
    if (arrayToSort[leftIndex] > arrayToSort[mid]) {
      swap(arrayToSort, leftIndex, mid);
    }
    if (arrayToSort[mid] > arrayToSort[rightIndex]) {
      swap(arrayToSort, mid, rightIndex);
    }
    if (arrayToSort[leftIndex] > arrayToSort[mid]) {
      swap(arrayToSort, leftIndex, mid);
    }
  }

  private static void swap(char[] a, int index1, int index2) {
    char charAtIndex1 = a[index1];
    char charAtIndex2 = a[index2];
    // swap
    a[index1] = charAtIndex2;
    a[index2] = charAtIndex1;
  }
}
