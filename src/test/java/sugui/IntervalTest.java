package sugui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import sugui.util.Interval;

/**
 * Unit test for simple App.
 */
public class IntervalTest {

    public static Interval interval1 = new Interval(2, 8);

    @Test
    public void containsTest() throws IOException {
        assertTrue(interval1.contains(2));
        assertTrue(interval1.contains(5));
        assertTrue(interval1.contains(7));
        assertFalse(interval1.contains(1));
        assertFalse(interval1.contains(8));
        assertFalse(interval1.contains(10));
    }
}
