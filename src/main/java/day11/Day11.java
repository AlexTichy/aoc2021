package day11;

import java.awt.*;
import java.util.List;

import static Utils.Utils.parseIntGrid;
import static Utils.Utils.readFile;

public class Day11 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day11\\input11.txt";

        long first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static long first(String input_file) {
        List<String> input = readFile(input_file);
        int[][] energy_map = parseIntGrid(input);

        long flashes_total = 0;
        for(int step = 1; step <= 100; step ++){
            increment(energy_map);
            int flash_count;
            do{
                flash_count = flash(energy_map);
                flashes_total += flash_count;
            }while(flash_count > 0);
        }
        return flashes_total;
    }


    static int second(String input_file) {
        List<String> input = readFile(input_file);
        int[][] energy_map = parseIntGrid(input);
        int step = 1;
        for(; ; step ++){
            increment(energy_map);
            int flash_count_step = 0;
            int flash_count;
            do{
                flash_count = flash(energy_map);
                flash_count_step += flash_count;
            }while(flash_count > 0);

            if(flash_count_step == energy_map.length * energy_map[0].length){
                break;
            }
        }
        return step;
    }

    private static int flash(int[][] energy_map) {
        int flash_count = 0;
        for(int i = 0; i < energy_map.length; i++){
            for(int j = 0; j< energy_map[0].length; j++){
                if(energy_map[i][j] > 9){
                    flash_count ++;
                    energy_map[i][j] = 0;
                    increment_around(energy_map, i, j);
                }
            }
        }
        return flash_count;
    }

    private static void increment(int[][] energy_map) {
        for(int i = 0; i < energy_map.length; i++){
            for(int j = 0; j< energy_map[0].length; j++){
                energy_map[i][j] ++;
            }
        }
    }

    private static void increment_around(int[][] energy_map, int i, int j){
        int lower_i = Math.max(i-1, 0);
        int upper_i = Math.min(i+1, energy_map.length -1);
        int lower_j = Math.max(j-1, 0);
        int upper_j = Math.min(j+1, energy_map[0].length -1);
        for(int i_ = lower_i; i_ <=upper_i; i_++){
            for(int j_ = lower_j; j_ <=upper_j; j_++){
                if(energy_map[i_][j_] > 0){
                    energy_map[i_][j_] ++;
                }
            }
        }
    }

}
