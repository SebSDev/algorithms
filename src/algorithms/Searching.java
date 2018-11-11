package algorithms;

/**
 * @author Sebastian Schäffler
 * created at 06.11.2018
 * description: Implementing some searching algorithms in this class
 */
public class Searching
{
    /**
     * searches an array for the i-th biggest element
     * @param a array to search in
     * @param l left border index
     * @param r right border index
     * @param i i-th biggest element is searched in the array
     * @return the i-th biggest element (the actual value, not the index)
     */
    public static int quickSelect (int[] a, int l, int r, int i)
    {
        // l and r index are on the same element. We found our element
        if (l == r)
        {
            return a[l];
        }

        // pivot
        int p = AlgorithmUtils.partition(a, l, r);

        // number of elements that are right of the pivot (+ pivot)
        int k = r - p + 1;

        // if the number of elements right of the pivot == i we got the element
        if (k == i)
        {
            return a[p];
        }
        else if (i > k) // the searched element is in the left partition
        {
            return quickSelect(a, l, p - 1, i - k);
        }
        else  // the searched element is in the right partition
        {
            return quickSelect(a, p + 1, r, i);
        }
    }

}
