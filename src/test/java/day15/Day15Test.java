package day15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void firstA() {
        List<String> input = Arrays.asList("111", "231", "333");
        int res = Day15.first(input);
        assertEquals(6, res);
    }

    @Test
    void firstB() {
        List<String> input = Utils.Utils.readFile("src\\test\\java\\day15\\input15test.txt");
        int res = Day15.first(input);
        assertEquals(40, res);
    }

    @Test
    void secondA() {
        List<String> input = Arrays.asList("8");
        int res = Day15.second(input);
        assertEquals(37, res);
    }

    @Test
    void second() {
        List<String> input = Utils.Utils.readFile("src\\test\\java\\day15\\input15test.txt");
        int res = Day15.second(input);
        assertEquals(315, res);
    }
}