package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DayXXTest {

    public static String firstBasicInput = """
            Basic input for the first problem
            """;
    public static String secondBasicInput = """
            Basic input for the second problem
            """;
    public static String firstBasicResult = "Solution to the first problem";
    public static String secondBasicResult = "Solution to the second problem";

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = DayXX.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = DayXX.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
