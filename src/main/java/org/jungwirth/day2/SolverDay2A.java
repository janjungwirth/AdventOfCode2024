//(c) Jan Jungwirth - 04.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day2;


import org.jungwirth.Solver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class SolverDay2A implements Solver {
    private static final String SEPARATOR = " ";
    private List<Report> reports = new LinkedList<>();

    @Override
    public void solve() {
        reports = generateReports(loadReports());
        System.out.println("No Violations:" + getAmountOfSaveReports());
        System.out.println("Violations:" + getAmountOfSaveReportsisWithViolations());
    }

    private List<Report> generateReports(final List<String> reportsData) {
        final List<Report> reportList = new LinkedList<>();
        for(String report : reportsData){
            final String[] reportNumbers = report.split(SEPARATOR);
            reportList.add(generateReport(reportNumbers));
        }
        return reportList;
    }

    private Report generateReport(final String[] reportNumbers) {
        return new Report(Arrays.stream(reportNumbers).mapToInt(Integer::parseInt).boxed().toList());
    }

    public List<String> loadReports(){
        final FileInput input = new FileInput();
        return input.loadListFromFile("inputFile.txt");
    }

    private Integer getAmountOfSaveReports() {
        return (int) reports.stream().filter(Report::isSave).count();
    }

    private Integer getAmountOfSaveReportsisWithViolations() {
        return (int) reports.stream().filter(Report::isSaveWithViolations).count();
    }
}
