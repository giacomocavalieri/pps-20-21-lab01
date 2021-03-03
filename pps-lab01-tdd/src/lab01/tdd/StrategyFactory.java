package lab01.tdd;

public interface StrategyFactory {
    /**
     * @return a {@link SelectStrategy} that allows to selects even numbers.
     */
    SelectStrategy evenStrategy();

    /**
     * @return a {@link SelectStrategy} that allows to selects odd numbers.
     */
    SelectStrategy oddStrategy();

    /**
     * @param n the number whose multiples will be selected.
     * @return a {@link SelectStrategy} that allows to selects multiples of n.
     */
    SelectStrategy multipleOfStrategy(int n);

    /**
     * @param n the number used to select others equals to it.
     * @return a {@link SelectStrategy} that allows to selects numbers equal to n.
     */
    SelectStrategy equalStrategy(int n);

    /**
     * @return a {@link SelectStrategy} that matches with any number.
     */
    SelectStrategy allMatchingStrategy();

    /**
     * @return a {@link SelectStrategy} that does not match with any number.
     */
    SelectStrategy noneMatchingStrategy();
}
