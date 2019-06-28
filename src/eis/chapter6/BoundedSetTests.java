package eis.chapter6;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

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

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        set.add(null);
    }

    @Test
    public void testAddOnEmpty() {
        set.add(1);
        assertEquals(set.content(), List.of(1));
    }
    
    @Test
    public void testAddAbsentOnNonFull() {
        set.add(1);
        set.add(2);  // line under test
        assertEquals(set.content(), List.of(1,2));
    }

    @Test
    public void testAddAbsentOnFull() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4); // line under test
        assertEquals(set.content(), List.of(2,3,4));
    }
    
    @Test
    public void testAddPresentOnNonFull() {
        set.add(2);
        set.add(1);
        set.add(2); // line under test
        assertEquals(set.content(), List.of(1,2));
    }

    @Test
    public void testAddPresentOnFull() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(2); // line under test
        assertEquals(set.content(), List.of(1,3,2));
    }
}
