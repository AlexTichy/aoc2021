package day14;

import day12.Day12;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @Test
    void first() {
        int solution = Day14.first("src\\test\\java\\day14\\input14test.txt");
        assertEquals(1588, solution);
    }

    @Test
    void second() {
        long solution = Day14.second("src\\test\\java\\day14\\input14test.txt");
        assertEquals(2188189693529L, solution);
    }
}