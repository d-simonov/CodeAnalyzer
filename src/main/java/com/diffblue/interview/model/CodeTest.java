package com.diffblue.interview.model;

import lombok.NonNull;

import java.util.Set;

/**
 * An interface representing a Java test
 */
public interface CodeTest {
    /**
     * @return the name of the test
     */
    @NonNull
    String getName();

    /**
     * @return the set of line numbers covered by an execution of this test
     */
    @NonNull
    Set<CodeLine> getCoveredLines();
}
