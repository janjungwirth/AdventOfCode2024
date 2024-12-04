//(c) Jan Jungwirth - 04.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day2;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
public class Report {
    private List<Integer> reportData;
    private boolean isGrowing;


    public Report(final List<Integer> reportData) {
        this.reportData = new LinkedList<>(reportData);
        isGrowing = reportData.getFirst() < reportData.getLast();
    }

    public boolean isSave() {
        return isSave(new LinkedList<>(reportData),-1);
    }

    public boolean isSaveWithViolations() {
        final List<Boolean> bruteForce = new LinkedList<>();
        if(!isSave()){
            for(int i=0; i<reportData.size(); i++){
                bruteForce.add(isSave(new LinkedList<>(reportData),i));
            }
        }
        int counter=0;
        for(Boolean b : bruteForce){
            if(b)
                counter++;
        }
        return counter>0;
    }

    public boolean isSave(final List<Integer> integerList, final int removed) {
        if (integerList.isEmpty())
            throw new RuntimeException("Populate report data first");
        if(removed>=0)
            integerList.remove(removed);
        boolean isSave = true;
        for (int pointer = 0; pointer < integerList.size() - 1; pointer++) {
            final Integer first = integerList.get(pointer);
            final Integer second = integerList.get(pointer + 1);
            isSave = isSave
                    && differenceNotGreaterThen3(first, second)
                    && differenceGreaterThen0(first, second)
                    && isNotTheSame(first, second)
                    && isStrictLinear(first,second);
        }
        return isSave;
    }

    private static boolean isNotTheSame(final Integer first, final Integer second) {
        return !Objects.equals(first, second);
    }

    private static boolean differenceGreaterThen0(final Integer first, final Integer second) {
        return second - first > 0 || first - second > 0;
    }

    private static boolean differenceNotGreaterThen3(final Integer first, final Integer second) {
        return second - first <= 3 && second - first >= -3;
    }

    private boolean isStrictLinear(final Integer first, final Integer second) {
        return isGrowing
                ? first < second
                : first > second;

    }
}