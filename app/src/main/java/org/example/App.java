package org.example;

import java.util.Scanner;

/*
 * My choice & explanation of abort behavior in getInput() for the second add-on:
 * When the user enters "exit" to abort input the function returns a null to clearly indicate
 * that no valid integer was provided. This avoids ambiguity that could arise from returning
 * random values or throwing exceptions for expected user actions. Returning a null allows
 * the caller to detect the abort condition easily and handle it gracefully such as ending
 * the program without printing further output. The design I implemented makes the input validation
 * function robust, clear, and simple to write.
 */

public class App {

  public static Integer getInput(int lowerBound, int upperBound, int defaultValue,
      String promptMessage, String errorMessage) {

    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println(promptMessage
            + " Enter 'default' to use the default value of " + defaultValue + "."
            + " Enter 'exit' to leave the menu.");

        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
          // User chose to exit and so return a null to indicate abort
          return null;
        } else if (input.equalsIgnoreCase("default")) {
          // User chose default value
          return defaultValue;
        } else {
          try {
            int value = Integer.parseInt(input);

            if (value >= lowerBound && value <= upperBound) {
              return value;
            } else {
              System.out.println(errorMessage);
            }
          } catch (NumberFormatException e) {
            System.out.println(errorMessage);
          }
        }
      }
    }
  }

  public static void main(String[] args) { // main func
    Integer result = getInput(10, 100, 50,
        "Please enter a value between 10 and 100.",
        "Your value is invalid.");

    if (result == null) {
      // User chose to exit and hence no printing
      return;
    } else {
      System.out.println("\nThe value chosen by the user is " + result);
    }
  }
}
