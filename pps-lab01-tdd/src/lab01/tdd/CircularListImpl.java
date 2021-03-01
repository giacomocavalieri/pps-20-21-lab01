package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Integer> next() {
        final Optional<Integer> next = this.isEmpty() ? Optional.empty() : Optional.of(this.list.get(currentPosition));
        advanceCurrentPosition();
        return next;
    }

    private void advanceCurrentPosition() {
        this.currentPosition = this.currentPosition < this.size() - 1 ? this.currentPosition + 1 : 0;
    }

    @Override
    public Optional<Integer> previous() {
        final Optional<Integer> previous = this.isEmpty() ? Optional.empty() : Optional.of(this.list.get(currentPosition));
        decreaseCurrentPosition();
        return previous;
    }

    private void decreaseCurrentPosition() {
        this.currentPosition = this.currentPosition > 0 ? this.currentPosition - 1 : this.size() - 1;
    }

    @Override
    public void reset() {

    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
