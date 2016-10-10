package de.sebhn.algorithm.excercise2;

import org.junit.Test;

public class QuaternstringsTest {


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

  /**
   * Holy grail of algo
   * 
   * @throws Exception
   */
  @Test
  public void testN1000() throws Exception {
    Quaternstrings.calculate(1000);
  }

}
