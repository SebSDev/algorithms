package algorithms;

/**
 * @author Sebastian Schäffler
 * created at 07.10.2018
 * description: Different sorting algorithms are implemented in this class
 */
public class Sorting
{
    /**
     * Sorts an integer array using bubblesort
     * @param a array to sort
     */
    public static void bubbleSort (int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            for (int j = a.length - 1; j >= i; j--)
            {
                if (a[j] < a[j - 1])
                {
                    // exchanging a[j] and a[j - 1]
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
    }

    /**
     * Sorts an integer array using insertion sort
     * @param a array to sort
     */
    public static void insertionSort (int[] a)
    {
        int key, i;

        // iterating over each element of the array starting at the second one
        for (int j = 1; j < a.length; j++)
        {
            key = a[j]; // current element
            i = j - 1;  // index of the element before

            // shifting each element that is left of the key to the right by one as long as its bigger than the key
            while (i >= 0 && a[i] > key)
            {
                a[i + 1] = a[i];
                i--;
            }

            // insert the key at the right position
            a[i + 1] = key;
        }
    }

    /**
     * Sorts an integer array using merge sort
     * @param a array to sort
     */
    public static void mergeSort (int[] a)
    {
        mergeSort(a, 0, a.length - 1);
    }

    /**
     * Sorts an integer array using merge sort
     * @param a array to sort
     * @param l left boundary index of the current subarray to sort
     * @param r right boundary index of the current subarray to sort
     */
    private static void mergeSort (int[] a, int l, int r)
    {
        if (l < r)
        {
            // dividing into two smaller arrays
            int m = (l + r) / 2;
            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);

            // conquer
            merge(a, l, m, r);
        }
    }

    /**
     * merging two already sorted subarrays of the array
     * @param a complete array to sort
     * @param l left boundary index of the first subarray
     * @param m border index between the two subarrays
     * @param r right index boundary of the second subarray
     */
    private static void merge (int[] a, int l, int m, int r)
    {
        int n1 = m - l + 1; // size of the left subarray
        int n2 = r - m;     // size of the right subarray

        // creating two new arrays which represent the left and the right subarrays
        int[] leftA = new int[n1 + 1]; // + 1 because we will add a sentinel value later
        int[] rightA = new int[n2 + 1];

        // inserting the values for the created arrays
        for(int i = 0; i < n1; i++)
        {
            leftA[i] = a[l + i];
        }

        for (int j = 0; j < n2; j++)
        {
            rightA[j] = a[m + 1 + j]; // m + 1 because m is part of the left subarray
        }

        // Adding a sentinel value, so we don't run into an Class ArrayIndexOutOfBoundsException later
        leftA[n1] = Integer.MAX_VALUE;
        rightA[n2] = Integer.MAX_VALUE;

        int i = 0; // index for the left subarray
        int j = 0; // index for the right subarray

        // iterating through both subarray combined (from l to r)
        for (int k = l; k <= r; k++)
        {
            // Now, depending on which number is bigger, we add the next value from left or right to our result
            // until all elements of both subarrays are sorted in the actual array
            if (leftA[i] <= rightA[j])
            {
                a[k] = leftA[i];
                i++;
            }
            else
            {
                a[k] = rightA[j];
                j++;
            }
        }
    }
}
