package de.sebhn.algorithm.excercise2;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;


public class QuaternstringsTest {

  @Test
  public void testN1() throws Exception {
    Quaternstrings.calculate(1);
  }

  @Test
  public void testN2() throws Exception {
    Quaternstrings.calculate(2);
  }

  @Test
  public void testN3() throws Exception {
    Quaternstrings.calculate(3);
  }

  @Test
  public void testN4() throws Exception {
    Quaternstrings.calculate(4);
  }

  @Test
  public void testN5() throws Exception {
    Quaternstrings.calculate(5);
  }

  @Test
  public void testN6() throws Exception {
    Quaternstrings.calculate(6);
  }

  @Test
  public void testN7() throws Exception {
    Quaternstrings.calculate(7);
  }

  @Test
  public void testN8() throws Exception {
    Quaternstrings.calculate(8);
  }

  @Test
  public void testN9() throws Exception {
    Quaternstrings.calculate(9);
  }

  @Test
  public void testN10() throws Exception {
    Quaternstrings.calculate(10);
  }

  /**
   * Holy grail of algo
   * 
   * @throws Exception
   */
  @Test
  @Ignore
  public void testN1000() throws Exception {
    Quaternstrings.calculate(1000);
  }

  @Test
  public void testCharArray() throws Exception {
    String anumber = "001";
    char[] charArray = anumber.toCharArray();
    assertEquals(3, charArray.length);
  }

}
