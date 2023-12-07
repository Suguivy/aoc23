package sugui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06 {

    public static Path inputPath = Paths.get("resources/input06.txt");

    private record Race(long time, long bestMark) {
    }

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static List<Race> parseFirst(String input) {
        String[] lines = input.split("\n");
        String[] times = lines[0].substring(5).trim().split(" +");
        String[] distance = lines[1].substring(9).trim().split(" +");
        List<Race> races = IntStream.range(0, times.length)
                .mapToObj(i -> new Race(Long.parseLong(times[i]), Long.parseLong(distance[i])))
                .collect(Collectors.toList());
        return races;
    }

    public static Race parseSecond(String input) {
        String[] lines = input.split("\n");
        long time = Long.parseLong(
                Arrays.asList(lines[0].substring(5).trim().split(" +")).stream().collect(Collectors.joining("")));
        long distance = Long.parseLong(Arrays.asList(lines[1].substring(9).trim().split(" +")).stream()
                .collect(Collectors.joining("")));
        return new Race(time, distance);
    }

    public static String getFirstPuzzleResult(String input) {
        List<Race> races = parseFirst(input);
        long result = races.stream().map(race -> {
            var firstOptimal = 1 + Math
                    .floor((-race.time + Math.sqrt(Math.pow(race.time, 2) - (-4) * (-race.bestMark))) / (-2));
            var lastOptimal = (-1) + Math
                    .ceil((-race.time - Math.sqrt(Math.pow(race.time, 2) - (-4) * (-race.bestMark))) / (-2));
            return (long) (lastOptimal - firstOptimal + 1);
        }).reduce(1l, (a, b) -> a * b);
        return Long.toString(result);
    }

    public static String getSecondPuzzleResult(String input) {
        Race race = parseSecond(input);
        var firstOptimal = 1 + Math
                .floor((-race.time + Math.sqrt(Math.pow(race.time, 2) - (-4) * (-race.bestMark))) / (-2));
        var lastOptimal = (-1) + Math
                .ceil((-race.time - Math.sqrt(Math.pow(race.time, 2) - (-4) * (-race.bestMark))) / (-2));
        long result = (long) (lastOptimal - firstOptimal + 1);
        return Long.toString(result);
    }
}
