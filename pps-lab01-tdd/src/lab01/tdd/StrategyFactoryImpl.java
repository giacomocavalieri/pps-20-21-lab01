package lab01.tdd;

public class StrategyFactoryImpl implements StrategyFactory {
    @Override
    public SelectStrategy evenStrategy() {
        return this.multipleOfStrategy(2);
    }

    @Override
    public SelectStrategy oddStrategy() {
        return this.inverseStrategy(this.evenStrategy());
    }

    private SelectStrategy inverseStrategy(final SelectStrategy strategy) {
        return element -> !strategy.apply(element);
    }

    @Override
    public SelectStrategy multipleOfStrategy(int n) {
        return element -> element % n == 0;
    }

    @Override
    public SelectStrategy equalStrategy(int n) {
        return element -> element == n;
    }

    @Override
    public SelectStrategy allMatchingStrategy() {
        return element -> true;
    }

    @Override
    public SelectStrategy noneMatchingStrategy() {
        return this.inverseStrategy(allMatchingStrategy());
    }
}
