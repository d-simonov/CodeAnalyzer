package com.diffblue.interview.model;

/**
 * An interface representing a line of Java code in a file
 */
public interface CodeLine {
    /**
     * @return the line number of the code under test
     */
    int getLineNumber();

    /**
     * @return the string representation of this line of code
     */
    String getContents();
}
