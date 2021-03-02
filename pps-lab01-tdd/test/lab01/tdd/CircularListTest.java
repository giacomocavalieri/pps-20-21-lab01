package lab01.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private final static List<Integer> EXAMPLE_ELEMENTS = List.of(1, 2, 3);
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

    private static <T> void assertOptionalValueEquals(T expected, Optional<T> actual) {
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void testSize() {
        addAll(EXAMPLE_ELEMENTS);
        assertEquals(EXAMPLE_ELEMENTS.size(), this.list.size());
    }

    private void addAll(Collection<Integer> elements) {
        elements.forEach(element -> this.list.add(element));
    }

    @Test
    public void testEmptyListHasSizeZero() {
        assertEquals(0, this.list.size());
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void testListWithElementsIsNotEmpty() {
        addAll(EXAMPLE_ELEMENTS);
        assertFalse(this.list.isEmpty());
    }

    @Test
    public void testRepeatedNext() {
        addAll(EXAMPLE_ELEMENTS);
        testRepeatedAction(EXAMPLE_ELEMENTS, CircularList::next);
    }

    private void testRepeatedAction(final List<Integer> expectedElements, final Function<CircularList, Optional<Integer>> operation) {
        final int repetitions = 5;
        Collections.nCopies(repetitions, expectedElements).stream()
                .flatMap(List::stream)
                .forEach(element -> assertOptionalValueEquals(element, operation.apply(this.list)));
    }

    @Test
    public void testRepeatedPrevious() {
        final List<Integer> expectedElements = List.of(1, 3, 2);
        addAll(EXAMPLE_ELEMENTS);
        testRepeatedAction(expectedElements, CircularList::previous);
    }

    @Test
    public void testNextEmptyOnEmptyList() {
        assertTrue(this.list.next().isEmpty());
    }

    @Test
    public void testPreviousEmptyOnEmptyList() {
        assertTrue(this.list.previous().isEmpty());
    }

    @Test
    public void testResetOnEmptyList() {
        this.list.reset();
        assertTrue(this.list.next().isEmpty());
        assertTrue(this.list.previous().isEmpty());
    }

    @Test
    public void testReset() {
        addAll(EXAMPLE_ELEMENTS);
        this.list.next();
        this.list.next();
        this.list.previous();
        this.list.reset();
        assertOptionalValueEquals(EXAMPLE_ELEMENTS.get(0), this.list.next());
    }

    @Test
    public void testNextStrategyWithNoMatch() {
        addAll(EXAMPLE_ELEMENTS);
        assertTrue(this.list.next(elem -> false).isEmpty());
        assertOptionalValueEquals(EXAMPLE_ELEMENTS.get(0), this.list.next());
    }

    @Test
    public void testNextStrategyWithMatch() {
        final int expectedMatch = EXAMPLE_ELEMENTS.get(2);
        addAll(EXAMPLE_ELEMENTS);
        assertOptionalValueEquals(expectedMatch, this.list.next(elem -> elem == expectedMatch));
        assertOptionalValueEquals(EXAMPLE_ELEMENTS.get(0), this.list.next());
    }

    @Test
    public void testNextStrategyWithMultipleMatches() {
        final List<Integer> expectedElements = List.of(1, 3);
        addAll(EXAMPLE_ELEMENTS);
        testRepeatedAction(expectedElements, list -> list.next(elem -> elem % 2 != 0));
        assertOptionalValueEquals(EXAMPLE_ELEMENTS.get(0), this.list.next());
    }
}
