package sugui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;

public class Day01 {

    public static Path inputPath = Paths.get("resources/input01.txt");

    public static Map<String, List<String>> spelledDigitsMap = Map.of(
            "1", List.of("1", "one"),
            "2", List.of("2", "two"),
            "3", List.of("3", "three"),
            "4", List.of("4", "four"),
            "5", List.of("5", "five"),
            "6", List.of("6", "six"),
            "7", List.of("7", "seven"),
            "8", List.of("8", "eight"),
            "9", List.of("9", "nine"));

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static String getFirstPuzzleResult(String input) {
        IntPredicate isDigit = c -> Character.isDigit(c);
        var bufferedReader = new BufferedReader(new StringReader(input));
        var result = bufferedReader.lines()
                .map(line -> {
                    var digits = line
                            .chars()
                            .filter(isDigit)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                    var number = digits.isEmpty() ? 0
                            : Integer.parseInt(
                                    String.format(
                                            "%c%c",
                                            digits.charAt(0),
                                            digits.charAt(digits.length() - 1)));
                    return number;
                })
                .reduce(0, (a, b) -> a + b);
        return Integer.toString(result);
    }

    public static String getSecondPuzzleResult(String input) {
        var bufferedReader = new BufferedReader(new StringReader(input));
        var result = bufferedReader.lines()
                .map(line -> {
                    Map<Integer, String> positions = new HashMap<>();
                    spelledDigitsMap.forEach((digit, spelledDigits) -> {
                        spelledDigits.forEach(spelled -> {
                            int firstIndex = line.indexOf(spelled);
                            int lastIndex = line.lastIndexOf(spelled);
                            if (firstIndex != -1) {
                                positions.put(firstIndex, digit);
                                positions.put(lastIndex, digit);
                            }
                        });
                    });
                    int maxPosition = Collections.max(positions.keySet());
                    int minPosition = Collections.min(positions.keySet());

                    int number = Integer.parseInt(positions.get(minPosition) + positions.get(maxPosition));
                    return number;
                }).reduce(0, (a, b) -> a + b);
        return Integer.toString(result);
    }
}
