package com.staccato.challenge.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationsTest {


  @Test
  public void addition_Test() {
    Assertions.assertEquals(3, Operations.addition(1, 2));
    Assertions.assertEquals(0, Operations.addition(0, 0));
    Assertions.assertEquals(0, Operations.addition(1, -1));
    Assertions.assertEquals(-2, Operations.addition(-1, -1));
  }

  @Test
  public void substraction_Test() {
    Assertions.assertEquals(1, Operations.substraction(2, 1));
    Assertions.assertEquals(0, Operations.substraction(2, 2));
    Assertions.assertEquals(2, Operations.substraction(1, -1));
    Assertions.assertEquals(0, Operations.substraction(-1, -1));
  }

  @Test
  public void multiplication_Test() {
    Assertions.assertEquals(2, Operations.multiplication(2, 1));
    Assertions.assertEquals(0, Operations.multiplication(0, 0));
    Assertions.assertEquals(-1, Operations.multiplication(1, -1));
    Assertions.assertEquals(1, Operations.multiplication(-1, -1));
  }

  @Test
  public void division_Test() {
    Assertions.assertEquals(1, Operations.division(2, 2));
    Assertions.assertEquals(0, Operations.division(1, 2));
    Assertions.assertEquals(-1, Operations.division(1, -1));
    Assertions.assertEquals(1, Operations.division(-1, -1));
  }

  @Test
  public void division_zero_by_zero_Test() {
    Assertions.assertThrows(ArithmeticException.class, () -> Operations.division(0, 0));
  }

  @Test
  public void division_by_zero_Test() {
    Assertions.assertThrows(ArithmeticException.class, () -> Operations.division(1, 0));
  }

  @Test
  public void sqrt_Test() {
    Assertions.assertEquals(10, Operations.sqrt(100));
    Assertions.assertEquals(0, Operations.sqrt(0));

  }

  @Test
  public void random_Test() {
    String result = Operations.random();
    Assertions.assertNotNull(result);
    Assertions.assertNotEquals("An error occurred, please try again", result);

    String result2 = Operations.random();
    Assertions.assertNotNull(result2);
    Assertions.assertNotNull("An error occurred, please try again", result2);
  }
}
