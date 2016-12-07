package de.sebhn.algorithm.exercise2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.sebhn.algorithm.exercise2.Quaternstrings;


public class QuaternstringsTest {

  @Test
  public void testN1() throws Exception {
    assertEquals("4", Quaternstrings.calculate(1).toString());
  }

  @Test
  public void testN2() throws Exception {
    assertEquals("13", Quaternstrings.calculate(2).toString());
  }

  @Test
  public void testN3() throws Exception {
    assertEquals("42", Quaternstrings.calculate(3).toString());
  }

  @Test
  public void testN4() throws Exception {
    assertEquals("136", Quaternstrings.calculate(4).toString());
  }

  @Test
  public void testN5() throws Exception {
    assertEquals("440", Quaternstrings.calculate(5).toString());
  }

  @Test
  public void testN6() throws Exception {
    assertEquals("1423", Quaternstrings.calculate(6).toString());
  }

  @Test
  public void testN7() throws Exception {
    assertEquals("4602", Quaternstrings.calculate(7).toString());
  }

  @Test
  public void testN8() throws Exception {
    assertEquals("14883", Quaternstrings.calculate(8).toString());
  }

  @Test
  public void testN9() throws Exception {
    assertEquals("48132", Quaternstrings.calculate(9).toString());
  }

  @Test
  public void testN10() throws Exception {
    assertEquals("155660", Quaternstrings.calculate(10).toString());
  }

  /**
   * Holy grail of algo
   * 
   * @throws Exception
   */
  @Test
  public void testN1000() throws Exception {
    assertEquals(
        "688392248037533757713764205706571705220243732319430179416242107760492969167450259416609386077991322671808671360568171761092554091087989909471229125400396713067129246831063231667097668213892255075339331792216099438420916353841538065913791571915375840112744147104775438060876515976193042885118501910610484400419840166574866304901830040616116115132077025726575992800701172374400231947975641504497518075894990138077106917270967959170682665660707079805188366662268687902881468825906809774748357181873845140529093548",
        Quaternstrings.calculate(1000).toString());
  }

}
