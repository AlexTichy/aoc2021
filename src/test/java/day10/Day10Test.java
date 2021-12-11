package day10;

import day09.Day09;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    String test_input_file = "D:\\aoc2021\\src\\test\\java\\day10\\input10test.txt";
    @Test
    void first() {
        int solution = Day10.first(test_input_file);
        assertEquals(26397, solution);
    }

    @Test
    void second() {
        long solution = Day10.second(test_input_file);
        assertEquals(288957, solution);
    }
}