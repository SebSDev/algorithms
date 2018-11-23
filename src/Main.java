import data_structures.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sebastian Schäffler
 * created at 07.10.2018
 * description:
 */
public class Main
{
    public static void main(String[] args)
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> list;

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

        for (Integer i : list)
        {
            tree.insert(i);
        }

        tree.printPreOrderList();
    }
}
