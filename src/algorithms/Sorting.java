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
                    AlgorithmUtils.exchange(a, j, j-1);
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
        AlgorithmUtils.buildMaxHeap(a);

        for (int i = a.length - 1; i >= 1; i--)
        {
            // exchanging a[0] and a[i]
            AlgorithmUtils.exchange(a, 0, i);

            AlgorithmUtils.maxHeapify(a, 0, i - 1);
        }
    }

    /**
     * sorts an array using countingSort
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
            // now we have to decrease the amount of numbers that are before our current element,
            // because we just added one to the new list
            c[a[i]]--;

            // c[a[i]] tells us how many elements are before the one
            // so the index of our current a[i] is the amount of elements before (because the index starts at 0)
            b[c[a[i]]] = a[i];
        }

        return b;
    }

    /**
     * sorts an array of integers in ascending order using radixSort
     * @param a the array to sort
     * @param d the maximum amount of digits a value has in this array
     * @return the sorted array
     */
    public static Integer[] radixSort(Integer[] a, int d)
    {
        Integer[] result = a;

        // iterating through all the digits beginning with the least significant one
        for (int i = 1; i <= d; i++)
        {
            // using counting sort for the current digit
            result = countingSortRadix(result, i);
        }

        return result;
    }

    /**
     * sorts an array using countingSort
     * @param a integer array given as input
     * @param k which digit should be sorted for
     * @return input array is sorted in a stable manner
     */
    public static Integer[] countingSortRadix(Integer[] a, Integer k)
    {
        int pot = (int)Math.pow(10, k-1); // the 10^k value that we need to get the right digit
        Integer[] b = new Integer[a.length]; // result array
        int[] c = new int[10];

        // counting the number of occurrences of each value in array a
        for (Integer i : a)
        {
            c[(i / pot) % 10]++;
        }

        // counting how many Integers are <= the current index
        // and save it to the current index
        for (int i = 1; i < c.length; i++)
        {
            c[i] += c[i-1];
        }

        // iterate through all the elements and set them on the right position in the result array
        for (int i = a.length-1; i >= 0; i--)
        {
            // (a[i] / pot) % 10 is the digit for this element
            // c[...] - 1 is how many digits are <= this digit, so this is the actual position of this element in
            // b[...]
            b[ c[ (a[i] / pot) % 10 ] - 1 ] = a[i];

            // reducing the amount of digits that are <= this one because we just "used" one
            c[ (a[i] / pot) % 10 ]--;
        }

        return b;
    }

    /**
     * directly sorts the array of w-character strings in ascending order.
     * Assumption: - each char is an 8-bit value (extended ASCII)
     *             - all strings have the same length of w
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sortStrings(String[] a, int w)
    {
        // sorting the single characters from right to left
        for (int i = w; i > 0; i--)
        {
            // getting the current characters
            Integer[] tmp = new Integer[a.length];
            for (int j = 0; j < a.length; j++)
            {
                tmp[j] = (int)a[j].charAt(i-1);
            }

            // we only have extended ASCII characters => 0-255
            // sorting the characters at the current position
            tmp = countingSort(tmp, 255);

            int currStart = 0;
            for (int j = 0; j < tmp.length; j++) // iterating through sorted chars
            {
                for (int k = currStart; k < a.length; k++) // iterating through "a", starting from first unsorted element
                {
                    if (a[k].charAt(i-1) == tmp[j])
                    {
                        AlgorithmUtils.exchange(a, currStart, k);

                        // in the next iteration we should not go through the already sorted elements
                        currStart++;
                        break;
                    }
                }
            }

        }
    }
}
