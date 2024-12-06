//(c) Jan Jungwirth - 06.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class FileInput {
    public List<List<Integer>> loadUpdates(final String fileName) {
        final List<List<Integer>> updates = new LinkedList<>();
        final Path path = Paths.get("src", "main", "java", "org", "jungwirth", "day5", fileName);
        try (Stream<String> lines = Files.lines(path)) {
            if (Files.exists(path))
                for (String line : lines.toList()) {
                    final String[] split = line.split(",");
                    updates.add(Arrays.stream(split).map(Integer::parseInt).toList());
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return updates;
    }

    public void initializeRuleset(final String fileName) {
        final Path path = Paths.get("src", "main", "java", "org", "jungwirth", "day5", fileName);
        try (Stream<String> lines = Files.lines(path)) {
            if (Files.exists(path))
                lines.forEach(line -> {
                    final String[] split = line.split("\\|");
                    Ruleset.getRULESET().addPageAfter(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
