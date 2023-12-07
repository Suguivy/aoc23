package sugui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import sugui.day03.Day03;

/**
 * Unit test for simple App.
 */
public class Day03Test {

    public static String firstBasicInput = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..        
            """;
    public static String secondBasicInput = firstBasicInput;
    public static String firstBasicResult = "4361";
    public static String secondBasicResult = "467835";

    @Test
    public void firstBasicCase() throws IOException {
        String actualResult = Day03.getFirstPuzzleResult(firstBasicInput);
        assertEquals(firstBasicResult, actualResult);
    }

    @Test
    public void secondBasicCase() throws IOException {
        String actualResult = Day03.getSecondPuzzleResult(secondBasicInput);
        assertEquals(secondBasicResult, actualResult);
    }
}
