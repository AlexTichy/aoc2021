package day11;

import day10.Day10;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    String test_input_file = "D:\\aoc2021\\src\\test\\java\\day11\\input11test.txt";
    @Test
    void first() {
        long solution = Day11.first(test_input_file);
        assertEquals(1656, solution);
    }

    @Test
    void second() {
        long solution = Day11.second(test_input_file);
        assertEquals(195, solution);
    }
}