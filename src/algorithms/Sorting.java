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
     * @param a array to countingSort
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
     * Sorts an integer array using insertion countingSort
     * @param a array to countingSort
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
     * Sorts an integer array using merge countingSort
     * @param a array to countingSort
     */
    public static void mergeSort (int[] a)
    {
        mergeSort(a, 0, a.length - 1);
    }

    /**
     * Sorts an integer array using merge countingSort
     * @param a array to countingSort
     * @param l left boundary index of the current subarray to countingSort
     * @param r right boundary index of the current subarray to countingSort
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
     * @param a complete array to countingSort
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


    /**
     * Sorts an array in ascending order using heapsort
     * @param a array to countingSort
     */
    public static void heapSort (int[] a)
    {
        buildMaxHeap(a);

        for (int i = a.length - 1; i >= 1; i--)
        {
            // exchanging a[0] and a[i]
            int tmp = a[i];
            a[i] = a[0];
            a[0] = tmp;

            maxHeapify(a, 0, i - 1);
        }
    }

    /**
     * builds a max heap
     * @param a array to build the max heap of
     */
    private static void buildMaxHeap (int[] a)
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
    private static void maxHeapify (int[] a, int i, int n)
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
     * sorts an array using counting countingSort
     * @param a integer array given as input
     * @param k defines maximum integer value in input; all integers must in range [0..k]
     * @return input array is sorted in a stable manner
     */
    public static Integer[] countingSort(Integer[] a, Integer k)
    {
        Integer[] b = new Integer[a.length]; // result array
        int[] c = new int[k + 1];

        // counting the number of occurrences of each value in a
        for (Integer i : a)
        {
            c[i]++;
        }

        // counting how many Integers are <= the current index
        // and save it to the current index
        for (int i = 1; i < c.length; i++)
        {
            c[i] += c[i-1];
        }

        // iterating through the elements in a and set them to the right position in b
        for (int i = 0; i < a.length; i++)
        {
            // c[a[i]] tells us how many elements are before the one
            // so the index of our current a[i] is the amount of elements before -1 (because the index starts at 0)
            b[c[a[i]] - 1] = a[i];

            // now we have to decrease the amount of numbers that are before our current element,
            // because we just added one to the new list
            c[a[i]]--;
        }

        return b;
    }
}
