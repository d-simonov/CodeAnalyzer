package com.diffblue.interview.filereader;

import com.diffblue.interview.exception.NoFileFoundException;
import com.diffblue.interview.model.CodeClass;
import com.diffblue.interview.model.CodeLine;
import com.diffblue.interview.exception.LineNotFoundException;
import com.diffblue.interview.model.impl.CodeLineImpl;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.stream.Stream;

/*
 * Reads CodeLine/CodeLines from file.
 */
@Log
public final class CodeFileReader {

    public static List<CodeLine> readAllLinesFromClass(CodeClass codeClass){
        List<CodeLine> result = new ArrayList<>();
        AtomicInteger currentLine = new AtomicInteger(1);

        try (Stream<String> stream = Files.lines(codeClass.getFile().toPath())) {
            stream.forEach(s -> result.add(new CodeLineImpl(currentLine.getAndIncrement(), s)));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to parse the file {0}", codeClass.getFile());
            throw new NoFileFoundException(e);
        }

        return result;
    }

    public static CodeLine readSingleLineFromClass(CodeClass codeClass, int lineNumber){
        if (lineNumber <= 0) {
            throw new LineNotFoundException("Incorrect line number");
        }
        try (Stream<String> lines = Files.lines(codeClass.getFile().toPath())) {
            Optional<String> lineOptional = lines.skip(lineNumber - 1).findFirst();
            if (!lineOptional.isPresent()) {
                throw new LineNotFoundException(String.format("Line with number %s not found", lineNumber));
            }
            return new CodeLineImpl(lineNumber, lineOptional.get());
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to parse the file {0}", codeClass.getFile());
            throw new NoFileFoundException(e);
        }
    }
}
