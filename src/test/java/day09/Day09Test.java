package day09;

import day08.Day08;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    @Test
    void first() {
        int solution = Day09.first("D:\\aoc2021\\src\\test\\java\\day09\\input09test.txt");
        assertEquals(15, solution);
    }

    @Test
    void second() {
        int solution = Day09.second("D:\\aoc2021\\src\\test\\java\\day09\\input09test.txt");
        assertEquals(1134, solution);
    }
}