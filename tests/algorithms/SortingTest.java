package algorithms;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Sebastian Schäffler
 * created at 07.10.2018
 * description:
 */
public class SortingTest
{
    // array with random numbers
    int[] a1 = { 35, 22, 10, 51, 48 };
    int[] expectedAsc1 = { 10, 22, 35, 48, 51 };
    int[] expectedDesc1 = { 51, 48, 35, 22, 10 };

    // array with already ascending sorted numbers
    int[] a2 = { 1, 2, 3, 4, 5 };
    int[] expectedAsc2 = { 1, 2, 3, 4, 5 };
    int[] expectedDesc2 = { 5, 4, 3, 2, 1 };

    // array with already descending sorted numbers
    int[] a3 = { 5, 4, 3, 2, 1 };
    int[] expectedAsc3 = { 1, 2, 3, 4, 5 };
    int[] expectedDesc3 = { 5, 4, 3, 2, 1 };

    @Before
    public void init()
    {
        a1[0] = 35;
        a1[1] = 22;
        a1[2] = 10;
        a1[3] = 51;
        a1[4] = 48;

        a2[0] = 1;
        a2[1] = 2;
        a2[2] = 3;
        a2[3] = 4;
        a2[4] = 5;

        a3[0] = 5;
        a3[1] = 4;
        a3[2] = 3;
        a3[3] = 2;
        a3[4] = 1;
    }

    @Test
    public void testBubbleSort()
    {
        Sorting.bubbleSort(a1);
        Sorting.bubbleSort(a2);
        Sorting.bubbleSort(a3);

        assertArrayEquals(expectedAsc1, a1);
        assertArrayEquals(expectedAsc2, a2);
        assertArrayEquals(expectedAsc2, a3);
    }

    @Test
    public void testInsertionSort()
    {
        Sorting.insertionSort(a1);
        Sorting.insertionSort(a2);
        Sorting.insertionSort(a3);

        assertArrayEquals(expectedAsc1, a1);
        assertArrayEquals(expectedAsc2, a2);
        assertArrayEquals(expectedAsc3, a3);
    }

    @Test
    public void testMergeSort()
    {
        Sorting.mergeSort(a1);
        Sorting.mergeSort(a2);
        Sorting.mergeSort(a3);

        assertArrayEquals(expectedAsc1, a1);
        assertArrayEquals(expectedAsc2, a2);
        assertArrayEquals(expectedAsc3, a3);
    }

    @Test
    public void testHeapSort()
    {
        Sorting.heapSort(a1);
        Sorting.heapSort(a2);
        Sorting.heapSort(a3);

        assertArrayEquals(expectedAsc1, a1);
        assertArrayEquals(expectedAsc2, a2);
        assertArrayEquals(expectedAsc3, a3);
    }

    @Test
    public void testCountingSortSimpleArray()
    {
        Integer[] input = { 2, 5, 3, 0, 2, 3, 0, 3 };
        Integer[] expected = { 0, 0, 2, 2, 3, 3, 3, 5 };
        Integer[] result = Sorting.countingSort(input, 5);
        assertArrayEquals("Sorting Integers in ascending order", expected, result);
        System.out.println("Result" + result.toString());
    }

    @Test
    public void testCountingSortStableSort()
    {
        Integer[] input = { 2, 5, 3, 0, 2, 3, 0, 3 };
        Integer[] result = Sorting.countingSort(input, 5);
        assertSame("Checking stable countingSort for 1st 3", input[2], result[4]);
        assertSame("Checking stable countingSort for 2nd 3", input[5], result[5]);
        assertSame("Checking stable countingSort for 3rd 3", input[7], result[6]);
    }
}
