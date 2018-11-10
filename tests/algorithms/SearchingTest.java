package algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Sebastian Schäffler
 * created at 06.11.2018
 * description:
 */
public class SearchingTest
{
    @Test
    public void testQuickSelect()
    {
        int[] a = { 2, 4, 6, 1, 3 , 8, 23 };

        assertEquals(6, Searching.quickSelect(a, 0, a.length - 1, 3));
    }
}
