//(c) Jan Jungwirth - 03.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class SolverDay1B extends SolverDay1Common {
    private final List<Integer> elvesListOne = new LinkedList<>();
    //Key = elvesNumberFromList, Value = number of appearance in given List
    private final Map<Integer, Integer> elvesListTwo =  new TreeMap<>();

    @Override
    public void solve() {
        System.out.println(calculate());
    }

    private Integer calculate() {
        final List<String> loadedList = loadLists();
        populateElvesLists(loadedList);
        return calculateSimilarityScore();
    }

    @Override
    protected void populateElvesLists(final List<String> loadedList) {
        loadedList.forEach(line -> {
           final String[] segment = line.split(REGEX_SPLIT);
           elvesListOne.add(Integer.parseInt(segment[0]));
           increaseProbability(Integer.parseInt(segment[1]));
        });
    }

    private void increaseProbability(final Integer key) {
        elvesListTwo.put(key, elvesListTwo.containsKey(key)?elvesListTwo.get(key)+1:1);
    }


    private int calculateSimilarityScore() {
        int similarityScore=0;
        for (Integer number : elvesListOne) {
            final Integer appearance = elvesListTwo.get(number)!=null?elvesListTwo.get(number):0;
            similarityScore+=number * appearance;
        }
        return similarityScore;
    }

}
