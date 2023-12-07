package sugui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import sugui.aux.Card;
import sugui.aux.Hand;
import sugui.aux.HandComparator;
import sugui.aux.HandWithJokerComparator;

public class Day07 {

    public static Path inputPath = Paths.get("resources/input07.txt");

    // Other static variables needed to solve the problem...

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static List<Hand> parsing(String input) {
        var bufferedReader = new BufferedReader(new StringReader(input));
        return bufferedReader.lines().map(line -> {
            String[] splittedLine = line.split(" ");
            String[] labels = splittedLine[0].split("");
            int bid = Integer.parseInt(splittedLine[1]);
            return new Hand(new Card(labels[0]), new Card(labels[1]), new Card(labels[2]), new Card(labels[3]),
                    new Card(labels[4]), bid);
        }).collect(Collectors.toList());
    }

    public static String getFirstPuzzleResult(String input) {
        List<Hand> hands = parsing(input);
        hands.sort(new HandComparator());
        int currentRank = 1;
        int result = 0;
        for (Hand hand : hands) {
            result += hand.bid() * currentRank;
            currentRank++;
        }
        return Integer.toString(result);
    }

    public static String getSecondPuzzleResult(String input) {
        List<Hand> hands = parsing(input);
        hands.sort(new HandWithJokerComparator());
        int currentRank = 1;
        int result = 0;
        for (Hand hand : hands) {
            result += hand.bid() * currentRank;
            currentRank++;
        }
        return Integer.toString(result);
    }
}
