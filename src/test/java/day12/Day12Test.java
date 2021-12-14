package day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void first() {
        int solution = Day12.first("day12\\input12test.txt");
        assertEquals(10, solution);
    }

    @Test
    void second() {
        int solution = Day12.second("day12\\input12test.txt");
        assertEquals(36, solution);
    }

    @Test
    void valid_connection_second() {
        assertTrue(Day12.valid_connection_second("a", Arrays.asList("a", "b", "c")));
        assertTrue(Day12.valid_connection_second("d", Arrays.asList("a", "b", "c")));
        assertTrue(Day12.valid_connection_second("A", Arrays.asList("A", "b", "c")));
        assertFalse(Day12.valid_connection_second("a", Arrays.asList("a", "b", "b")));
        assertFalse(Day12.valid_connection_second("a", Arrays.asList("a", "a", "c")));
    }
}