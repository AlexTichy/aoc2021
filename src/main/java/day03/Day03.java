package day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static Utils.Utils.readFile;

public class Day03 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day03\\input03.txt";
        int first_solution = first(input_file);
        System.out.println(first_solution);

        int second_solution = second(input_file);
        System.out.println(second_solution);
    }

    static int first(String input_file) {
        List<String> input = readFile(input_file);
        int[] counts = getOneCounts(input);

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for(int c: counts){
            if(c> input.size()/2){
                gamma.append("1");
                epsilon.append("0");
            }else{
                gamma.append("0");
                epsilon.append("1");
            }
        }
        return Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2);
    }


    static int second(String input_file) {
        List<String> input = readFile(input_file);
        int[] counts = getOneCounts(input);

        List<String> oxygenCandidates = new ArrayList<>(input);

        List<String> co2Candidates = new ArrayList<>(input);

        int currentIndex = 0;

        while(oxygenCandidates.size() > 1) {

            Iterator<String> oxygenIterator = oxygenCandidates.listIterator();

            List<String> oxygenOnes = new ArrayList<>();
            List<String> oxygenZeros = new ArrayList<>();

            while (oxygenIterator.hasNext()) {
                String currentLine = oxygenIterator.next();
                if (currentLine.charAt(currentIndex) == '1') {
                    oxygenOnes.add(currentLine);
                } else {
                    oxygenZeros.add(currentLine);
                }
            }

            oxygenCandidates = oxygenOnes.size() >= oxygenZeros.size() ? oxygenOnes : oxygenZeros;
            currentIndex++;
        }

        currentIndex = 0;

        while(co2Candidates.size() > 1) {

            Iterator<String> co2Iterator = co2Candidates.listIterator();

            List<String> co2Ones = new ArrayList<>();
            List<String> co2Zeros = new ArrayList<>();

            while (co2Iterator.hasNext()) {
                String currentLine = co2Iterator.next();
                if (currentLine.charAt(currentIndex) == '1') {
                    co2Ones.add(currentLine);
                } else {
                    co2Zeros.add(currentLine);
                }
            }

            co2Candidates = co2Ones.size() < co2Zeros.size() ? co2Ones : co2Zeros;
            currentIndex++;
        }

        String oxygen = oxygenCandidates.get(0);
        String co2 = co2Candidates.get(0);

        return Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2);
    }

    private static int[] getOneCounts(List<String> input) {
        int[] counts = new int[input.get(0).length()];

        input.forEach(
                line -> {
                    char[] chars = line.toCharArray();
                    for (int i = 0; i < chars.length; i++){
                        if(chars[i] == '1') {counts[i]++ ;}
                    }
                }
        );
        return counts;
    }


}

