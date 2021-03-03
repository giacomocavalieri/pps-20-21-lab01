package lab01.tdd;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {
    private final static StrategyFactory STRATEGY_FACTORY = new StrategyFactoryImpl();
    private final static int TEST_LIST_SIZE = 100;
    private final static List<Integer> TEST_LIST = IntStream.range(0, TEST_LIST_SIZE).boxed().collect(Collectors.toList());

    @TestFactory
    public List<DynamicTest> testStrategies() {
        final int testedMultiple = 3;
        final int testedEqual = TEST_LIST.get(0);

        return List.of(
            testExpectedBehaviour("testEvenStrategy", n -> n % 2 == 0, STRATEGY_FACTORY.evenStrategy()),
            testExpectedBehaviour("testOddStrategy", n -> n % 2 != 0, STRATEGY_FACTORY.oddStrategy()),
            testExpectedBehaviour("testMultipleOfStrategy", n -> n % testedMultiple == 0, STRATEGY_FACTORY.multipleOfStrategy(testedMultiple)),
            testExpectedBehaviour("testEqualStrategy", n -> n == testedEqual, STRATEGY_FACTORY.equalStrategy(testedEqual)),
            testExpectedBehaviour("testAllMatchingStrategy", n -> true, STRATEGY_FACTORY.allMatchingStrategy()),
            testExpectedBehaviour("testNoneMatchingStrategy", n -> false, STRATEGY_FACTORY.noneMatchingStrategy())
        );
    }

    private DynamicTest testExpectedBehaviour(final String testName, final Predicate<Integer> expectedBehaviour,
                                              final SelectStrategy testedStrategy) {
        return DynamicTest.dynamicTest(testName, createTestRunnable(expectedBehaviour, testedStrategy));
    }

    private Executable createTestRunnable(final Predicate<Integer> expectedBehaviour, final SelectStrategy testedStrategy) {
        return () -> {
            final Map<Boolean, List<Integer>> correctSplits = splitListWithPredicate(TEST_LIST, expectedBehaviour);
            final Map<Boolean, List<Integer>> actualSplits = splitListWithPredicate(TEST_LIST, testedStrategy::apply);
            assertEquals(correctSplits.get(true), actualSplits.get(true));
            assertEquals(correctSplits.get(false), correctSplits.get(false));
        };
    }

    private <T> Map<Boolean, List<T>> splitListWithPredicate(final List<T> list, final Predicate<T> splittingPredicate) {
        return list.stream().collect(Collectors.partitioningBy(splittingPredicate));
    }
}
