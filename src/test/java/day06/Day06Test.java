package day06;

import day05.Day05;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

    @Test
    void first() {
        long result = Day06.first("src\\test\\java\\day06\\input06test.txt");
        assertEquals(5934L, result);
    }

    @Test
    void second() {
        long result = Day06.second("src\\test\\java\\day06\\input06test.txt");
        assertEquals(26984457539L, result);
    }
}