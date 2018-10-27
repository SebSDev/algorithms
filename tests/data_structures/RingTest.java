package data_structures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Sebastian Schäffler
 * created at 27.10.2018
 * description:
 */
public class RingTest
{
    Ring r1 = new Ring();

    @Before
    public void init()
    {
        r1.append(1);
        r1.append(2);
        r1.append(3);
        r1.append(4);
        r1.append(5);
        r1.append(6);
        r1.append(7);
        r1.append(8);
    }

    @Test
    public void testSplit()
    {
        Ring r2 = r1.split(r1.getItem(3), r1.getItem(6));

        assertEquals(4, r1.getSize());
        assertEquals(4, r2.getSize());

        assertEquals(1, r1.getFirst().value);
        assertEquals(3, r2.getFirst().value);
    }
}
