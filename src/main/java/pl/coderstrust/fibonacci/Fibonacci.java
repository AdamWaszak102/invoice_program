package pl.coderstrust.fibonacci;

/**
 * Created by Adam on 2018-02-12.
 */

public class Fibonacci {

  public static int fibonacciIterative(int number) {

    int firstFibonacciSeries;
    int secondFibonacciSeries;
    if (number == 0) {
      return 0;
    }

    firstFibonacciSeries = 0;

    secondFibonacciSeries = 1;
    for (int i = 0; i < (number - 1); i++) {
      secondFibonacciSeries = secondFibonacciSeries + firstFibonacciSeries;
      firstFibonacciSeries = secondFibonacciSeries - firstFibonacciSeries;
    }
    return secondFibonacciSeries;
  }

  public static int fibonacciRecursive(int number2) {
    if (number2 == 0) {
      return 0;
    } else if (number2 == 1) {
      return 1;
    } else {
      return fibonacciRecursive(number2 - 1) + fibonacciRecursive(number2 - 2);
    }
  }

}