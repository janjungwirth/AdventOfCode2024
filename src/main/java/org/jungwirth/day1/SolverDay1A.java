package org.jungwirth.day1.partA;

import org.jungwirth.Solver;
import org.jungwirth.day1.FileInput;

import java.util.LinkedList;
import java.util.List;

public class SolverDay1A implements Solver {
    List<Integer> elvesListOne = new LinkedList<>();
    List<Integer> elvesListTwo = new LinkedList<>();;

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


    private List<String> loadLists() {
        final FileInput fileInput = new FileInput();
        return fileInput.loadListFromFile("input.txt");
    }

    private void populateElvesLists(final List<String> loadedList) {
        loadedList.forEach(line -> {
           final String[] segment = line.split(" {3}");
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

    private static Integer getUnsignedInteger(final Integer evaluation) {
        Integer result = evaluation;
        if(isNegativeNumber(result))
            result *=-1;
        return result;
    }

    private static boolean isNegativeNumber(final Integer evaluation) {
        return evaluation < 0;
    }

}
