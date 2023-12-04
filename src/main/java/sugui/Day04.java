package sugui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 {

    private record Card(Set<Integer> winningNumbers, Set<Integer> currentNumbers) {
    }

    public static Path inputPath = Paths.get("resources/input04.txt");

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static String getFirstPuzzleResult(String input) {
        List<Card> cardList = parse(input);
        int result = cardList.stream().map(card -> {
            var intersection = new HashSet<>(card.currentNumbers);
            intersection.retainAll(card.winningNumbers);
            // We use a shift (<<) to calculate the power of 2
            return intersection.size() == 0 ? 0 : 1 << (intersection.size() - 1);
        }).reduce(0, (a, b) -> a + b);
        return Integer.toString(result);
    }

    public static String getSecondPuzzleResult(String input) {
        List<Card> cardList = parse(input);
        ListIterator<Card> iter = cardList.listIterator(cardList.size());
        List<Integer> numMatchesList = new ArrayList<>();
        while (iter.hasPrevious()) {
            Card card = iter.previous();
            var intersection = new HashSet<>(card.currentNumbers);
            intersection.retainAll(card.winningNumbers);
            int numMatches = intersection.size();
            numMatchesList.add(numMatches);
        }
        int totalMatchingNumbers = cardList.get(0).winningNumbers.size();
        ArrayList<Integer> lastCardPoints = new ArrayList<>();
        IntStream.range(0, totalMatchingNumbers).forEach(i -> lastCardPoints.add(0));

        int result = 0;
        for (int numMatches : numMatchesList) {
            int currentCardPoints = 1;
            for (int nextCardIndex = 0; nextCardIndex < numMatches; nextCardIndex++) {
                currentCardPoints += lastCardPoints.get(nextCardIndex);
            }
            lastCardPoints.remove(totalMatchingNumbers - 1);
            lastCardPoints.add(0, currentCardPoints);
            result += currentCardPoints;
        }
        
        return Integer.toString(result);
    }

    private static List<Card> parse(String input) {
        var bufferedReader = new BufferedReader(new StringReader(input));
        return bufferedReader.lines().map(line -> {
            String[] numbers = line.substring(line.indexOf(":") + 2).split(" \\| ");
            return new Card(
                    Arrays.stream(numbers[0].trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet()),
                    Arrays.stream(numbers[1].trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet()));
        }).collect(Collectors.toList());
    }
}
