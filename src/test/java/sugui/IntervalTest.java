package sugui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sugui.util.Interval;

/**
 * Unit test for simple App.
 */
public class IntervalTest {

    public static Interval interval1 = new Interval(2, 4);
    public static Interval interval2 = new Interval(7, 9);
    public static Interval union = interval1.union(interval2);

    @Test
    public void containsTest() {
        assertTrue(interval1.contains(2));
        assertTrue(interval1.contains(3));
        assertFalse(interval1.contains(1));
        assertFalse(interval1.contains(8));
        assertFalse(interval1.contains(10));
    }

    @Test
    public void unionTest() {
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertFalse(union.contains(4));
        assertFalse(union.contains(5));
        assertFalse(union.contains(6));
        assertTrue(union.contains(7));
        assertTrue(union.contains(8));
        assertFalse(union.contains(9));
    }
}
