package org.jungwirth.day1.partA;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public sealed class SolverDay1B extends SolverDay1Common {
    List<Integer> elvesListOne = new LinkedList<>();
    //Key = elvesNumber, Value = appearance
    Map<Integer, Integer> elvesListTwo =  new TreeMap<>();

    @Override
    public void solve() {
        System.out.println(calculate());
    }

    private Integer calculate() {
        final List<String> loadedList = loadLists();
        populateElvesLists(loadedList);
        return calculateSimilarityScore();
    }


    private void populateElvesLists(final List<String> loadedList) {
        loadedList.forEach(line -> {
           final String[] segment = line.split(" {3}");
           elvesListOne.add(Integer.parseInt(segment[0]));
           increaseProbability(Integer.parseInt(segment[1]));
        });
    }

    private void increaseProbability(final Integer key) {
        if(elvesListTwo.containsKey(key))
            elvesListTwo.put(key, elvesListTwo.get(key)+1);
        else
            elvesListTwo.put(key, 1);
    }


    private int calculateSimilarityScore() {
        int result=0;
        for (Integer number : elvesListOne) {
            final Integer appearance = elvesListTwo.get(number)!=null?elvesListTwo.get(number):0;
            result+=number * appearance;
        }
        return result;
    }

}
