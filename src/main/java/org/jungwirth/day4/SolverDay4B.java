//(c) Jan Jungwirth - 06.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day4;

import org.jungwirth.Solver;

import java.util.List;

public class SolverDay4B implements Solver {

    private static final List<String> MAS_RULES = List.of("MMSS", "MSMS", "SSMM", "SMSM");
    private static final char MIDDLE_ELEMENT = 'A';

    @Override
    public void solve() {
        final FileInput input = new FileInput();
        final char[][] baseArray = input.loadArrayFromFile("input.txt");

        System.out.println(getFoundMAS(baseArray));
    }

    private int getFoundMAS(final char[][] baseArray) {
        int foundMAS = 0;
        for (int row = 1; row < baseArray.length - 1; row++) {
            for (int col = 1; col < baseArray[row].length - 1; col++) {
                if (baseArray[row][col] == MIDDLE_ELEMENT && isXMas(baseArray, row, col))
                    foundMAS++;
            }
        }
        return foundMAS;
    }

    public boolean isXMas(final char[][] board, final int row, final int col) {
        return isValidMas(extractStringFromBoard(board, row, col));
    }

    private static String extractStringFromBoard(final char[][] board, final int row, final int col) {
        return String.valueOf(
                board[row - 1][col - 1]) +
                board[row - 1][col + 1] +
                board[row + 1][col - 1] +
                board[row + 1][col + 1];
    }

    private boolean isValidMas(final String potentialMas) {
        return MAS_RULES.stream().anyMatch(potentialMas::equals);
    }

}
