package algorithms;

import java.util.Arrays;

/**
 * @author Sebastian Schäffler
 * created at 13.10.2018
 * description: Implementing some Divide and Conquer algorithms here
 */
public class DivideAndConquer
{

    /**
     * Solves the 'Towers of Hanoi'-problem in a recursive way
     * @param n number of discs to move
     * @param from start position of the discs
     * @param via helper tower
     * @param to target position of the discs
     */
    public static void hanoiRecursive(int n, String from, String via, String to)
    {
        if (n == 1)
        {
            System.out.println(from + "->" + to);
        }
        if (n > 1)
        {
            hanoiRecursive(n-1, from, to, via);
            System.out.println(from + "->" + to);
            hanoiRecursive(n-1, via, from, to);
        }
    }

    //----------------------------------------- my own implementation START
    public static int[] getMaxSubarray (int[] a)
    {
        return getMaxSubarray(a, 0, a.length - 1);
    }

    public static int[] getMaxSubarray(int[] a, int low, int high)
    {
        if (low == high) return a;

        int mid = (low + high) / 2;

        int[] leftMax = getMaxSubarray(a, 0, mid);
        int[] rightMax = getMaxSubarray(a, mid + 1, high);
        int[] crossingMax = getMaxCrossingSubarray(a, low, mid, high);

        int leftSum = 0;
        int rightSum = 0;
        int crossingSum = 0;

        for (int i = 0; i < leftMax.length; i++)
        {
            leftSum += leftMax[i];
        }

        for (int i = 0; i < rightMax.length; i++)
        {
            rightSum += rightMax[i];
        }

        for (int i = 0; i < crossingMax.length; i++)
        {
            crossingSum += crossingMax[i];
        }

        if (leftSum >= rightSum && leftSum >= crossingSum)
            return leftMax;

        if (rightSum >= leftSum && rightSum >= crossingSum)
            return rightMax;

        return crossingMax;
    }

    private static int[] getMaxCrossingSubarray(int[] a, int low, int mid, int high)
    {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;

        int maxLeft = 0;
        int maxRight = 0;

        for (int i = mid; i >= 0; i--)
        {
            sum += a[i];

            if (sum > leftSum)
            {
                leftSum = sum;
                maxLeft = i;
            }
        }

        sum = 0;

        for (int i = mid + 1; i < a.length; i++)
        {
            sum += a[i];

            if (sum > rightSum)
            {
                rightSum = sum;
                maxRight = i;
            }
        }

        return Arrays.copyOfRange(a, maxLeft, maxRight + 1);
    }
    //----------------------------------------- my own implementation END

}
