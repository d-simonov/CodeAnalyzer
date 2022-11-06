package com.diffblue.interview.testrunner;

import com.diffblue.interview.analyzer.CodeAnalyzer;
import com.diffblue.interview.analyzer.impl.CodeAnalyzerImpl;
import com.diffblue.interview.exception.NoFileFoundException;
import com.diffblue.interview.model.CodeClass;
import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.model.CodeLineOutput;
import com.diffblue.interview.model.CodeTest;
import com.diffblue.interview.model.impl.CodeClassImpl;
import com.diffblue.interview.model.impl.CodeTestMockImpl;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestRunner {

    /*
     * A method that takes the file name of a Java source file and generates a printout of the file with lines prefixed with whether they are covered by tests or not. (Question 1)
     */
    public void runTestsAndPrintOutput(String fileName) {

        URL resource = this.getClass().getClassLoader().getResource(fileName);
        if (resource == null || !(new File(resource.getFile()).exists())) {
            throw new NoFileFoundException(String.format("File with name %s not found", fileName));
        }

        File classFile = new File(resource.getFile());

        if (!classFile.exists()) {
            throw new NoFileFoundException(String.format("File with name %s not found", fileName));
        }

        CodeClass clazz = new CodeClassImpl(new File(resource.getFile()));
        List<CodeLine> allCodeLinesInTheClass = clazz.getLinesOfCode();

        Set<CodeLine> coveredLinesMock = new HashSet<>(Arrays.asList(allCodeLinesInTheClass.get(11), allCodeLinesInTheClass.get(12)));
        CodeTest test = new CodeTestMockImpl(coveredLinesMock);

        CodeAnalyzer analyzer = new CodeAnalyzerImpl();
        Set<CodeLine> coveredLines = analyzer.runTest(test);

        for (CodeLine codeLine : allCodeLinesInTheClass) {
            CodeLineOutput outputString = new CodeLineOutput(codeLine, coveredLines.contains(codeLine));
            System.out.println(outputString);
        }
    }
}
