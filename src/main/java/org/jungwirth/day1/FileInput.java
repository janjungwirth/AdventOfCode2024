package org.jungwirth.day1.partA;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileInput {
    public List<String> loadListFromFile(String fileNamel){
        try {
            Path path = Paths.get("src","main","java","org","jungwirth","day1","partA",fileNamel);
            if(Files.exists(path))
                return Files.lines(path).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
