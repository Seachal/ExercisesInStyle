package eis.chapter6;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
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
        Integer result = set.add(1);
        assertThat("Wrong return value", result, is(nullValue()));
        assertThat("Wrong set content", set.content(), contains(1));
    }
    
    @Test
    public void testAddAbsentOnNonFull() {
        set.add(1);
        Integer result = set.add(2);  // line under test
        assertThat("Wrong return value", result, is(nullValue()));
        assertThat("Wrong set content", set.content(), contains(1, 2));        
    }

    @Test
    public void testAddAbsentOnFull() {
        set.add(1);
        set.add(2);
        set.add(3);
        Integer result = set.add(4); // line under test
        assertThat("Wrong return value", result, is(1));
        assertThat("Wrong set content", set.content(), contains(2, 3, 4));        
    }
    
    @Test
    public void testAddPresentOnNonFull() {
        set.add(2);
        set.add(1);
        Integer result = set.add(2); // line under test
        assertThat("Wrong return value", result, is(nullValue()));
        assertThat("Wrong set content", set.content(), contains(1, 2));        
    }

    @Test
    public void testAddPresentOnFull() {
        set.add(1);
        set.add(2);
        set.add(3);
        Integer result = set.add(2); // line under test
        assertThat("Wrong return value", result, is(nullValue()));       
        assertThat("Wrong set content", set.content(), contains(1, 3, 2));        
    }
}
