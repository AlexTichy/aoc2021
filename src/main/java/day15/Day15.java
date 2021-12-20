package day15;

import lombok.AllArgsConstructor;

import java.awt.*;
import java.util.*;
import java.util.List;

import static Utils.Utils.parseIntGrid;
import static Utils.Utils.readFile;

public class Day15 {

    public static void main(String[] args) {

        String input_file = args[0];

        int first_solution = first(readFile(input_file));
        System.out.println("First solution: " + first_solution);

        int second_solution = second(readFile(input_file));
        System.out.println("Second solution: " + second_solution);
    }

    static int first(List<String> input) {
        int[][] cave = parseIntGrid(input);
        CaveGraph caveGraph = new CaveGraph(new FakeCave(cave, 1));
        return caveGraph.get_minimal_path_cost(new Point(0,0), new Point(cave.length-1, cave[0].length-1));
    }

    static int second(List<String> input){
        int[][] cave_part = parseIntGrid(input);
        FakeCave fakeCave = new FakeCave(cave_part, 5);
        CaveGraph caveGraph = new CaveGraph(fakeCave);
        return caveGraph.get_minimal_path_cost(new Point(0,0), new Point(fakeCave.x_len()-1, fakeCave.y_len()-1));
    }

    @AllArgsConstructor
    static class FakeCave{
        int[][] actual_cave;
        int replications;

        int x_len(){
            return actual_cave.length * replications;
        }

        int y_len(){
            return actual_cave[0].length * replications;
        }

        int get(int x, int y){
            int actual_x = x % actual_cave.length;
            int add_x = x / actual_cave.length;
            int actual_y = y % actual_cave[0].length;
            int add_y = y / actual_cave[0].length;

            int value = (actual_cave[actual_x][actual_y] + add_x + add_y);
            if(value >= 10){
                value = value - 9;
            }
            return value;
        }

        boolean contains(int x, int y){
            if(x >= 0 && x < actual_cave.length * replications){
                if(y >= 0 && y < actual_cave[0].length * replications){
                    return true;
                }
            }
            return false;
        }
    }

    static class CaveGraph {

        FakeCave cave;
        private int x_len;
        private int y_len;
        Map<Point, Integer> costs;
        boolean[][] visited;


        public CaveGraph(FakeCave cave) {
            this.cave = cave;
            x_len = cave.x_len();
            y_len = cave.y_len();
        }

        public int get_minimal_path_cost(Point start, Point end){
            costs = new HashMap<>();
            costs.put(start, 0);
            visited = new boolean[x_len][y_len];
            Point current = start;
            while(!current.equals(end)){
                int cost_to_here = costs.get(current);
                List<Point> neighbours = unvisited_neighbours(current.x, current.y);
                for(Point n: neighbours){
                    costs.merge(n, cost_to_here + cave.get(n.x, n.y),
                            (old_value, new_value) -> new_value < old_value ? new_value : old_value);
                }
                visited[current.x][current.y] = true;
                costs.remove(current);
                current = costs.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
            }
            return costs.get(current);
        }

        private List<Point> unvisited_neighbours(int x, int y) {
            List<Point> neighbours = new ArrayList<>();
            if(cave.contains(x-1, y) && !visited[x-1][y]){
                neighbours.add(new Point(x-1, y));
            }
            if(cave.contains(x+1,y) && !visited[x+1][y]){
                neighbours.add(new Point(x+1, y));
            }
            if(cave.contains(x,y-1)&& !visited[x][y-1]){
                neighbours.add(new Point(x, y-1));
            }
            if(cave.contains(x,y+1) && !visited[x][y+1]){
                neighbours.add(new Point(x, y+1));
            }
            return neighbours;
        }


    }

}
