package lab01.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private CircularList list;

    @BeforeEach
    public void beforeEach() {
        this.list = new CircularListImpl();
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void testSimpleAdd() {
        final int addedElement = 1;
        this.list.add(addedElement);
        assertFalse(this.list.isEmpty());
        final Optional<Integer> next = this.list.next();
        assertTrue(next.isPresent());
        assertEquals(addedElement, next.get());
    }
}
