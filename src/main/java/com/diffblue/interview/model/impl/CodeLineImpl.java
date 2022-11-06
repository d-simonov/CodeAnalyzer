package com.diffblue.interview.model.impl;

import com.diffblue.interview.model.CodeLine;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class CodeLineImpl implements CodeLine {
    private final int lineNumber;
    private final String contents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeLineImpl codeLine = (CodeLineImpl) o;
        return lineNumber == codeLine.lineNumber && contents.equals(codeLine.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, contents);
    }

    @Override
    public String toString() {
        return "lineNumber:" + lineNumber +
                ", contents: " + contents;
    }
}
