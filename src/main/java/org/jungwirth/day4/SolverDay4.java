//(c) Jan Jungwirth - 04.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day4;

import org.jungwirth.Solver;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SolverDay4 implements Solver {
    private final static Pattern XMAS_PATTERN = Pattern.compile("XMAS");
    private final List<String> fileContains = loadFile();

    @Override
    public void solve() {
        final Map<String, List<String>> transformedLines = getTransformedLines();
        int totalSum = 0;
        for (Map.Entry<String, List<String>> entry : transformedLines.entrySet()) {
            int partSum = 0;
            for (String string : entry.getValue()) {
                partSum += countXmas(string);
            }
            System.out.printf("%s : %d\n", entry.getKey(), partSum);
            totalSum += partSum;
        }
        System.out.printf("Total: %d", totalSum);
    }

    private int countXmas(final String string) {
        final Matcher matcher = XMAS_PATTERN.matcher(string);
        int amount = 0;
        while (matcher.find()) {
            amount++;
        }
        return amount;
    }

    private List<String> loadFile() {
        final FileInput fileInput = new FileInput();
        return fileInput.loadListFromFile("input.txt");
    }

    private Map<String, List<String>> getTransformedLines() {
        final Map<String, List<String>> transformedLines = new LinkedHashMap<>();
        transformedLines.put("leftRight", fileContains);
        transformedLines.put("rightLeft", reverse(fileContains));
        transformedLines.put("topDown", getColumns(fileContains));
        transformedLines.put("bottomUp", reverse(getColumns(fileContains)));
        transformedLines.put("topLeftBottomRight", traverseDiagonale(fileContains));
        transformedLines.put("bottomRightTopLeft", reverse(traverseDiagonale(fileContains)));
        transformedLines.put("topRightBottomLeft", traverseDiagonale(reverse(fileContains)));
        transformedLines.put("bottomLeftTopRight", reverse(traverseDiagonale(reverse(fileContains))));

        return transformedLines;
    }


    private List<String> reverse(final List<String> lines) {
        return lines.stream()
                .map(line -> new StringBuilder(line).reverse().toString())
                .collect(Collectors.toList());
    }

    private List<String> getColumns(final List<String> lines) {
        final List<String> result = new LinkedList<>();
        final List<StringBuilder> builder = new LinkedList<>();
        lines.forEach(_ -> builder.add(new StringBuilder()));

        populateStringBuilder(lines, builder);

        builder.forEach(s -> result.add(s.toString()));
        return result;
    }

    private static void populateStringBuilder(final List<String> source, final List<StringBuilder> builder) {
        for (int row = 0; row < source.getFirst().length(); row++) {
            final int finalRow = row;
            source.forEach(line -> {
                builder.get(finalRow).append(line.charAt(finalRow));
            });
        }
    }

    private List<String> traverseDiagonale(final List<String> origin) {
        final List<String> odd = new LinkedList<>();
        final List<String> results = new LinkedList<>();
        final List<String> even = new LinkedList<>();

        for (int pointer = 0; pointer < origin.size(); pointer++) {
            odd.add(origin.get(pointer).substring(0, pointer + 1));
            even.add(origin.get(pointer).substring(pointer + 1));
        }

        results.addAll(processSequence(odd));
        results.addAll(processSequence(reverse(even)));

        return results;
    }

    private List<String> processSequence(final List<String> sequence) {
        List<String> sequenceHolder = new LinkedList<>(sequence);
        final List<String> results = new LinkedList<>();
        for (int i = 0; i < sequence.size(); i++) {
            results.add(getLastCharacter(sequenceHolder));
            sequenceHolder = reduceStringsByLastChar(sequenceHolder);
        }
        return results;
    }

    private String getLastCharacter(final List<String> source) {
        final StringBuilder builder = new StringBuilder();
        source.forEach(element -> {
            if (!element.isEmpty())
                builder.append(element.toCharArray()[Math.max(0, element.length() - 1)]);
        });
        return builder.toString();
    }

    private List<String> reduceStringsByLastChar(final List<String> source) {
        final List<String> result = new LinkedList<>();
        source.forEach(element -> {
            result.add(element.substring(0, Math.max(0, element.length() - 1)));
        });
        return result;
    }
}
