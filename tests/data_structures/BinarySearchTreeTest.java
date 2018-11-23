package data_structures;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Sebastian Schäffler
 * created at 22.11.2018
 * description:
 */
public class BinarySearchTreeTest
{
    BinarySearchTree<Integer> tree;
    List<Integer> list;
    List<Integer> ascSortedList;

    @Before
    public void setup()
    {
        tree = new BinarySearchTree<>();
        list = new LinkedList<>();
        list.add(30);
        list.add(18);
        list.add(8);
        list.add(23);
        list.add(20);
        list.add(22);
        list.add(16);
        list.add(17);
        list.add(47);
        list.add(55);
        list.add(69);
        list.add(88);
        list.add(95);
        list.add(50);
        list.add(85);
        list.add(60);

        ascSortedList = list;
        ascSortedList.sort((o1, o2) -> { return Integer.compare(o1, o2); });

        for (Integer i : list)
        {
            tree.insert(i);
        }
    }

    @Test
    public void testSize()
    {
        // TODO: adapt for deleting elements

        assertEquals(list.size(), tree.size());
        tree.insert(72);
        assertEquals(list.size() + 1, tree.size());
    }

    @Test
    public void testSearch()
    {
        for (Integer i : list)
        {
            assertEquals(i, tree.search(i).value);
        }
    }

    @Test
    public void testMinMax()
    {
        assertEquals(ascSortedList.get(0), tree.getMin());
        assertEquals(ascSortedList.get(ascSortedList.size() - 1), tree.getMax());
    }

    @Test
    public void testSuccessor()
    {
        // testing the successor of each element in the tree
        for (int i = 0; i < list.size() - 1; i++)
        {
            assertEquals(ascSortedList.get(i + 1), tree.getSuccessor(tree.search(ascSortedList.get(i))));
        }
    }

    @Test
    public void testPredecessor()
    {
        // testing the predecessor of each element in the tree
        for (int i = 0; i < list.size() - 1; i++)
        {
            assertEquals(ascSortedList.get(i), tree.getPredecessor(tree.search(ascSortedList.get(i + 1))));
        }
    }
}
