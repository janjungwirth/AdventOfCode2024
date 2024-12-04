//(c) Jan Jungwirth - 04.12.2024 - check out: https://adventofcode.com/2024
package org.jungwirth.day3;

import org.jungwirth.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolverDay3 implements Solver {
    public static final String REGEX_ACTIONS = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|don't\\(\\)|do\\(\\)";
    boolean doFlag = true;
    boolean alwaysAdd = true;

    public SolverDay3(){
    }

    @Override
    public void solve(){
        System.out.println("All: "+getResult());
        this.alwaysAdd=false;
        this.doFlag=true;
        System.out.println("do/don't: "+getResult());
    }

    private String loadInputFile() {
        final FileInput input = new FileInput();
        return input.loadFromFile("inputFile.txt");
    }

    private int getResult() {
        final String inputString = loadInputFile();
        final List<String> multiplications = getAllActions(inputString);

        int sum = 0;
        for (String action : multiplications) {
            switch (action.substring(0, 3)) {
                case "mul" -> sum+=evaluateMulExpression(action);
                case "do(" -> doFlag = true;
                case "don" -> doFlag = false;
            }
        }
        return sum;
    }

    //This function was inspired by:
    //https://stackoverflow.com/questions/6020384/create-array-of-regex-matches
    private List<String> getAllActions(final String input) {
        final List<String> allMatches = new ArrayList<>();
        final Matcher m = Pattern.compile(REGEX_ACTIONS)
                .matcher(input);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return allMatches;
    }

    private int evaluateMulExpression(final String expression){
        if(!(doFlag || alwaysAdd))
            return 0;

        int sum=1;
        for(String element : expression.replace("mul(", "").replace(")","").split(",")){
            sum *= Integer.parseInt(element);
        }
        return sum;
    }
}
