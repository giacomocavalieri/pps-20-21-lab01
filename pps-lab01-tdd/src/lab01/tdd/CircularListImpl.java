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
        final Optional<Integer> current = getCurrent();
        updatePosition(getFollowingPosition(this.currentPosition));
        return current;
    }

    @Override
    public Optional<Integer> previous() {
        final Optional<Integer> current = getCurrent();
        updatePosition(getPreviousPosition(this.currentPosition));
        return current;
    }

    private Optional<Integer> getCurrent() {
        return this.isEmpty() ? Optional.empty() : Optional.of(this.list.get(currentPosition));
    }

    private void updatePosition(final int newPosition) {
        if (!this.isEmpty()) {
            this.currentPosition = newPosition;
        }
    }

    private int getFollowingPosition(final int position) {
        return position >= this.size() - 1 ? 0 : position + 1;
    }

    private int getPreviousPosition(final int position) {
        return position <= 0 ? this.size() - 1 : position - 1;
    }

    @Override
    public void reset() {
        this.currentPosition = 0;
    }

    @Override
    public Optional<Integer> next(final SelectStrategy strategy) {
        Optional<Integer> foundIndex = IntStream.concat(IntStream.range(this.currentPosition, this.size()),
                                                        IntStream.range(0, this.currentPosition))
                                                .filter(index -> strategy.apply(this.list.get(index)))
                                                .boxed()
                                                .findFirst();
        foundIndex.ifPresent(index -> updatePosition(getFollowingPosition(index)));
        return foundIndex.map(this.list::get);
    }
}
