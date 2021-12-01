package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day01 {

    public static void main(String[] args) throws IOException {

        second();
    }

    private static void first() throws IOException {
        List<Integer> measurements = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\aoc2021\\src\\day01\\input01.txt"))) {
            String current_line;
            while ((current_line = br.readLine()) != null) {
                measurements.add(Integer.valueOf(current_line));
            }
        }
        System.out.println(count_increases(measurements));
    }

    private static void second() throws IOException {

        List<Integer> measurements = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\aoc2021\\src\\day01\\input01.txt"))) {

            List<Integer> window = new ArrayList<>();
            window.add(Integer.valueOf(br.readLine()));
            window.add(Integer.valueOf(br.readLine()));

            String current_last_line;
            while ((current_last_line = br.readLine()) != null) {
                window.add(Integer.valueOf(current_last_line));
                //System.out.println(String.format("%d, %d, %d",window.get(0), window.get(1), window.get(2)));
                measurements.add(window.stream().reduce(0, Integer::sum));
                //System.out.println(String.format("-> %d",measurements.get(measurements.size()-1)));
                window.remove(0);
            }
        }
        System.out.println(count_increases(measurements));
    }

    private static int count_increases(List<Integer> values){

        int count = 0;
        int last = values.get(0);
        for (int current: values){
            if(current > last){
                count ++;
            }
            last = current;
        }
        return count;
    }
}
