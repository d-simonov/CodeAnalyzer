package com.diffblue.interview.model;

import lombok.AllArgsConstructor;

/*
 * A class helping to format the output of the test execution.
 */
@AllArgsConstructor
public class CodeLineOutput {
    private static final String CHECK_MARK = "\u2713";
    private static final String CROSS_MARK = "\u2715";
    private CodeLine codeLine;
    private boolean coveredByTest;

    @Override
    public String toString() {
        return "CoveredByTest: " + (coveredByTest ? CHECK_MARK : CROSS_MARK) + "  ||  " + "Line Number: " + codeLine.getLineNumber() + "  ||  " + "Content: " + codeLine.getContents();
    }
}
