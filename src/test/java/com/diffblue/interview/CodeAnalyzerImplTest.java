package com.diffblue.interview;

import com.diffblue.interview.analyzer.CodeAnalyzer;
import com.diffblue.interview.analyzer.impl.CodeAnalyzerImpl;
import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.model.CodeTest;
import com.diffblue.interview.model.impl.CodeLineImpl;
import com.diffblue.interview.model.impl.CodeTestMockImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CodeAnalyzerImplTest {

    public static final String MOCK_CONTENT_1 = "public static void main(String[] args) {";
    public static final String MOCK_CONTENT_2 = "    static boolean isPrime(int n, int i)";
    private final CodeAnalyzer classUnderTest = new CodeAnalyzerImpl();

    @Test
    void runTest_happyPath() {
        Set<CodeLine> codeLineSet = Collections.singleton(new CodeLineImpl(1, MOCK_CONTENT_1));
        CodeTest test = new CodeTestMockImpl(codeLineSet);
        Set<CodeLine> result = classUnderTest.runTest(test);
        assertEquals(codeLineSet, result);
    }

    @Test
    void runTest_emptyTest() {
        Set<CodeLine> result = classUnderTest.runTest(null);
        assertEquals(new HashSet<>(), result);
    }

    @Test
    void runTestSuite_happyPath() {
        CodeLine codeLine1 = new CodeLineImpl(1, MOCK_CONTENT_1);
        Set<CodeLine> codeLineSet1 = Collections.singleton(codeLine1);
        CodeTest test1 = new CodeTestMockImpl(codeLineSet1);

        CodeLine codeLine2 = new CodeLineImpl(2, MOCK_CONTENT_2);
        Set<CodeLine> codeLineSet2 = Collections.singleton(codeLine2);
        CodeTest test2 = new CodeTestMockImpl(codeLineSet2);

        Set<CodeLine> result = classUnderTest.runTestSuite(new HashSet<>(Arrays.asList(test1, test2)));
        assertIterableEquals(new HashSet<>(Arrays.asList(codeLine1, codeLine2)), result);
    }

    @Test
    void runTestSuite_emptyTest() {
        Set<CodeLine> result = classUnderTest.runTestSuite(null);
        assertEquals(new HashSet<>(), result);
    }
}
