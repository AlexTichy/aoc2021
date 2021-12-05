package day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @Test
    void first() {
        String test_input_file = "D:\\aoc2021\\src\\test\\java\\day05\\input05test.txt";
        int result = Day05.first(test_input_file);
        assertEquals(5, result);
    }

    @Test
    void second() {
        String test_input_file = "D:\\aoc2021\\src\\test\\java\\day05\\input05test.txt";
        int result = Day05.second(test_input_file);
        assertEquals(12, result);
    }

    @Test
    void pointsBetween(){
        Day05.VentPoint ventPoint1 = new Day05.VentPoint(0, 12);
        Day05.VentPoint ventPoint2 = new Day05.VentPoint(2, 10);

        assertEquals(ventPoint1.pointsBetween(ventPoint2), Arrays.asList(
                new Day05.VentPoint(0, 12),
                new Day05.VentPoint(1, 11),
                new Day05.VentPoint(2, 10)));

    }

}