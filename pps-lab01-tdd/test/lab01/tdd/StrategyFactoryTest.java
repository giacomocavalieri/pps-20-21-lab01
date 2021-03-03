package lab01.tdd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {
    private final static StrategyFactory STRATEGY_FACTORY = new StrategyFactoryImpl();
    private final static int TEST_LIST_SIZE = 50;
    private final static List<Integer> TEST_LIST = IntStream.range(0, TEST_LIST_SIZE).boxed().collect(Collectors.toList());

    @ParameterizedTest(name = "test{0}")
    @MethodSource("provideStrategies")
    public void testStrategy(final String strategyName, final SelectStrategy strategy,
                             final List<Integer> matchingValues, final List<Integer> nonMatchingValues) {
        testStrategyMatches(strategy, matchingValues);
        testStrategyDoesNotMatch(strategy, nonMatchingValues);
    }

    private void testStrategyMatches(SelectStrategy strategy, Collection<Integer> matchingValues) {
        assertTrue(matchingValues.stream().allMatch(strategy::apply));
    }

    private void testStrategyDoesNotMatch(SelectStrategy strategy, Collection<Integer> nonMatchingValues) {
        assertTrue(nonMatchingValues.stream().noneMatch(strategy::apply));
    }

    private static Stream<Arguments> provideStrategies() {
        final int testedMultiple = 3;
        final int testedEqual = TEST_LIST.get(0);

        return Stream.of(
            createArgumentFromPredicate("EvenStrategy", STRATEGY_FACTORY.evenStrategy(), n -> n % 2 == 0),
            createArgumentFromPredicate("OddStrategy", STRATEGY_FACTORY.oddStrategy(), n -> n % 2 != 0),
            createArgumentFromPredicate("MultipleOfStrategy", STRATEGY_FACTORY.multipleOfStrategy(testedMultiple), n -> n % testedMultiple == 0),
            createArgumentFromPredicate("EqualStrategy", STRATEGY_FACTORY.equalStrategy(testedEqual), n -> n == testedEqual),
            createArgumentFromPredicate("AllMatchingStrategy", STRATEGY_FACTORY.allMatchingStrategy(), n -> true),
            createArgumentFromPredicate("NoneMatchingStrategy", STRATEGY_FACTORY.noneMatchingStrategy(), n -> false)
        );
    }

    private static Arguments createArgumentFromPredicate(final String testName, final SelectStrategy testedStrategy,
                                                         final Predicate<Integer> matchingPredicate) {
        final Map<Boolean, List<Integer>> listSplit = TEST_LIST.stream().collect(Collectors.partitioningBy(matchingPredicate));
        return Arguments.of(testName, testedStrategy, listSplit.get(true), listSplit.get(false));
    }

}
