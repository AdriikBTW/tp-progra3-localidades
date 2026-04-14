package prog3.tp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
    private int _number;

    @Before
    public void setUp() {
        _number = 10;
    }

    @Test
    public void testMain() {
        assertEquals(10, _number);
    }
}
