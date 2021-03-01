package lab01.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

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
    public void testAdd() {
        final int addedElement = 1;
        this.list.add(addedElement);
        assertOptionalValueEquals(addedElement, this.list.next());
    }

    @Test
    public void testCircularNext() {
        final int tries = 5;
        final int addedElement = 1;
        this.list.add(addedElement);
        IntStream.range(0, tries).forEach(i -> assertOptionalValueEquals(addedElement, this.list.next()));
    }

    @Test
    public void testNextOnEmptyListReturnsEmptyOptional() {
        assertTrue(this.list.next().isEmpty());
    }

    private static <T> void assertOptionalValueEquals(T expected, Optional<T> actual) {
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

}
