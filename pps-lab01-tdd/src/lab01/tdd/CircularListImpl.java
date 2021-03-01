package lab01.tdd;

import java.util.*;
import java.util.stream.IntStream;

public class CircularListImpl implements CircularList {
    private final List<Integer> list = new ArrayList<>();
    private int currentPosition = 0;

    @Override
    public void add(int element) {
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

    private Optional<Integer> getCurrent() {
        return this.isEmpty() ? Optional.empty() : Optional.of(this.list.get(currentPosition));
    }

    private Optional<Integer> getCurrentAndUpdate(final int newValue) {
        final Optional<Integer> current = getCurrent();
        this.currentPosition = newValue;
        return current;
    }

    @Override
    public Optional<Integer> next() {
        return getCurrentAndUpdate(getFollowingIndex(this.currentPosition));
    }

    private int getFollowingIndex(final int position) {
        return position >= this.size() - 1 ? 0 : position + 1;
    }

    @Override
    public Optional<Integer> previous() {
        return getCurrentAndUpdate(getPreviousIndex(this.currentPosition));
    }

    private int getPreviousIndex(final int position) {
        return position <= 0 ? this.size() - 1 : position - 1;
    }

    @Override
    public void reset() {
        this.currentPosition = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        Optional<Integer> foundIndex = IntStream.concat(IntStream.range(this.currentPosition, this.size()),
                                                        IntStream.range(0, this.currentPosition))
                                                .filter(index -> strategy.apply(this.list.get(index)))
                                                .boxed()
                                                .findFirst();
        foundIndex.ifPresent(index -> this.currentPosition = getFollowingIndex(index));
        return foundIndex.map(this.list::get);
    }
}
