package day02;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {

    public static void main(String[] args) throws IOException {

        second();
    }

    private static void first() throws IOException {
        Position submarine_position = new Position();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\aoc2021\\src\\main\\java\\day02\\input02.txt"))) {
            String current_line;
            while ((current_line = br.readLine()) != null) {
                String[] split = current_line.split("\\s+");
                submarine_position.move_first(split[0], Integer.parseInt(split[1]));
            }
        }

        System.out.println(submarine_position.getDepth() * submarine_position.getHorizontal());
    }

    private static void second() throws IOException {
        Position submarine_position = new Position();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\aoc2021\\src\\main\\java\\day02\\input02.txt"))) {
            String current_line;
            while ((current_line = br.readLine()) != null) {
                String[] split = current_line.split("\\s+");
                submarine_position.move_second(split[0], Integer.parseInt(split[1]));
            }
        }

        System.out.println(submarine_position.getDepth() * submarine_position.getHorizontal());
    }

    @Getter
    static class Position{
        int depth = 0;
        int horizontal = 0;
        int aim = 0;

        void move_first(String direction, int units){
            if (direction.contentEquals("forward")) {
                horizontal += units;
            }else if (direction.contentEquals("up")){
                depth -= units;
            }else if (direction.contentEquals("down")){
                depth += units;
            }else{
                System.out.println("Found unknown direction: " + direction);
            }
        }

        void move_second(String direction, int units){
            if (direction.contentEquals("forward")) {
                horizontal += units;
                depth += aim * units;
            }else if (direction.contentEquals("up")){
                aim -= units;
            }else if (direction.contentEquals("down")){
                aim += units;
            }else{
                System.out.println("Found unknown direction: " + direction);
            }
        }

    }
}
