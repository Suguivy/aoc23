package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import sugui.day08.Day08;

/**
 * Unit test for simple App.
 */
public class Day08Test {

    public static String firstBasicInput = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
            """;
    public static String secondBasicInput = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
            """;
    public static String firstBasicResult = "2";
    public static String secondBasicResult = "6";

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = Day08.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = Day08.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
