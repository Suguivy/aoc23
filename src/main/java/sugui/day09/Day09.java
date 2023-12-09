package sugui.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day09 {

    public static Path inputPath = Paths.get("resources/input09.txt");

    // Other static variables needed to solve the problem...

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static List<History> parse(String input) {
        return Arrays.asList(input.split("\n")).stream()
                .map(line -> new History(
                        Arrays.asList(line.split(" +")).stream().map(num -> Integer.parseInt(num)).toList()))
                .toList();
    }

    public static String getFirstPuzzleResult(String input) {
        return Integer.toString(parse(input).stream().map(history -> history.getNext()).reduce(0, (a, b) -> a + b));
    }

    public static String getSecondPuzzleResult(String input) {
        return Integer.toString(parse(input).stream().map(history -> history.reverse().getNext()).reduce(0, (a, b) -> a + b));

    }
}
