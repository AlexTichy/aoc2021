package day09;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Utils.Utils.readFile;

public class Day09 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day09\\input09.txt";

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        List<String> input = readFile(input_file);
        char[][] heightmap = new char[input.size()][];
        List<Point> low_point_candidates = new ArrayList<>();
        for(int i = 0; i < input.size(); i++){
            char[] line = input.get(i).toCharArray();
            for(int j = 0; j<line.length; j++){
                int height = line[j];
                if(j == 0 || line[j-1] > height){
                    if(j == line.length - 1 || line[j+1] > height){
                        low_point_candidates.add(new Point(i,j));
                    }
                }
            }
            heightmap[i] = line;
        }

        List<Integer> low_point_heights = new ArrayList<>();
        for(Point p: low_point_candidates){
            char height = heightmap[p.x][p.y];
            if(p.x == 0 || heightmap[p.x-1][p.y] > height){
                if(p.x == heightmap.length - 1 || heightmap[p.x+1][p.y] > height){
                    low_point_heights.add(Integer.parseInt(String.valueOf(height)));
                }
            }
        }

        return low_point_heights.size() + low_point_heights.stream().mapToInt(Integer::intValue).sum();
    }

    static int second(String input_file) {
        List<String> input = readFile(input_file);

        char[][] heightmap = new char[input.size()][];
        for(int i = 0; i < input.size(); i++){
            heightmap[i] = input.get(i).toCharArray();
        }

        List<Integer> basin_sizes = new ArrayList<>();
        for(int i = 0; i < heightmap.length; i++){
            for(int j = 0; j < heightmap[0].length; j++){
                if(heightmap[i][j] == '-' || heightmap[i][j] == '9'){
                    continue;
                }
                basin_sizes.add(get_basin_size(heightmap, new Point(i,j)));

            }
        }
        Collections.sort(basin_sizes, Collections.reverseOrder());

        return basin_sizes.get(0) * basin_sizes.get(1) * basin_sizes.get(2);
    }

    static int get_basin_size(char[][] heightmap, Point seed){

        int size = 0;
        List<Point> points = new ArrayList<>();
        points.add(seed);
        while(!points.isEmpty()){
            Point p = points.remove(0);
            char height = heightmap[p.x][p.y];
            if( height == '-' || height == '9'){
                continue;
            }
            size += 1;
            heightmap[p.x][p.y] = '-';
            if(p.x > 0) {
                points.add(new Point(p.x - 1, p.y));
            }
            if(p.x < heightmap.length -1) {
                points.add(new Point(p.x + 1, p.y));
            }
            if(p.y > 0) {
                points.add(new Point(p.x, p.y -1));
            }
            if(p.y < heightmap[0].length -1) {
                points.add(new Point(p.x, p.y+1));
            }
        }
        return size;
    }

}
