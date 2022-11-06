package com.diffblue.interview.model.impl;

import com.diffblue.interview.model.CodeClass;
import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.filereader.CodeFileReader;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
public class CodeClassImpl implements CodeClass {

    @NonNull
    @Getter
    private File file;

    private List<CodeLine> linesOfCode;

    @Override
    public List<CodeLine> getLinesOfCode() {
        //only lazy loading, not thread safe (considering thread safety is out of scope for this task)
        if (linesOfCode == null) {
            linesOfCode = CodeFileReader.readAllLinesFromClass(this);
        }

        return linesOfCode;
    }
}
