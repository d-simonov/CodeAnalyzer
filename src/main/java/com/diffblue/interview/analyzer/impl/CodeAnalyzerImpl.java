package com.diffblue.interview.analyzer.impl;

import com.diffblue.interview.analyzer.CodeAnalyzer;
import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.model.CodeTest;

import java.util.HashSet;
import java.util.Set;

public class CodeAnalyzerImpl implements CodeAnalyzer {

    @Override
    public Set<CodeLine> runTest(CodeTest test) {
        if (test == null) {
            return new HashSet<>();
        }
        return test.getCoveredLines();
    }

    @Override
    public Set<CodeLine> runTestSuite(Set<CodeTest> tests) {
        if (tests == null) {
            return new HashSet<>();
        }
        Set<CodeLine> result = new HashSet<>();
        for (CodeTest test : tests) {
            result.addAll(test.getCoveredLines());
        }
        return result;
    }
}
