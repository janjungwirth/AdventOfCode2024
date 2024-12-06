//(c) Jan Jungwirth - 06.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day5;

import lombok.Getter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;


public class Ruleset {
    @Getter
    private static final Ruleset RULESET = new Ruleset();
    private static final String ERROR = "No Updates Loaded";
    private static final Comparator<Integer> COMPARATOR = new Sorter();
    private static final Map<Integer, List<Integer>> RULES = new LinkedHashMap<>();

    private Ruleset() {
    }

    public List<Integer> getPagesAfterPage(final Integer page) {
        if (RULES.isEmpty())
            throw new RuntimeException(ERROR);

        if (RULES.get(page) == null)
            return List.of();
        return RULES.get(page);
    }

    public void addPageAfter(final Integer page, final Integer pageAfter) {
        final List<Integer> toBeAdded = new LinkedList<>(
                RULES.get(page) != null ? RULES.get(page) : List.of()
        );
        toBeAdded.add(pageAfter);
        RULES.put(page, toBeAdded);
    }

    public boolean containsPage(final Integer page) {
        if (RULES.isEmpty())
            throw new RuntimeException(ERROR);

        return RULES.containsKey(page);
    }

    public boolean hasViolation(final Integer page, final List<Integer> pagesBefore) {
        if (RULES.isEmpty())
            throw new RuntimeException(ERROR);

        if (RULESET.containsPage(page)) {
            for (Integer pageAfter : RULESET.getPagesAfterPage(page)) {
                if (pagesBefore.contains(pageAfter))
                    return true;
            }
        }
        return false;
    }

    public List<Integer> reorderUpdate(final List<Integer> update) {
        if (RULES.isEmpty())
            throw new RuntimeException(ERROR);

        final LinkedList<Integer> reordered = new LinkedList<>(update);
        reordered.sort(COMPARATOR);
        return reordered;
    }

    private static class Sorter implements Comparator<Integer> {
        @SuppressWarnings("all")
        @Override
        public int compare(final Integer o1, final Integer o2) {
            if (RULESET.getPagesAfterPage(o1).contains(o2))
                return -1;
            return 1;
        }
    }


}
