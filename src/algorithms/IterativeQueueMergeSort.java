package algorithms;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Iterative MergeSort using Queues
 * idea and parts of the code from Sedgewick et al., adapted by W. Mühlbauer
 */
public class IterativeQueueMergeSort
{

    /**
     * central queue: a queue that stores queues!
     * - contains in the beginning n queues (each with 1 element),
     * - repeatedly merge until only one queue remains
     * Hint: Java Collection Interface "Queue"
     * - "Queue" is only an interface, here a "LinkedList" is always used as its implementation
     * - enqueue: method add(.)
     * - dequeue: method remove(.)
     * - peek:    methoed peek(.) or element(.) to examine element at head of queue without removing it.
     */
    private static Queue<Queue<Comparable>> centralQueue;


    /**
     * sorts array a. Note: The input array is overwritten with the result.
     * @param a array to be sorted
     * @author Sebastian Schäffler
     */
    public static void sort(Comparable[] a)
    {
        centralQueue = new LinkedList<Queue<Comparable>>();
        Queue<Comparable> tmpQueue;

        // creating a queue of queues with the data of the array
        for (Comparable e : a)
        {
            tmpQueue = new LinkedList<Comparable>();
            tmpQueue.add(e);
            centralQueue.add(tmpQueue);
        }

        // merging as long as our queue has more than 1 element
        while (centralQueue.size() > 1)
        {
            centralQueue.add(merge(centralQueue.remove(), centralQueue.remove()));
        }

        // adding our sorted results to the initial array
        tmpQueue = centralQueue.remove();
        int counter = 0;
        for (Comparable e : tmpQueue)
        {
            a[counter] = e;
            counter++;
        }

    }


    /**
     * Merges 2 sorted (!!!) queues. Returns 1 queue that contains all elements of the two input
     * queues in sorted (!) order.
     * @param a first queue (sorted!)
     * @param b second queue (sorted!)
     * @return queue with elements of a and b in sorted order
     * @author Sebastian Schäffler
     */
    private static Queue<Comparable> merge(Queue<Comparable> a, Queue<Comparable> b)
    {
        Queue<Comparable> c = new LinkedList<Comparable>();

        int n = a.size() + b.size();
        Comparable aElement = a.remove();
        Comparable bElement = b.remove();

        for (int i = 0; i < n; i++)
        {
            // if aElement <= bElement add aElement to the queue, else add bElement
            if (bElement == null || aElement != null && aElement.compareTo(bElement) <= 0)
            {
                c.add(aElement);
                try
                {
                    aElement = a.remove();
                }
                catch (NoSuchElementException e)
                {
                    aElement = null;
                }
            }
            else
            {
                c.add(bElement);
                try
                {
                    bElement = b.remove();
                }
                catch (NoSuchElementException e)
                {
                    bElement = null;
                }
            }
        }

        return c;
    }

}