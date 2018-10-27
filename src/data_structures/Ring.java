package data_structures;

/**
 * @author Sebastian Schäffler
 * created at 21.10.2018
 * description: A doubly linked ring list
 */
public class Ring
{
    // inner class: groups data value and prev/next "pointer"
    class Item
    {
        int value;
        Item prev;
        Item next;

        Item(int v)
        {
            value = v;
            prev = null;
            next = null;
        }
    }

    // "first" element of ring, anchor
    private Item first;

    /**
     * Creating an new empty Ring
     */
    public Ring()
    {
        first = null;
    }

    /**
     * Checking if the ring is empty
     * @return true if the Ring is empty, otherwise false
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * Check if the item is the "first" element of this Ring
     * @param item the item to check
     * @return true if the item is the "first" element, otherwise false
     */
    public boolean isFirst(Item item)
    {
        return item == first;
    }

    /**
     * Gets the first element
     * @return the first element of this Ring
     */
    public Item getFirst()
    {
        return first;
    }

    /**
     * Gets the succeeding element of the item in this Ring
     * @param item item to get the successor of
     * @return the succeeding element
     */
    public Item getNext(Item item)
    {
        return item.next;
    }

    /**
     * Gets the amount of items of this Ring
     * @return the amount of items of this Ring
     */
    public int getSize()
    {
        if (this.isEmpty()) return 0;

        Item i = first.next;
        int counter = 1;

        while (i != first)
        {
            counter++;

            i = i.next;
        }

        return counter;
    }

    /**
     * Gets the first occurring item with the value
     * @param val value to search for
     * @return the item with the value. If there is no Item with val -> null
     */
    public Item getItem(int val)
    {
        Item i = first;

        if (i != null)
        {
            do
            {
                if (i.value == val) return i;

                i = i.next;

            } while (i != first);
        }

        return null;
    }

    /**
     * Appending a new element with the value "val" to this Ring. It is appended before the "first" element.
     * @param val value of the element to append
     */
    public void append(int val)
    {
        Item i = new Item(val);

        if (this.isEmpty())
        {
            first = i;
            first.next = first;
            first.prev = first;
        }
        else
        {
            i.next = first;
            i.prev = first.prev;

            first.prev.next = i;
            first.prev = i;
        }


    }

    /**
     * Removes items from this Ring and creates a new Ring with the new elements
     * @param a starting index of the new Ring
     * @param b ending index of the new Ring
     * @return the new Ring
     */
    public Ring split(Item a, Item b)
    {
        Ring newRing = new Ring();

        if (b.next == a)
        {
            newRing.first = first;
            first = null;
        }
        else
        {
            // reconnect the old Ring
            b.next.prev = a.prev;
            a.prev.next = b.next;

            // connect the new Ring
            newRing.first = a;
            b.next = a;
            a.prev = b;
        }

        return newRing;
    }


    /**
     * Creates a String that represents this Ring
     * @return the created String
     */
    public String toString()
    {
        if (first == null)
        {
            return "";
        }
        else
        {
            String back = "";
            Item pos = first;
            do
            {
                back = back + pos.value;
                pos = pos.next;
                if (pos != first) back = back + " ";
            } while (pos != first);
            return back;
        }
    }

    // Testet die Implementierung
    public static void main(String args[])
    {
        // Erzeuge Ring mit den Elementen 1..8
        Ring r = new Ring();
        r.append(1);
        r.append(2);
        r.append(3);
        r.append(4);
        r.append(5);
        r.append(6);
        r.append(7);
        r.append(8);
        System.out.println("Erzeugter Ring:  r = " + r.toString());
        System.out.println("Erwartet:        r = 1 2 3 4 5 6 7 8\n");


        // Trenne den Teilring 3..6 vom Ring r ab und speichere in R2
        Item pos3 = r.getNext(r.getNext(r.getFirst()));
        Item pos6 = r.getNext(r.getNext(r.getNext(pos3)));
        Ring r2 = r.split(pos3, pos6);
        System.out.println("Teilring r2 (=" + r2.toString() + ") wird abgetrennt -> r = " + r.toString());
        System.out.println("Erwartet: r = 1 2 7 8\n");


    }


}
