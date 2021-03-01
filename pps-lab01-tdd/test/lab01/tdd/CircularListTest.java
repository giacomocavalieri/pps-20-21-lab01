package lab01.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private CircularList list;

    @BeforeEach
    public void beforeEach() {
        this.list = new CircularListImpl();
    }
}
