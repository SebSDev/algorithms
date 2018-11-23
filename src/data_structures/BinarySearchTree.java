package data_structures;

/**
 * @author Sebastian Schäffler
 * created at 22.11.2018
 * description:
 */
public class  BinarySearchTree <T extends Comparable>
{
    private Node root;
    private int size;

    /**
     * represents a tree node
     */
    public class Node
    {
        T value;
        Node left;
        Node right;
        Node parent;

        Node(T value, Node parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }

    /**
     * adds an element to the tree
     * @param value the value of the element to add
     */
    public void insert(T value)
    {
        Node x = root;
        Node parent = null;

        while (x != null)
        {
            parent = x;

            if (value.compareTo(parent.value) < 0)
            {
                x = parent.left;
            }
            else
            {
                x = parent.right;
            }
        }

        // if parent is still null that means we didn't have a single iteration of the loop above
        // which means we don't have a root element, so we just set it
        if (parent == null)
        {
            root = new Node(value, null);
        }
        else if (value.compareTo(parent.value) < 0)
        {
            parent.left = new Node(value, parent);
        }
        else
        {
            parent.right = new Node(value, parent);
        }

        size++;
    }

    /**
     * searches a Node with a certain value
     * @param value the value to look for
     * @return the Node with the value. If nothing has been found, null
     */
    public Node search(T value)
    {
        Node n = root;

        while (n != null)
        {
            if (value.compareTo(n.value) < 0)
            {
                n = n.left;
            }
            else if (value.compareTo(n.value) > 0)
            {
                n = n.right;
            }
            else
            {
                return n;
            }
        }

        return n;
    }

    /**
     * Deletes a Node from the tree
     * @param n The Node to delete
     */
    public void delete(Node n)
    {
        // n has two direct childs
        if (n.right != null && n.left != null)
        {
            // get the successor of n: The smallest element in the right subtree
            Node successor = search(getSuccessor(n));

            // If the successor is not a right child, it is not a direct child of n,
            // so we have to do some extra work - adapting the right hand side childs
            if (successor != n.right)
            {
                // transplanting the right child of the successor in the place of the successor
                // note: the successor does not have a left child since its the smallest element in its subtree
                transplant(successor, successor.right);

                // now we set the right child of the successor to the right child of the element we want to delete
                successor.right = n.right;
                successor.right.parent = successor;
            }

            // setting the successor in place of the element we want to delete
            transplant(n, successor);

            // now we set the left child of the successor to the left child of the element we want to delete
            successor.left = n.left;
            successor.left.parent = successor;
        }
        // n only has a left child
        else if (n.left != null)
        {
            transplant(n, n.left);
        }
        // n only has a right child
        else if (n.right != null)
        {
            transplant(n, n.right);
        }
        // n doesn't have any childs
        else
        {
            transplant(n, null);
        }

        size--;
    }

    /**
     * Removes the Node u from the tree and sets the Node v where u was before
     * @param u Node to remove
     * @param v Node to move "up"
     */
    private void transplant(Node u, Node v)
    {

        if (u.parent == null)
        {
            root = v;
        }
        else if (u == u.parent.left)
        {
            u.parent.left = v;
        }
        else
        {
            u.parent.right = v;
        }

        if (v != null)
        {
            v.parent = u.parent;
        }
    }

    /**
     * Gets the successor of the specified node
     * The successor is the Element with the next bigger value in the whole tree
     * @param n the node to get the successor of
     * @return the value of the successor
     */
    public T getSuccessor(Node n)
    {
        // if n has a right child, the successor is the minimum of the left subtree
        if (n.right != null)
        {
            return getMin(n.right);
        }

        Node x = n.parent;

        // get "up" as long as the current child not is not a left child
        // when the child is a left child, the parent is the searched successor
        while( x != null && n == x.right)
        {
            n = x;
            x = x.parent;
        }

        return x.value;
    }

    /**
     * Gets the predecessor of the specified node
     * The predecessor is the Element with the next lower value in the whole tree
     * @param n the node to get the predecessor of
     * @return the value of the predecessor
     */
    public T getPredecessor(Node n)
    {
        // if n has a left child, the predecessor is the maximum of the left subtree
        if (n.left != null)
        {
            return getMax(n.left);
        }

        Node x = n.parent;

        // get "up" as long as the current child not is not a right child
        // when the child is a right child, the parent is the searched predecessor
        while (x != null && n == x.left)
        {
            n = x;
            x = x.parent;
        }

        return x.value;
    }

    /**
     * gets the minimal value in this tree
     * @return the minimal value of the tree
     */
    public T getMin()
    {
        return getMin(root);
    }

    /**
     * gets the minimal value in this tree
     * @param n the starting node of the subtree to look in
     * @return the minimal value of the tree
     */
    private T getMin(Node n)
    {
        if (n.left != null)
        {
            return getMin(n.left);
        }

        return n.value;
    }

    /**
     * gets the maximum value in this tree
     * @return the maximum value of the tree
     */
    public T getMax()
    {
        return getMax(root);
    }

    /**
     * gets the maximum value in this tree
     * @param n the starting node of the subtree to look in
     * @return the maximum value of the tree
     */
    private T getMax(Node n)
    {
        if (n.right != null)
        {
            return getMax(n.right);
        }

        return n.value;
    }

    public void printPreOrderList()
    {
        printPreOrderList(root);
    }

    private void printPreOrderList(Node n)
    {
        if (n != null)
        {
            System.out.println(n.value);
            printPreOrderList(n.left);
            printPreOrderList(n.right);
        }
    }

    public void printInOrderList()
    {
        printInOrderList(root);
    }

    private void printInOrderList(Node n)
    {
        if (n != null)
        {
            printInOrderList(n.left);
            System.out.println(n.value);
            printInOrderList(n.right);
        }
    }

    public void printPostOrderList()
    {
        printPostOrderList(root);
    }

    private void printPostOrderList(Node n)
    {
        if (n != null)
        {
            printPostOrderList(n.left);
            printPostOrderList(n.right);
            System.out.println(n.value);
        }
    }

    /**
     * Gets the size of this tree
     * @return the amount of elements in this tree
     */
    public int size()
    {
        return this.size;
    }
}
