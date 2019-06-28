package eis.chapter5.contracts;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

/** Unit tests for bounded sets.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class BoundedSetTests {

    // Test fixture
    private BoundedSet<Integer> set;

    @Before
    public void setUp() {
        set = new BoundedSet<>(3);
    }

    
    @Test
    public void testSingleElement() {
        set.add(1);
        assertTrue(set.contains(1));
    }

    @Test
    public void testRepeatedElement() {
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(1);
        assertTrue(set.contains(1));
    }
    
    @Test
    public void testOverflowKeepsSecond() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertTrue(set.contains(2));
    }

    @Test
    public void testOverflowRemovesOldest() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertFalse(set.contains(1));
    }

    @Test
    public void testOverflowKeepsNewest() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertTrue(set.contains(4));
    }

    @Test
    public void testRenewal() {
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(3);
        set.add(4);
        assertTrue(set.contains(1));
    }
}
