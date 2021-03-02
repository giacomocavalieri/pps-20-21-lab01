package lab01.tdd;

import java.util.*;
import java.util.stream.IntStream;

public class CircularListImpl implements CircularList {
    private final List<Integer> list = new ArrayList<>();
    private int currentPosition = 0;

    @Override
    public void add(final int element) {
        this.list.add(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        return next(element -> true);
    }

    @Override
    public Optional<Integer> next(final SelectStrategy strategy) {
        Optional<Integer> foundIndex = firstMatchingIndexFromCurrentPosition(strategy);
        foundIndex.ifPresent(index -> setCurrentPosition(getFollowingPosition(index)));
        return foundIndex.map(this.list::get);
    }

    private Optional<Integer> firstMatchingIndexFromCurrentPosition(final SelectStrategy strategy) {
        return IntStream.concat(IntStream.range(this.currentPosition, this.size()),
                                IntStream.range(0, this.currentPosition))
                        .filter(index -> strategy.apply(this.list.get(index)))
                        .boxed()
                        .findFirst();
    }

    private void setCurrentPosition(final int newPosition) {
        this.currentPosition = newPosition;
    }

    private int getFollowingPosition(final int position) {
        return position >= this.size() - 1 ? 0 : position + 1;
    }

    @Override
    public Optional<Integer> previous() {
        final Optional<Integer> current = getCurrentIfPresent();
        current.ifPresent(element -> setCurrentPosition(getPreviousPosition(this.currentPosition)));
        return current;
    }

    public Optional<Integer> getCurrentIfPresent() {
        return this.isEmpty() ? Optional.empty() : Optional.of(this.list.get(this.currentPosition));
    }

    private int getPreviousPosition(final int position) {
        return position <= 0 ? this.size() - 1 : position - 1;
    }

    @Override
    public void reset() {
        this.currentPosition = 0;
    }
}
