package com.diffblue.interview;

import com.diffblue.interview.exception.LineNotFoundException;
import com.diffblue.interview.exception.NoFileFoundException;
import com.diffblue.interview.model.impl.CodeClassImpl;
import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.filereader.CodeFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CodeFileReaderTest {

    public static final File TEST_JAVA_CLASS = new File("src/test/resources/PrimeNumber.java");
    public static final File WRONG_CLASS_NAME = new File("src/test/resources/TotallyWrong.java");
    public static final int TOTAL_NUMBER_OF_LINES = 23;
    public static final int TARGET_LINE_NUMBER = 14;
    public static final String LINE_14_CONTENT = "            return (n == 2) ? true : false;";
    public static final int LINE_NUMBER_0 = 0;
    public static final int NEGATIVE_LINE_NUMBER = -2;


    @Test
    void readAllLinesFromClass_happyPath() {
        List<CodeLine> codeLines = CodeFileReader.readAllLinesFromClass(new CodeClassImpl(TEST_JAVA_CLASS));

        CodeLine codeLine = codeLines.get(TARGET_LINE_NUMBER - 1);
        assertEquals(TOTAL_NUMBER_OF_LINES, codeLines.size());
        assertNotNull(codeLine);
        assertEquals(TARGET_LINE_NUMBER, codeLine.getLineNumber());
        assertEquals(LINE_14_CONTENT, codeLine.getContents());
    }

    @Test
    void readAllLinesFromClass_missingFile() {
        Assertions.assertThrows(NoFileFoundException.class, () -> {
            CodeFileReader.readAllLinesFromClass(new CodeClassImpl(WRONG_CLASS_NAME));
        });
    }

    @Test
    void readSingleLineFromClass_happyPath() {
        CodeLine codeLine = CodeFileReader.readSingleLineFromClass(new CodeClassImpl(TEST_JAVA_CLASS), TARGET_LINE_NUMBER);

        assertNotNull(codeLine);
        assertEquals(TARGET_LINE_NUMBER, codeLine.getLineNumber());
        assertEquals(LINE_14_CONTENT, codeLine.getContents());
    }

    @Test
    void readSingleLineFromClass_incorrectLineNumber() {

        LineNotFoundException exception = Assertions.assertThrows(LineNotFoundException.class, () -> {
            CodeFileReader.readSingleLineFromClass(new CodeClassImpl(TEST_JAVA_CLASS), LINE_NUMBER_0);
        });

        Assertions.assertEquals("Incorrect line number", exception.getMessage());

        LineNotFoundException exception2 = Assertions.assertThrows(LineNotFoundException.class, () -> {
            CodeFileReader.readSingleLineFromClass(new CodeClassImpl(TEST_JAVA_CLASS), NEGATIVE_LINE_NUMBER);
        });

        Assertions.assertEquals("Incorrect line number", exception2.getMessage());
    }

    @Test
    void readSingleLineFromClass_missingFile() {
        Assertions.assertThrows(NoFileFoundException.class, () -> {
            CodeFileReader.readSingleLineFromClass(new CodeClassImpl(WRONG_CLASS_NAME), TARGET_LINE_NUMBER);
        });
    }
}