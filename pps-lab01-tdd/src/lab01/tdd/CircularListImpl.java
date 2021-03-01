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
        advanceCurrentPosition();
        return next;
    }

    @Override
    public Optional<Integer> previous() {
        final Optional<Integer> previous = getCurrent();
        decreaseCurrentPosition();
        return previous;
    }

    private void advanceCurrentPosition() {
        tryUpdatingPositionOrReset(position -> position < this.size() - 1, () -> this.currentPosition++);
    }

    private void decreaseCurrentPosition() {
        tryUpdatingPositionOrReset(position -> position > 0, () -> this.currentPosition--);
    }

    private void tryUpdatingPositionOrReset(Predicate<Integer> updatingCondition, Runnable updateOperation) {
        if (updatingCondition.test(this.currentPosition)) {
            updateOperation.run();
        } else {
            this.reset();
        }
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
