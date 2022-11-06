package com.diffblue.interview.model.impl;

import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.model.CodeTest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CodeTestMockImpl implements CodeTest {

    @NonNull
    Set<CodeLine> coveredLines;

    @Override
    public String getName() {
        return "Test1";
    }

    @Override
    public Set<CodeLine> getCoveredLines() {
        return coveredLines;
    }
}
