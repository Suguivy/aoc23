package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Day06Test {

    public static String firstBasicInput = """
            Time:      7  15   30
            Distance:  9  40  200
            """;
    public static String secondBasicInput = firstBasicInput;
    public static String firstBasicResult = "288";
    public static String secondBasicResult = "71503";

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = Day06.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = Day06.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
