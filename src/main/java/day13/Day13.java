package day13;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static Utils.Utils.readFile;

public class Day13 {

    public static void main(String[] args) {

        String input_file = args[0];

        long first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        long second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static long first(String input_file) {
        Iterator<String> input = readFile(input_file).iterator();
        Transparency transparency = new Transparency();
        while(input.hasNext()){
            String line = input.next();
            if(line.isBlank()){
                break;
            }
            String[] coord = line.split(",");
            transparency.add_point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        }

        String inst = input.next();
        int coord = Integer.parseInt(inst.split("=")[1]);
        transparency.fold_x(coord);
        return transparency.number_of_points();
    }

    static long second(String input_file) {

        Iterator<String> input = readFile(input_file).iterator();
        Transparency transparency = new Transparency();
        while(input.hasNext()){
            String line = input.next();
            if(line.isBlank()){
                break;
            }
            String[] coord = line.split(",");
            transparency.add_point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        }

        while(input.hasNext()){
            String inst = input.next();
            int coord = Integer.parseInt(inst.split("=")[1]);
            if(inst.contains("x")){
                transparency.fold_x(coord);
            }else if(inst.contains("y")){
                transparency.fold_y(coord);
            }
        }
        transparency.print();
        return transparency.number_of_points();
    }

    static class Transparency {
        List<Point> points = new ArrayList<>();
        int x_max = -1;
        int y_max = -1;

        void add_point(int x, int y){
            points.add(new Point(x,y));
        }

        void fold_x(int fold_coordinate){
            x_max = fold_coordinate -1;
            for(Point p: points){
                if(p.x > fold_coordinate){
                    p.x = fold_coordinate - (p.x - fold_coordinate);
                }
            }
        }

        void fold_y(int fold_coordinate){
            y_max = fold_coordinate-1;
            Iterator<Point> iterator = points.iterator();
            for(Point p: points){
                if(p.y > fold_coordinate){
                    p.y = fold_coordinate - (p.y - fold_coordinate);
                }
            }
        }

        long number_of_points(){
            return points.stream().distinct().count();
        }

        void print(){
            if(x_max == 0 || y_max == 0){
                System.out.println("Currently to lazy to print this.");
                return;
            }
            for(int i = 0; i <= y_max; i++){
                for (int j = 0; j <= x_max; j++) {
                    if(points.contains(new Point(j,i))){
                        System.out.print("#");
                    }else{
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
        }

    }

}
