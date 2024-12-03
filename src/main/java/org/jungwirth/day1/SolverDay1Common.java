//(c) Jan Jungwirth - 03.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day1;

import org.jungwirth.Solver;

import java.util.List;

public abstract sealed class SolverDay1Common implements Solver permits SolverDay1A, SolverDay1B {
    protected static final String REGEX_SPLIT = " {3}";
    abstract protected void populateElvesLists(final List<String> loadedList);

    protected List<String> loadLists() {
        final FileInput fileInput = new FileInput();
        return fileInput.loadListFromFile("input.txt");
    }
}
