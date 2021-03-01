package lab01.tdd;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Override
    public Optional<Integer> next() {
        final Optional<Integer> next = getCurrent();
        this.currentPosition = getFollowingIndex(this.currentPosition);
        return next;
    }

    @Override
    public Optional<Integer> previous() {
        final Optional<Integer> previous = getCurrent();
        this.currentPosition = getPreviousIndex(this.currentPosition);
        return previous;
    }

    private int getFollowingIndex(final int position) {
        return position >= this.size() - 1 ? 0 : position + 1;
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
                                                .boxed().findFirst();
        foundIndex.ifPresent(index -> this.currentPosition = getFollowingIndex(index));
        return foundIndex.map(this.list::get);
    }
}
