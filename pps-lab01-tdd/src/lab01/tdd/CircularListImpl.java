package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
        increaseCurrentPosition();
        return next;
    }

    @Override
    public Optional<Integer> previous() {
        final Optional<Integer> previous = getCurrent();
        decreaseCurrentPosition();
        return previous;
    }

    private void increaseCurrentPosition() {
        tryUpdatingPosition(this::canIncrease, this.currentPosition + 1, 0);
    }

    private boolean canIncrease(final int position) {
        return position < this.size() - 1;
    }

    private void decreaseCurrentPosition() {
        tryUpdatingPosition(this::canDecrease, this.currentPosition - 1, this.size() - 1);
    }

    private boolean canDecrease(final int position) {
        return position > 0;
    }

    private void tryUpdatingPosition(Predicate<Integer> updatingCondition, int newValue, int fallbackValue) {
        this.currentPosition = updatingCondition.test(this.currentPosition) ? newValue : fallbackValue;
    }

    @Override
    public void reset() {
        this.currentPosition = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
