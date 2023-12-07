package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Day07Test {

    public static String firstBasicInput = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            """;
    public static String secondBasicInput = firstBasicInput;
    public static String firstBasicResult = "6440";
    public static String secondBasicResult = "5905";

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = Day07.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = Day07.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
