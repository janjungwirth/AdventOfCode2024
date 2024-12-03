//(c) Jan Jungwirth - 03.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day1;

import java.util.LinkedList;
import java.util.List;

public final class SolverDay1A extends SolverDay1Common {
    private final List<Integer> elvesListOne = new LinkedList<>();
    private final List<Integer> elvesListTwo = new LinkedList<>();

    @Override
    public void solve() {
        System.out.println(getDifferenceBetweenLists());
    }

    private Integer getDifferenceBetweenLists() {
        final List<String> loadedList = loadLists();
        populateElvesLists(loadedList);
        sortElvesLists();
        return calculateDifference();
    }

    @Override
    protected void populateElvesLists(final List<String> loadedList) {
        loadedList.forEach(line -> {
           final String[] segment = line.split(REGEX_SPLIT);
           elvesListOne.add(Integer.parseInt(segment[0]));
           elvesListTwo.add(Integer.parseInt(segment[1]));
        });
    }

    private void sortElvesLists() {
        this.elvesListOne.sort(Integer::compareTo);
        this.elvesListTwo.sort(Integer::compareTo);
    }


    private Integer calculateDifference() {
        Integer result = 0;
        for(int i = 0; i < elvesListOne.size(); i++) {
            result+=getUnsignedInteger(elvesListOne.get(i)-elvesListTwo.get(i));
        }
        return result;
    }

    private static Integer getUnsignedInteger(final Integer signedInteger) {
        Integer unsignedInteger = signedInteger;
        if(isNegativeNumber(signedInteger))
            unsignedInteger *=-1;
        return unsignedInteger;
    }

    private static boolean isNegativeNumber(final Integer signedInteger) {
        return signedInteger < 0;
    }

}
