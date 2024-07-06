package com.staccato.challenge.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationsTest {


  @Test
  public void addition_Test() {
    Assertions.assertEquals(3d, Operations.addition(1d, 2d));
    Assertions.assertEquals(0d, Operations.addition(0d, 0d));
    Assertions.assertEquals(0d, Operations.addition(1d, -1d));
    Assertions.assertEquals(-2d, Operations.addition(-1d, -1d));
  }

  @Test
  public void substraction_Test() {
    Assertions.assertEquals(1, Operations.substraction(2d, 1d));
    Assertions.assertEquals(0, Operations.substraction(2d, 2d));
    Assertions.assertEquals(2, Operations.substraction(1d, -1d));
    Assertions.assertEquals(0, Operations.substraction(-1d, -1d));
  }

  @Test
  public void multiplication_Test() {
    Assertions.assertEquals(2, Operations.multiplication(2d, 1d));
    Assertions.assertEquals(0, Operations.multiplication(0d, 0d));
    Assertions.assertEquals(-1, Operations.multiplication(1d, -1d));
    Assertions.assertEquals(1, Operations.multiplication(-1d, -1d));
  }

  @Test
  public void division_Test() {
    Assertions.assertEquals(1, Operations.division(2d, 2d));
    Assertions.assertEquals(0, Operations.division(1d, 2d));
    Assertions.assertEquals(-1, Operations.division(1d, -1d));
    Assertions.assertEquals(1, Operations.division(-1d, -1d));
  }

  @Test
  public void division_zero_by_zero_Test() {
    Assertions.assertThrows(ArithmeticException.class, () -> Operations.division(0d, 0d));
  }

  @Test
  public void division_by_zero_Test() {
    Assertions.assertThrows(ArithmeticException.class, () -> Operations.division(1d, 0d));
  }

  @Test
  public void sqrt_Test() {
    Assertions.assertEquals(10, Operations.sqrt(100d));
    Assertions.assertEquals(0, Operations.sqrt(0d));

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
