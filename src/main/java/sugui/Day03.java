package sugui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import sugui.util.IntegerRef;
import sugui.util.Pair;

public class Day03 {

    public static Path inputPath = Paths.get("resources/input03.txt");

    private static Map<Pair<Integer, Integer>, IntegerRef> numbers = new HashMap<>();
    private static Map<Pair<Integer, Integer>, String> symbols = new HashMap<>();

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    private static void loadNumbersAndSymbols(String input) {
        var bufferedReader = new BufferedReader(new StringReader(input));
        var lines = bufferedReader.lines().collect(Collectors.toList());
        int lineNumber = 0;
        for (String line : lines) {
            Pattern numberPattern = Pattern.compile("(\\d+)");
            Pattern symbolPattern = Pattern.compile("[^\\d|\\.]");
            Matcher numberMatcher = numberPattern.matcher(line);
            Matcher symbolMatcher = symbolPattern.matcher(line);
            while (numberMatcher.find()) {
                IntegerRef numberRef = new IntegerRef(Integer.parseInt(numberMatcher.group()));
                for (int col = numberMatcher.start(); col < numberMatcher.end(); col++) {
                    numbers.put(new Pair<>(col, lineNumber), numberRef);
                }
            }
            while (symbolMatcher.find()) {
                String symbol = symbolMatcher.group();
                symbols.put(new Pair<>(symbolMatcher.start(), lineNumber), symbol);
            }
            lineNumber++;
        }
    }

    public static String getFirstPuzzleResult(String input) {
        loadNumbersAndSymbols(input);

        int sumOfPartNumbers = 0;
        Set<IntegerRef> visitedNumbers = new HashSet<>();
        for (var symbolPositions : symbols.keySet()) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    var position = new Pair<>(symbolPositions._1() + dx, symbolPositions._2() + dy);
                    if (numbers.containsKey(position)) {
                        IntegerRef numberRef = numbers.get(position);
                        if (!visitedNumbers.contains(numberRef)) {
                            visitedNumbers.add(numberRef);
                            sumOfPartNumbers += numberRef.value();
                        }
                    }
                }
            }
        }
        return Integer.toString(sumOfPartNumbers);
    }

    public static String getSecondPuzzleResult(String input) {
        loadNumbersAndSymbols(input);

        int sumOfGearRatios = 0;
        for (var symbolPositions : symbols.keySet()) {
            if ("*".equals(symbols.get(symbolPositions))) {
                Set<IntegerRef> visitedNumbers = new HashSet<>();
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        var position = new Pair<>(symbolPositions._1() + dx, symbolPositions._2() + dy);
                        if (numbers.containsKey(position)) {
                            IntegerRef numberRef = numbers.get(position);
                            if (!visitedNumbers.contains(numberRef)) {
                                visitedNumbers.add(numberRef);
                            }
                        }
                    }
                }
                if (visitedNumbers.size() == 2) {
                    int gearRatio = visitedNumbers.stream().map(iRef -> iRef.value()).reduce(1, (a, b) -> a * b);
                    sumOfGearRatios += gearRatio;
                }
            }
        }
        return Integer.toString(sumOfGearRatios);
    }
}
