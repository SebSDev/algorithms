/**
 * @author Sebastian Schäffler
 * created at 07.10.2018
 * description: Different sorting algorithms are implemented in this class
 */
public class Sorting
{
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

    public static void insertionSort (int[] a)
    {
        int key, i;

        for (int j = 1; j <= a.length - 1; j++)
        {
            key = a[j];
            i = j - 1;

            while (i >= 0 && a[i] > key)
            {
                a[i + 1] = a[i];
                i = i - 1;
            }

            a[i + 1] = key;
        }
    }
}
