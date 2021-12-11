package day10;

import java.util.*;

import static Utils.Utils.readFile;

public class Day10 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day10\\input10.txt";

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        long second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        Map<Character, Integer> scores = Map.of(
                ')', 3,
                ']', 57,
                '}', 1197,
                '>', 25137);
        Map<Character, Character> open_close_map = Map.of(
                ')', '(',
                ']', '[',
                '}', '{',
                '>', '<');
        List<String> input = readFile(input_file);
        int score = 0;
        for(String line: input){
            Stack<Character> stack = new Stack<>();
            for(char c: line.toCharArray()){
                if(open_close_map.containsValue(c)){ //is opening character
                    stack.push(c);
                }else{                              //is closing character
                    char stack_top = stack.pop();
                    if(open_close_map.get(c) != stack_top){
                        score += scores.get(c);
                        break;
                    }
                }
            }
        }
        return score;
    }

    static long second(String input_file) {
        Map<Character, Integer> scores = Map.of(
                '(', 1,
                '[', 2,
                '{', 3,
                '<', 4);
        Map<Character, Character> open_close_map = Map.of(
                ')', '(',
                ']', '[',
                '}', '{',
                '>', '<');
        List<String> input = readFile(input_file);
        List<Long> score_list = new ArrayList<>();

        for(String line: input){
            Stack<Character> stack = new Stack<>();
            boolean broken = false;
            for(char c: line.toCharArray()){
                if(open_close_map.containsValue(c)){ //is opening character
                    stack.push(c);
                }else{                              //is closing character
                    char stack_top = stack.pop();
                    if(open_close_map.get(c) != stack_top){
                        broken = true;
                        break;
                    }
                }
            }
            if(broken){
                continue;
            }
            long score = 0;
            while(!stack.empty()){
                char c = stack.pop();
                score = score * 5 + scores.get(c);
            }
            System.out.println(line + " --> score: " + score);
            score_list.add(score);
        }
        Collections.sort(score_list);
        return score_list.get(score_list.size()/2);
    }

}
