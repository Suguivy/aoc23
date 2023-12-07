package sugui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import sugui.util.Pair;

public class Day05 {

    public static Path inputPath = Paths.get("resources/input05.txt");

    private record ProductionKit(List<Long> seeds, List<List<MapInfo>> maps) {
    }

    private record ProductionKitRanged(List<Pair<Long, Long>> seedPairs, List<List<MapInfo>> maps) {
    }

    public record MapInfo(long destination, long source, long range) {
    }

    public static long getLocation(List<MapInfo> mapper, long origin) {
        for (var mapInfo : mapper) {
            var originSourceDiff = origin - mapInfo.source;
            if (originSourceDiff >= 0 && originSourceDiff < mapInfo.range)
                return mapInfo.destination + originSourceDiff;
        }
        return origin;
    }

    public static void main(String[] args) throws IOException {
        var input = Files.readString(inputPath);
        var firstResult = getFirstPuzzleResult(input);
        var secondResult = getSecondPuzzleResult(input);
        System.out.println("First result: " + firstResult);
        System.out.println("Second result: " + secondResult);
    }

    public static ProductionKit parseFirst(String input) {
        String[] parts = input.split("\\n\\n", 2);
        List<Long> seeds = Arrays.stream(parts[0].substring(7).split(" ")).map(Long::parseLong)
                .collect(Collectors.toList());

        String sepRegex = "\\n*[^\\n\\d]+\\n";
        String[] mapsData = parts[1].split(sepRegex, 2)[1].split(sepRegex);
        List<List<MapInfo>> maps = new ArrayList<>();
        for (var mapData : mapsData) {
            var bufferedReader = new BufferedReader(new StringReader(mapData));
            List<MapInfo> map = bufferedReader.lines().map(line -> {
                String[] data = line.split(" ");
                return new MapInfo(Long.parseLong(data[0]), Long.parseLong(data[1]), Long.parseLong(data[2]));
            }).collect(Collectors.toList());
            maps.add(map);
        }
        return new ProductionKit(seeds, maps);
    }

    public static ProductionKitRanged parseSecond(String input) {
        String[] parts = input.split("\\n\\n", 2);
        Iterator<String> seedsData = Arrays.asList(parts[0].substring(7).split(" ")).iterator();
        List<Pair<Long, Long>> seedPairs = new ArrayList<>();
        while (seedsData.hasNext()) {
            long seedInit = Long.parseLong(seedsData.next());
            long seedRange = Long.parseLong(seedsData.next());
            seedPairs.add(new Pair<Long, Long>(seedInit, seedRange));
        }
        String sepRegex = "\\n*[^\\n\\d]+\\n";
        String[] mapsData = parts[1].split(sepRegex, 2)[1].split(sepRegex);
        List<List<MapInfo>> maps = new ArrayList<>();
        for (var mapData : mapsData) {
            var bufferedReader = new BufferedReader(new StringReader(mapData));
            List<MapInfo> map = bufferedReader.lines().map(line -> {
                String[] data = line.split(" ");
                return new MapInfo(Long.parseLong(data[0]), Long.parseLong(data[1]), Long.parseLong(data[2]));
            }).collect(Collectors.toList());
            maps.add(map);
        }
        return new ProductionKitRanged(seedPairs, maps);
    }

    public static String getFirstPuzzleResult(String input) {
        ProductionKit prodKit = parseFirst(input);
        var locations = new ArrayList<>(prodKit.seeds);
        for (var map : prodKit.maps) {
            for (int i = 0; i < locations.size(); i++) {
                long currentLocation = locations.get(i);
                locations.set(i, getLocation(map, currentLocation));
            }
        }
        long minimumLocation = locations.stream().min(Long::compare).get();
        return Long.toString(minimumLocation);
    }

    public static String getSecondPuzzleResult(String input) {
        ProductionKitRanged prodKit = parseSecond(input);
        long minLocation = Long.MAX_VALUE;
        for (var seedPair : prodKit.seedPairs) {
            long initSeed = seedPair._1();
            long maxSeed = initSeed + seedPair._2();
            for (long seed = initSeed; seed < maxSeed; seed++) {
                long currLocation = seed;
                for (var map : prodKit.maps) {
                    long newLocation = getLocation(map, currLocation);
                    currLocation = newLocation;
                }
                if (minLocation > currLocation) {
                    minLocation = currLocation;
                }
            }
        }
        return Long.toString(minLocation);
    }
}
