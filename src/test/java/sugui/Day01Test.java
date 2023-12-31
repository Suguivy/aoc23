package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import sugui.day01.Day01;

/**
 * Unit test for simple App.
 */
public class Day01Test {
    public static String firstBasicInput = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """;
    public static String secondBasicInput = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """;
    public static String firstBasicResult = "142";
    public static String secondBasicResult = "281";

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = Day01.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = Day01.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
