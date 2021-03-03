package lab01.tdd;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {
    private final static StrategyFactory STRATEGY_FACTORY = new StrategyFactoryImpl();
    private final static int LENGTH_OF_LIST = 30;
    private final static List<Integer> TEST_LIST = IntStream.range(0, LENGTH_OF_LIST).boxed().collect(Collectors.toList());
    private final static List<Integer> EVEN_LIST = TEST_LIST.stream().filter(value -> value % 2 == 0).collect(Collectors.toList());
    private final static List<Integer> ODD_LIST = TEST_LIST.stream().filter(value -> value % 2 != 0).collect(Collectors.toList());

    @Test
    public void testEvenStrategy() {
        final SelectStrategy evenStrategy = STRATEGY_FACTORY.evenStrategy();
        testStrategyMatches(evenStrategy, EVEN_LIST);
        testStrategyDoesNotMatch(evenStrategy, ODD_LIST);
    }

    @Test
    public void testOddStrategy() {
        final SelectStrategy oddStrategy = STRATEGY_FACTORY.oddStrategy();
        testStrategyDoesNotMatch(oddStrategy, EVEN_LIST);
        testStrategyMatches(oddStrategy, ODD_LIST);
    }

    @Test
    public void testMultipleOfStrategy() {
        final int multiple = 3;
        final SelectStrategy multipleOfStrategy = STRATEGY_FACTORY.multipleOfStrategy(multiple);
        final List<Integer> multiples = TEST_LIST.stream().filter(value -> value % multiple == 0)
                                                          .collect(Collectors.toList());
        final List<Integer> nonMultiples = TEST_LIST.stream().filter(value -> !multiples.contains(value))
                                                             .collect(Collectors.toList());
        testStrategyMatches(multipleOfStrategy, multiples);
        testStrategyDoesNotMatch(multipleOfStrategy, nonMultiples);
    }

    @Test
    public void testEqualStrategy() {
        final int expectedMatch = TEST_LIST.get(0);
        final SelectStrategy equalStrategy = STRATEGY_FACTORY.equalStrategy(expectedMatch);
        testStrategyMatches(equalStrategy, List.of(expectedMatch));
        testStrategyDoesNotMatch(equalStrategy, TEST_LIST.stream().skip(1).collect(Collectors.toList()));
    }

    @Test
    public void testAllMatchingStrategy() {
        final SelectStrategy allMatchingStrategy = STRATEGY_FACTORY.allMatchingStrategy();
        testStrategyMatches(allMatchingStrategy, TEST_LIST);
    }

    @Test
    public void testNoneMatchingStrategy() {
        final SelectStrategy noneMatchingStrategy = STRATEGY_FACTORY.noneMatchingStrategy();
        testStrategyDoesNotMatch(noneMatchingStrategy, TEST_LIST);
    }

    private void testStrategyMatches(SelectStrategy strategy, Collection<Integer> matchingValues) {
        assertTrue(matchingValues.stream().allMatch(strategy::apply));
    }

    private void testStrategyDoesNotMatch(SelectStrategy strategy, Collection<Integer> nonMatchingValues) {
        assertTrue(nonMatchingValues.stream().noneMatch(strategy::apply));
    }
}
