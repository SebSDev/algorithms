import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Sebastian Schäffler
 * created at 07.10.2018
 * description:
 */
public class SortingTest
{
    int[] a = {35, 22, 10, 51, 48};
    int[] expectedAsc = {10, 22, 35, 48, 51};
    int[] expectedDesc = {51, 48, 35, 22, 10};

    @Before
    public void init()
    {
        a[0] = 35;
        a[1] = 22;
        a[2] = 10;
        a[3] = 51;
        a[4] = 48;
    }

    @Test
    public void testBubbleSort()
    {
        Sorting.bubbleSort(a);

        assertArrayEquals(expectedAsc, a);
    }

    @Test
    public void testInsertionSort()
    {
        Sorting.insertionSort(a);

        assertArrayEquals(expectedAsc, a);
    }
}
