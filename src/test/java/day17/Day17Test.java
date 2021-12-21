package day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    @Test
    void second() {
        int result = Day17.second(20, 30, -10, -5);
        assertEquals(result, 112);
    }
}