package day07;

import Utils.Utils;

import java.util.Arrays;
import java.util.List;

import static Utils.Utils.readFile;

public class Day07 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day07\\input07.txt";

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        String[] input = readFile(input_file).get(0).split(",");
        int[] positions = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(positions);
        int median = positions[positions.length/2];
        int total_fuel = 0;
        for(int pos: positions){
            total_fuel += Math.abs(pos - median);
        }
        return total_fuel;
    }

    static int second(String input_file) {
        String[] input = readFile(input_file).get(0).split(",");
        int[] positions = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        int sum = 0;
        for(int pos: positions) {
            sum += pos;
        }
        int avg = sum/positions.length;
        return total_fuel_increasing(positions, avg);
    }

    private static int total_fuel_increasing(int[] positions, int align_at) {
        int total_fuel = 0;
        for (int pos : positions) {
            int dist = Math.abs(pos - align_at);
            total_fuel += dist * (dist + 1) /2;
        }
        return total_fuel;
    }

}
