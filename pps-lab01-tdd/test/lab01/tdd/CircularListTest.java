package lab01.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
