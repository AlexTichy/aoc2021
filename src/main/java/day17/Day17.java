package day17;

import java.util.*;

public class Day17 {

    public static void main(String[] args) {

        //x=128..160, y=-142..-88
        int x_min = 128; int x_max = 160;
        int y_min = -142; int y_max = -88;

        int first_solution = first(x_min, x_max, y_min, y_max);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(x_min, x_max, y_min, y_max);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(int x_min, int x_max, int y_min, int y_max) {
        int y_velocity = Math.abs(y_min) - 1;
        return y_velocity * (y_velocity + 1)/2;
    }


    static int second(int x_min, int x_max, int y_min, int y_max) {
        Map<Integer, List<Integer>> y_velocity_candidates = y_velocity_candidates(y_min, y_max);

        List<Integer> all_y_steps = new ArrayList<>();
        y_velocity_candidates.values().forEach(all_y_steps::addAll);
        int max_y_steps = all_y_steps.stream().max(Integer::compareTo).get();

        Map<Integer, List<Integer>> x_velocity_candidates = x_velocity_candidates(x_min, x_max, max_y_steps);

        int num_candidates = 0;
        for(Integer x_velocity: x_velocity_candidates.keySet()){
            for(Integer y_velocity: y_velocity_candidates.keySet()){
                if(!Collections.disjoint(x_velocity_candidates.get(x_velocity), y_velocity_candidates.get(y_velocity))){
                    num_candidates ++;
                }
            }
        }
        return num_candidates;
    }

    static Map<Integer, List<Integer>> y_velocity_candidates(int y_min, int y_max) {
        int min_y_velocity = y_min;
        int max_y_velocity = -y_min -1;

        // map: number of steps to reach position within target zone  -> number of starting y velocities with this behaviour
        Map<Integer, List<Integer>> cand = new HashMap<>();
        for(int start_velocity = min_y_velocity; start_velocity<= max_y_velocity; start_velocity++){

            int y_position = 0;
            int current_velocity = start_velocity;
            int steps = 0;
            while(true){
                steps ++;
                y_position += current_velocity;
                if(y_position < y_min){
                    break;
                }
                if(y_position >= y_min && y_position <= y_max){
                    if(!cand.containsKey(start_velocity)){
                        cand.put(start_velocity, new ArrayList<>());
                    }
                    cand.get(start_velocity).add(steps);
                }
                current_velocity --;
            }
        }
        return cand;
    }

    static Map<Integer, List<Integer>> x_velocity_candidates(int x_min, int x_max, int max_y_steps){
        int min_x_velocity = find_min_x_velocity(x_min, x_max);
        Map<Integer, List<Integer>> cand = new HashMap<>();
        for(int i = min_x_velocity; i<= x_max; i++){

            int x_position = 0;
            int current_velocity = i;
            int steps = 0;
            while(true){

                steps ++;
                x_position += current_velocity;

                if(x_position > x_max || steps > max_y_steps){
                    break;
                }

                if(x_position >= x_min && x_position <= x_max){
                    if(!cand.containsKey(i)){
                        cand.put(i, new ArrayList<>());
                    }
                    cand.get(i).add(steps);
                }

                current_velocity = Math.max(current_velocity - 1, 0);
            }
        }
        return cand;
    }

    static int find_min_x_velocity(int x_min, int x_max) {
        int x_velocity = 0;
        int sum = 0;
        int current_x = 0;
        while(true){
            current_x ++;
            sum += current_x;
            if(sum > x_max){
                return -1;
            }
            if(sum >= x_min){
                return x_velocity;
            }
        }
    }






}
