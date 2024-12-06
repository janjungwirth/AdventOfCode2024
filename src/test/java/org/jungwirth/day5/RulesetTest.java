package org.jungwirth.day5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RulesetTest {

    @BeforeAll
    static void setup() {
        final Ruleset ruleset = Ruleset.getRULESET();
        ruleset.addPageAfter(47,53);
        ruleset.addPageAfter(97,13);
        ruleset.addPageAfter(97,61);
        ruleset.addPageAfter(97,47);
        ruleset.addPageAfter(75,29);
        ruleset.addPageAfter(61,13);
        ruleset.addPageAfter(75,53);
        ruleset.addPageAfter(29,13);
        ruleset.addPageAfter(97,29);
        ruleset.addPageAfter(53,29);
        ruleset.addPageAfter(61,53);
        ruleset.addPageAfter(97,53);
        ruleset.addPageAfter(61,29);
        ruleset.addPageAfter(47,13);
        ruleset.addPageAfter(75,47);
        ruleset.addPageAfter(97,75);
        ruleset.addPageAfter(47,61);
        ruleset.addPageAfter(75,61);
        ruleset.addPageAfter(47,29);
        ruleset.addPageAfter(75,13);
        ruleset.addPageAfter(53,13);
    }

    @Test
    void hasNoViolation() {
        final List<Integer> pages = new LinkedList<>(List.of(75,47,61,53,29));
        List<Integer> pagesBefore = pages.subList(0, pages.size()-1);
        for (int i=pages.size()-1; i>0; i--) {
            assertFalse(Ruleset.getRULESET().hasViolation(pages.get(i), pagesBefore));
            pagesBefore = pagesBefore.subList(0, pagesBefore.size()-1);
        }
    }

    @Test
    void hasViolation() {
        final List<Integer> pages = new LinkedList<>(List.of(75,97,47,61,53));
        List<Integer> pagesBefore = pages.subList(0, pages.size()-1);
        for (int i=pages.size()-1; i>1; i--) {
            assertFalse(Ruleset.getRULESET().hasViolation(pages.get(i), pagesBefore));
            pagesBefore = pagesBefore.subList(0, pagesBefore.size()-1);
        }
        assertTrue(Ruleset.getRULESET().hasViolation(pages.get(1), pagesBefore));
    }
}