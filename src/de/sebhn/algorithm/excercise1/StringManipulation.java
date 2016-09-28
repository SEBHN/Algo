package de.sebhn.algorithm.excercise1;

/**
 * Excercise 2
 * 
 * @author manuel
 *
 */
public class StringManipulation {


  public static void main(String[] args) {
    System.out.println("Rekm-Output: " + rekm("Hi my name is"));
    System.out.println("Rekl-Output: " + rekl("Hi my name is"));
  }

  public static String rekm(String s) {
    int m = s.length() / 2;
    return m == 0 ? s : rekm(s.substring(m)) + rekm(s.substring(0, m));
  }

  public static String rekl(String s) {
    return s.length() <= 1 ? s : rekl(s.substring(1)) + s.charAt(0);
  }
}
