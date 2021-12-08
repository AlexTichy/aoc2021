package day08;

import java.util.*;

import static Utils.Utils.readFile;

public class Day08 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day08\\input08.txt";

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        List<String> input = readFile(input_file);
        int count = 0;
        for(String line: input){
            List<String> relevant_parts = Arrays.asList(line.split("\\s")).subList(11, 15);
            for(String digit: relevant_parts){
                int l = digit.length();
                if(l == 2 || l == 3 || l == 4 || l == 7){
                    count += 1;
                }
            }
        }
        return count;
    }


    static int second(String input_file) {

        List<String> input = readFile(input_file);
        int sum = 0;
        for(String line: input){
            List<String> digits = Arrays.asList(line.split("\\s"));
            DigitDecoder digitDecoder = find_digit_mapping(digits.subList(0, 10));
            int value = 0;
            for(String digit: digits.subList(11,15)){
                int d = digitDecoder.decode(digit);
                value = value*10 + d;
            }
            sum += value;
        }

        return sum;
    }

    static private DigitDecoder find_digit_mapping(List<String> input){
        //a-0, g-6
        String one = null; String four = null; String seven = null;
        List<String> five_segments = new ArrayList<>();
        List<String> six_segments = new ArrayList<>();

        for(String signal: input){
            switch (signal.length()) {
                case 2 -> one = signal;
                case 3 -> seven = signal;
                case 4 -> four = signal;
                case 5 -> five_segments.add(signal);
                case 6 -> six_segments.add(signal);
            }
        }

        String zero = null; String two; String three = null; String five = null; String six; String nine = null;

        for(String cand: six_segments){
            if(containsAllCharsOf(cand, four)){
                nine = cand;
                break;
            }
        }
        six_segments.remove(nine);

        for(String cand: six_segments){
            if(containsAllCharsOf(cand, one)){
                zero = cand;
                break;
            }
        }
        six_segments.remove(zero);
        six = six_segments.get(0);

        for(String cand: five_segments){
            if(containsAllCharsOf(cand,seven)){
                three = cand;
                break;
            }
        }
        five_segments.remove(three);

        for(String cand: five_segments){
            if(containsAllCharsOf(six,cand)){
                five = cand;
                break;
            }
        }
        five_segments.remove(five);
        two = five_segments.get(0);

        return new DigitDecoder(zero, two, three, five, six, nine);

    }

    static boolean containsAllCharsOf(String s1, String s2){
        for(char c: s2.toCharArray()){
            if(s1.indexOf(c) < 0){
                return false;
            }
        }
        return true;
    }

    static class DigitDecoder{
        Map<String, Integer> five_digit_mapping = new HashMap<>();
        Map<String,Integer> six_digit_mapping = new HashMap<>();

        DigitDecoder(String zero, String two, String three, String five, String six, String nine){
            five_digit_mapping.put(sortDigit(two), 2);
            five_digit_mapping.put(sortDigit(three), 3);
            five_digit_mapping.put(sortDigit(five), 5);
            six_digit_mapping.put(sortDigit(zero), 0);
            six_digit_mapping.put(sortDigit(six), 6);
            six_digit_mapping.put(sortDigit(nine), 9);
        }

        int decode(String digit){
            return switch (digit.length()) {
                case 2 -> 1;
                case 3 -> 7;
                case 4 -> 4;
                case 5 -> five_digit_mapping.get(sortDigit(digit));
                case 6 -> six_digit_mapping.get(sortDigit(digit));
                case 7 -> 8;
                default -> -1;
            };
        }

        private String sortDigit(String digit) {
            char[] digitArray = digit.toCharArray();
            Arrays.sort(digitArray);
            return String.valueOf(digitArray);
        }

    }

}
