package day14;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static Utils.Utils.readFile;

public class Day14 {

    public static void main(String[] args) {

        String input_file = args[0];

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        long second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        List<String> input = readFile(input_file);

        Map<String, Character> rules = new HashMap<>();
        for(String line:input.subList(2, input.size())){
            rules.put(line.substring(0, 2), line.charAt(6));
        }

        char[] polymer = input.get(0).toCharArray();
        for(int i = 0; i<10; i++){
            char[] new_polymer = new char[2*polymer.length -1];
            new_polymer[0] = polymer[0];
            for (int j = 1; j < polymer.length ; j++) {
                char inserted_element = rules.get("" + polymer[j-1] + polymer[j]);
                new_polymer[(2*j-1)] = inserted_element;
                new_polymer[2*j] = polymer[j];
            }
            polymer = new_polymer;
        }

        Map<Character, Integer> counts = new HashMap<>();
        for(char c: polymer){
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        Integer min_count = counts.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();
        Integer max_count = counts.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        return max_count - min_count;
    }

    static long second(String input_file) {
        List<String> input = readFile(input_file);
        Polymerizer polymerizer = new Polymerizer(input.subList(2, input.size()));
        Map<Character, Long> counts_from_template = polymerizer.counts_from_template(input.get(0), 40);

        Long min_count = counts_from_template.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();
        Long max_count = counts_from_template.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        return max_count - min_count;

    }

    static class Polymerizer{

        Map<String, Character> rules;
        Map<Integer, Map<String, CountMap>> cache = new HashMap<>();

        Polymerizer(List<String> description){
            rules = new HashMap<>();
            for(String line:description){
                rules.put(line.substring(0, 2), line.charAt(6));
            }
        }

        Map<Character, Long> counts_from_template(String template, int steps){
            CountMap count_map = element_counts(template.charAt(0), template.charAt(1), steps);
            for (int i = 1; i < template.length()-1; i++) {
                CountMap next_count_map = element_counts(template.charAt(i), template.charAt(i + 1), steps);
                count_map = count_map.join(template.charAt(i), next_count_map);
            }
            return count_map.counts;
        }

        private CountMap element_counts(char left, char right, int steps_left){

            if(steps_left == 0){
                if(left == right){
                    return new CountMap(left);
                }
                return new CountMap(left, right);
            }


            String keypair = "" +left +right;
            if(steps_left%5 != 0){
                char middle = rules.get(keypair);
                CountMap left_counts = element_counts(left, middle, steps_left - 1);
                CountMap right_counts = element_counts(middle, right, steps_left - 1);
                return left_counts.join(middle, right_counts);
            }

            if(!cache.containsKey(steps_left)) {
                cache.put(steps_left, new HashMap<>());
            }

            if(!cache.get(steps_left).containsKey(keypair)){
                char middle = rules.get(keypair);
                CountMap left_counts = element_counts(left, middle, steps_left - 1);
                CountMap right_counts = element_counts(middle, right, steps_left - 1);
                cache.get(steps_left).put(keypair, left_counts.join(middle, right_counts));
            }

            return cache.get(steps_left).get(keypair);
        }

    }

    @AllArgsConstructor
    static class CountMap{

        HashMap<Character, Long> counts;

        public CountMap(char left_and_right) {
            counts = new HashMap<>();
            counts.put(left_and_right, 2L);
        }

        public CountMap(char left, char right) {
            counts = new HashMap<>();
            counts.put(left, 1L);
            counts.put(right, 1L);
        }


        private CountMap join(char middle, CountMap other) {
            HashMap<Character, Long> total_counts = new HashMap<>(counts);
            other.counts.forEach
                    ((character, count) -> total_counts.merge(character, count, Long::sum));
            total_counts.replace(middle, total_counts.get(middle) -1);
            return new CountMap(total_counts);
        }

    }

}
