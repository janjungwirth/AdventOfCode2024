//(c) Jan Jungwirth - 04.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileInput {
    public String loadFromFile(final String fileName){
        final Path path = Paths.get("src","main","java","org","jungwirth","day3",fileName);
        final StringBuilder containmentOfFile = new StringBuilder();
        try (Stream<String> lines = Files.lines(path)){
            lines.forEach(containmentOfFile::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return containmentOfFile.toString();
    }
}
