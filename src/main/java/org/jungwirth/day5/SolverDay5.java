//(c) Jan Jungwirth - 06.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day5;

import org.jungwirth.Solver;

import java.util.List;

public class SolverDay5 implements Solver {
    private static final FileInput INPUT = new FileInput();
    private static final Ruleset RULESET = Ruleset.getRULESET();

    @Override
    public void solve() {
        INPUT.initializeRuleset("rules.txt");

        final List<List<Integer>> updates = INPUT.loadUpdates("updates.txt");
        final List<List<Integer>> reorderedUpdates = getInValidUpdates(updates).stream()
                .map(RULESET::reorderUpdate)
                .toList();

        System.out.println("Valid: " + sumMiddleNumbers(getValidUpdates(updates)));
        System.out.println("Reordered: " + sumMiddleNumbers(reorderedUpdates));
    }

    private int sumMiddleNumbers(final List<List<Integer>> validUpdates) {
        return validUpdates.stream()
                .map(update -> update.get(update.size() / 2))
                .reduce(0, Integer::sum);
    }

    private List<List<Integer>> getValidUpdates(final List<List<Integer>> updates) {
        return updates.stream().filter(this::isValidateUpdate).toList();
    }

    private List<List<Integer>> getInValidUpdates(final List<List<Integer>> updates) {
        return updates.stream().filter(update -> !isValidateUpdate(update)).toList();
    }

    private boolean isValidateUpdate(final List<Integer> update) {
        List<Integer> pagesBefore = update.subList(0, update.size() - 1);
        for (int i = update.size() - 1; i > 0; i--) {
            if (RULESET.hasViolation(update.get(i), pagesBefore))
                return false;
            pagesBefore = pagesBefore.subList(0, pagesBefore.size() - 1);
        }
        return true;
    }
}
