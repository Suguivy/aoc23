package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import sugui.day09.Day09;

/**
 * Unit test for simple App.
 */
public class Day09Test {

    public static String firstBasicInput = """
            0   3   6   9  12  15
            1   3   6  10  15  21
            10  13  16  21  30  45
            """;
    public static String secondBasicInput = """
            0   3   6   9  12  15
            1   3   6  10  15  21
            10  13  16  21  30  45
            """;
    public static String firstBasicResult = Integer.toString(18 + 28 + 68);
    public static String secondBasicResult = Integer.toString(-3 + 0 + 5);

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = Day09.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = Day09.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
