package sugui.dayXX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DayXX {

    public static Path inputPath = Paths.get("resources/inputXX.txt");

    // Other static variables needed to solve the problem...

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static String getFirstPuzzleResult(String input) {
        // Here goes the code for the solution to the first problem...
        return "Solution to the first problem";
    }

    public static String getSecondPuzzleResult(String input) {
        // Here goes the code for the solution to the second problem...
        return "Solution to the second problem";
    }
}
