package day05;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Utils.Utils.readFile;

public class Day05 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day05\\input05.txt";

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        List<String> input = readFile(input_file);
        HashMap<VentPoint, Integer> line_counts = new HashMap<>();

        for(String vent: input){
            String[] coord = vent.trim().split("\\s+->\\s+");
            VentPoint start = new VentPoint(coord[0]);
            VentPoint end = new VentPoint(coord[1]);
            if(start.x == end.x || start.y == end.y) {
                List<VentPoint> points_on_line = start.pointsBetween(end);
                for (VentPoint v : points_on_line) {
                    if (!line_counts.containsKey(v)) {
                        line_counts.put(v, 0);
                    }
                    line_counts.put(v, line_counts.get(v) + 1);
                }
            }
        }
        return (int) line_counts.values().stream().filter(value -> value > 1).count();
    }

    static int second(String input_file) {
        List<String> input = readFile(input_file);
        HashMap<VentPoint, Integer> line_counts = new HashMap<>();

        for(String vent: input){
            String[] coord = vent.trim().split("\\s+->\\s+");
            VentPoint start = new VentPoint(coord[0]);
            VentPoint end = new VentPoint(coord[1]);
            List<VentPoint> points_on_line = start.pointsBetween(end);
            for (VentPoint v : points_on_line) {
                if (!line_counts.containsKey(v)) {
                    line_counts.put(v, 0);
                }
                line_counts.put(v, line_counts.get(v) + 1);
            }
        }
        return (int) line_counts.values().stream().filter(value -> value > 1).count();
    }

    static class VentPoint extends Point{

        public VentPoint(String coordinates){
            String[] coord = coordinates.split("[(,)]");
            this.x = Integer.parseInt(coord[0]);
            this.y = Integer.parseInt(coord[1]);
        }

        public VentPoint(int x, int y){
            super(x, y);
        }

        public List<VentPoint> pointsBetween(VentPoint other){
            ArrayList<VentPoint> pointsBetween = new ArrayList<>();
            int[] x_range;
            int[] y_range;

            if(this.x == other.x){
                y_range = inclusiveRange(this.y, other.y);
                x_range = new int[y_range.length];
                Arrays.fill(x_range, this.x);
            }else if(this.y == other.y){
                x_range = inclusiveRange(this.x, other.x);
                y_range = new int[x_range.length];
                Arrays.fill(y_range, this.y);
            }else{
                x_range = inclusiveRange(this.x, other.x);
                y_range = inclusiveRange(this.y, other.y);
            }
            for(int i = 0; i < x_range.length; i++){
                pointsBetween.add(new VentPoint(x_range[i], y_range[i]));
            }
            return pointsBetween;
        }

        private int[] inclusiveRange(int n1, int n2){
            if(n1<n2){
                return IntStream.rangeClosed(n1, n2).toArray();
            }else{
                return IntStream.iterate(n1, i -> i-1).limit(n1-n2 + 1).toArray();
            }
        }
    }
}