package sugui.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import sugui.util.Pair;

public class Day08 {

    public static Path inputPath = Paths.get("resources/input08.txt");

    enum Instruction {
        LEFT, RIGHT
    }

    private record Network(List<Instruction> instructions, Map<String, Pair<String, String>> nodes) {
    }

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    private static Network parse(String input) {
        String[] splittedInput = input.split("\n\n");
        List<Instruction> instructions = splittedInput[0].chars()
                .mapToObj(c -> c == 'L' ? Instruction.LEFT : Instruction.RIGHT).collect(Collectors.toList());
        Map<String, Pair<String, String>> nodes = new HashMap<>();
        Pattern nodeName = Pattern.compile("(\\w+) = \\((\\w+), (\\w+)\\)");
        for (String line : splittedInput[1].split("\n")) {
            Matcher matcher = nodeName.matcher(line);
            matcher.find();
            String mainNode = matcher.group(1);
            String leftNode = matcher.group(2);
            String rightNode = matcher.group(3);
            nodes.put(mainNode, new Pair<>(leftNode, rightNode));
        }
        return new Network(instructions, nodes);
    }

    public static String getFirstPuzzleResult(String input) {
        Network network = parse(input);
        int hops = hops(network, "AAA", "ZZZ");
        return Integer.toString(hops);
    }

    private static int hops(Network network, String nodeName, String end) {
        String currNode = nodeName;
        int hops = 0;
        while (!currNode.endsWith(end)) {
            Instruction instruction = network.instructions.get(hops % network.instructions.size());
            currNode = instruction == Instruction.LEFT ? network.nodes.get(currNode)._1()
                    : network.nodes.get(currNode)._2();
            hops++;
        }
        return hops;
    }

    private static long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    private static long mcm(long x, long y) {
        return (x * y) / gcd(x, y);
    }

    public static String getSecondPuzzleResult(String input) {
        Network network = parse(input);
        var result = network.nodes.keySet().stream().filter(node -> node.endsWith("A"))
                .map(node -> (long)hops(network, node, "Z")).reduce((ac, i) -> mcm(ac, i));
        return Long.toString(result.get());
    }
}
