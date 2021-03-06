package lab01.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {
    private final static int TEST_LIST_SIZE = 100;
    private final static List<Integer> TEST_LIST = IntStream.range(0, TEST_LIST_SIZE).boxed()
                                                            .collect(Collectors.toUnmodifiableList());

    private StrategyFactory strategyFactory;

    @BeforeEach
    public void beforeEach() {
        this.strategyFactory = new StrategyFactoryImpl();
    }

    @Test
    public void testEvenStrategy() {
        testStrategyBehaviour(n -> n % 2 == 0, strategyFactory.evenStrategy());
    }

    @Test
    public void testOddStrategy() {
        testStrategyBehaviour(n -> n % 2 != 0, strategyFactory.oddStrategy());
    }

    @Test
    public void testMultipleOfStrategy() {
        final int testedMultiple = 3;
        testStrategyBehaviour(n -> n % testedMultiple == 0, strategyFactory.multipleOfStrategy(testedMultiple));
    }

    @Test
    public void testEqualStrategy() {
        final int testedEqual = TEST_LIST.get(0);
        testStrategyBehaviour(n -> n == testedEqual, strategyFactory.equalStrategy(testedEqual));
    }

    @Test
    public void testAllMatchingStrategy() {
        testStrategyBehaviour(n -> true, strategyFactory.allMatchingStrategy());
    }

    @Test
    public void testNoneMatchingStrategy() {
        testStrategyBehaviour(n -> false, strategyFactory.noneMatchingStrategy());
    }

    private void testStrategyBehaviour(Predicate<Integer> expectedBehaviour, SelectStrategy actualStrategy) {
        final Map<Boolean, List<Integer>> expectedSplits = splitListWithPredicate(TEST_LIST, expectedBehaviour);
        final Map<Boolean, List<Integer>> actualSplits = splitListWithPredicate(TEST_LIST, actualStrategy::apply);
        assertEquals(expectedSplits.get(true), actualSplits.get(true));
        assertEquals(expectedSplits.get(false), expectedSplits.get(false));
    }

    private <T> Map<Boolean, List<T>> splitListWithPredicate(final List<T> list, final Predicate<T> splittingPredicate) {
        return list.stream().collect(Collectors.partitioningBy(splittingPredicate));
    }
}
