package algorithms;

/**
 * @author Sebastian Schäffler
 * created at 06.11.2018
 * description: provides static util methods
 */
abstract class AlgorithmUtils
{
    private AlgorithmUtils ()
    {

    }

    /**
     * exchanges the array elements with index i and j
     * @param a array in which to exchange the elements
     * @param i index of the first element
     * @param j index of the second element
     */
    static void exchange(int[] a, int i, int j)
    {
        int tmp = a[i];

        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * exchanges the array elements with index i and j
     * @param a array in which to exchange the elements
     * @param i index of the first element
     * @param j index of the second element
     */
    static void exchange(Object[] a, int i, int j)
    {
        Object tmp = a[i];

        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * builds a max heap
     * @param a array to build the max heap of
     */
    static void buildMaxHeap (int[] a)
    {
        int n = a.length;

        for (int i = n / 2; i >= 0; i--)
        {
            maxHeapify(a, i, n - 1);
        }
    }

    /**
     * building a max heap of a parent element (knot) and its children
     * @param a array which holds the elements
     * @param i index of the knot
     * @param n max index of the array
     */
    static void maxHeapify (int[] a, int i, int n)
    {
        int l = 2 * i + 1; // index of the left element of the knot with index i
        int r = 2 * i + 2; // index of the right element of the knot with index i
        int largest = 0;

        // first we check if the index l can be part of the array
        // then if the left element is larger than its parent element
        // => we set the index of the largest element to l
        if (l <= n && a[l] > a[i])
        {
            largest = l;
        }
        else
        {
            largest = i;
        }

        // now we check if the right element is the largest of the three
        if (r <= n && a[r] > a[largest])
        {
            largest = r;
        }

        // if the original knot is not the largest element, we exchange it with the largest element
        if (largest != i)
        {
            int tmp = a[i];
            a[i] = a[largest];
            a[largest] = tmp;

            maxHeapify(a, largest, n);
        }
    }

    /**
     * Divides an array into two partitions where left of the pivot (value at r index)
     * are all numbers smaller than the pivot and right all numbers that are bigger than the pivot
     * @param a array to divide
     * @param l leftmost index of the current partition
     * @param r rightmost index of the current partition
     * @return index of the pivot element
     */
    static int partition(int[] a, int l, int r)
    {
        int pivot = a[r];
        int i = l;
        int j = r - 1;

        do
        {
            while(a[j] >= pivot && j > l)
            {
                j--;
            }

            while (a[i] <= pivot && i < r)
            {
                i++;
            }

            if (i < j)
            {
                exchange(a, i, j);
            }
        } while (i < j);

        if (a[i] > pivot)
        {
            exchange(a, i, r);
        }

        return i;
    }
}
