package sugui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    public static Path inputPath = Paths.get("resources/input02.txt");

    public static int MAX_RED_CONTENTS = 12;
    public static int MAX_GREEN_CONTENTS = 13;
    public static int MAX_BLUE_CONTENTS = 14;

    private record Bunch(int red, int green, int blue) {
    }

    private record Game(int id, List<Bunch> bunches) {
    }

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static List<Game> parse(String input) {
        var bufferedReader = new BufferedReader(new StringReader(input));
        List<Game> games = bufferedReader.lines().map(line -> {
            String[] splittedGame = line.split(": ");
            String[] splittedBunches = splittedGame[1].split("; ");
            int gameId = Integer.parseInt(splittedGame[0].split(" ")[1]);
            List<Bunch> bunches = new ArrayList<>();
            for (String bunch : splittedBunches) {
                int redCubes = 0;
                int greenCubes = 0;
                int blueCubes = 0;
                String[] splittedCubes = bunch.split(", ");
                for (String cube : splittedCubes) {
                    String[] splittedCube = cube.split(" ");
                    int count = Integer.parseInt(splittedCube[0]);
                    switch (splittedCube[1]) {
                        case "red":
                            redCubes = count;
                            break;
                        case "green":
                            greenCubes = count;
                            break;
                        case "blue":
                            blueCubes = count;
                            break;
                        default:
                            break;
                    }
                }
                bunches.add(new Bunch(redCubes, greenCubes, blueCubes));
            }
            return new Game(gameId, bunches);
        }).collect(Collectors.toList());
        return games;
    }

    public static String getFirstPuzzleResult(String input) {
        List<Game> games = parse(input);
        int result = games.stream()
                .filter(game -> {
                    return game.bunches.stream().allMatch(bunch -> bunch.red <= MAX_RED_CONTENTS
                            && bunch.green <= MAX_GREEN_CONTENTS && bunch.blue <= MAX_BLUE_CONTENTS);
                })
                .map(game -> game.id)
                .reduce(0, (a, b) -> a + b);
        return Integer.toString(result);
    }

    public static String getSecondPuzzleResult(String input) {
        List<Game> games = parse(input);
        int result = games.stream()
                .map(game -> game.bunches.stream().reduce(new Bunch(0, 0, 0),
                        (currBunch, newBunch) -> new Bunch(Math.max(currBunch.red, newBunch.red),
                                Math.max(currBunch.green, newBunch.green), Math.max(currBunch.blue, newBunch.blue))))
                .map(bunch -> bunch.red * bunch.green * bunch.blue)
                .reduce(0, (a, b) -> a + b);
        return Integer.toString(result);
    }
}
