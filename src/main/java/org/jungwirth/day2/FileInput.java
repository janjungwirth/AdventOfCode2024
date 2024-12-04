//(c) Jan Jungwirth - 04.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileInput {
    public List<String> loadListFromFile(final String fileName){
        final Path path = Paths.get("src","main","java","org","jungwirth","day2",fileName);
        try (Stream<String> lines = Files.lines(path)){
            if(Files.exists(path))
                return lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
