package lab01.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    public void testAdd() {
        final int addedElement = 1;
        this.list.add(addedElement);
        assertOptionalValueEquals(addedElement, this.list.next());
    }

    @Test
    public void testSize() {
        final List<Integer> elements = List.of(1, 2, 3);
        elements.forEach(element -> this.list.add(element));
        assertEquals(elements.size(), this.list.size());
    }

    @Test
    public void testSizeOfEmptyListIsZero() {
        assertEquals(0, this.list.size());
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void testListWithElementsIsNotEmpty() {
        final List<Integer> elements = List.of(1, 2, 3);
        elements.forEach(element -> this.list.add(element));
        assertFalse(this.list.isEmpty());
    }

    @Test
    public void testRepeatedNext() {
        final List<Integer> elements = List.of(1, 2, 3);
        testRepeatedAction(elements, elements, CircularList::next);
    }

    @Test
    public void testRepeatedPrevious() {
        final List<Integer> elements = List.of(1, 2, 3);
        final List<Integer> expectedElements = List.of(1, 3, 2);
        testRepeatedAction(elements, expectedElements, CircularList::previous);
    }

    private void testRepeatedAction(final List<Integer> elements, final List<Integer> expectedElements,
                                    final Function<CircularList, Optional<Integer>> operation) {
        final int repetitions = 5;
        elements.forEach(element -> this.list.add(element));
        Collections.nCopies(repetitions, expectedElements).stream()
                .flatMap(List::stream)
                .forEach(element -> assertOptionalValueEquals(element, operation.apply(this.list)));
    }

    @Test
    public void testNextOnEmptyListReturnsEmptyOptional() {
        assertTrue(this.list.next().isEmpty());
    }

    @Test
    public void testPreviousOnEmptyListReturnsEmptyOptional() {
        assertTrue(this.list.previous().isEmpty());
    }



    private static <T> void assertOptionalValueEquals(T expected, Optional<T> actual) {
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

}
