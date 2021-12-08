package day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

    @Test
    void second() {
        String input_file = "D:\\aoc2021\\src\\test\\java\\day08\\input08test.txt";

        int solution = Day08.second(input_file);
        System.out.println("Second solution: " + solution);
        assertEquals(61229, solution);
    }

    @Test
    void containsAllCharsOf() {
        assertTrue(Day08.containsAllCharsOf("abc", "ab"));
        assertFalse(Day08.containsAllCharsOf("ab", "abc"));
    }

}