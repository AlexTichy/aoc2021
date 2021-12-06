package day06;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static Utils.Utils.readFile;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Day06 {

    static HashMap<Tuple, Long> seen_children = new HashMap<>();

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day06\\input06.txt";

        long first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        long second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static long first(String input_file) {
        long total_children = 0;
        for(String f:readFile(input_file).get(0).split(",")){
            total_children += children(80, Integer.parseInt(f));
        }
        return total_children;
    }

    static long second(String input_file) {
        long total_children = 0;
        for(String f:readFile(input_file).get(0).split(",")){
            total_children += children(256, Integer.parseInt(f));
        }
        return total_children;
    }

    private static long children(int remaining_days, int time_to_reproduction){
        if(remaining_days == 0){
                return 1;
        }else{
            Tuple children_key = new Tuple(remaining_days, time_to_reproduction);
            if(!seen_children.containsKey(children_key)){
                long val;
                if(time_to_reproduction == 0){
                    val =  children(remaining_days-1, 6) + children(remaining_days -1, 8);
                }else{
                    val =  children(max(remaining_days - time_to_reproduction, 0), 0);
                }
                seen_children.put(children_key, val);
            }
            return seen_children.get(children_key);

        }
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    static
    class Tuple{
        final int remaining_days;
        final int time_to_reproduction;
    }

}
