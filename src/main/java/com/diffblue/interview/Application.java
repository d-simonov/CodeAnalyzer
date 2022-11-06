package com.diffblue.interview;

import com.diffblue.interview.testrunner.TestRunner;

/*
 * Main application entry point.
 */
public class Application {

    public static void main(String[] args) {
        TestRunner testRunner = new TestRunner();
        testRunner.runTestsAndPrintOutput("./PrimeNumber.java");
    }
}
