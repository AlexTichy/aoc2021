package day04;

import java.sql.Array;
import java.util.*;

import static Utils.Utils.readFile;

public class Day04 {

    public static void main(String[] args) {

        String input_file = "src\\main\\java\\day04\\input04.txt";

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        List<String> input = readFile(input_file);
        String[] drawn_numbers = input.get(0).split(",");

        List<Board> boards = parseBoards(input.subList(1, input.size()));

        for(String n: drawn_numbers){
            for(Board b: boards){
                b.mark(Integer.parseInt(n));
                if(b.wins()){
                    return b.getScore(Integer.parseInt(n));
                }
            }
        }

        return -1;
    }

    static int second(String input_file) {
        List<String> input = readFile(input_file);
        String[] drawn_numbers = input.get(0).split(",");

        List<Board> boards = parseBoards(input.subList(1, input.size()));

        for(String n: drawn_numbers){
            int current_number = Integer.parseInt(n);
            Iterator<Board> board_iterator = boards.iterator();
            while(board_iterator.hasNext()){
                Board b = board_iterator.next();
                b.mark(current_number);
                if(b.wins()) {
                    if(boards.size() == 1){
                        return b.getScore(current_number);
                    }else {
                        board_iterator.remove();
                    }
                }
            }
        }
        return -1;

    }

    private static List<Board> parseBoards(List<String> input) {

        List<Board> boards = new ArrayList<>();
        List<List<Integer>> bingo_board = null;

        for(String line: input){
            if(line.isBlank()){
                if(bingo_board != null){
                    boards.add(new Board(bingo_board));
                }
                bingo_board = new ArrayList<>();
            }else{
                ArrayList<Integer> row = new ArrayList<>();
                Arrays.asList(line.trim().split("\\s+")).forEach(n -> row.add(Integer.parseInt(n)));
                bingo_board.add(row);
            }
        }
        boards.add(new Board(bingo_board));
        return boards;
    }

    static class Board{

        HashMap<Integer, int[]> number_map;
        boolean [][] marks;

        public Board(List<List<Integer>> numbers){
            initializeNumberMap(numbers);
            this.marks = new boolean[numbers.size()][numbers.get(0).size()];
        }

        private void initializeNumberMap(List<List<Integer>> numbers){
            number_map = new HashMap<>();
            for(int i = 0; i < numbers.size(); i++){
                for(int j = 0; j < numbers.get(0).size(); j++) {
                    number_map.put(numbers.get(i).get(j), new int[]{i, j});
                }
            }
        }

        public void mark(int number){
            int[] coord = number_map.get(number);
            if(coord != null){
                marks[coord[0]][coord[1]] = true;
            }
        }

        public boolean wins(){
            for(int i = 0; i < marks.length; i++){
                if(fullRow(i)){
                    return true;
                }
            }

            for(int j = 0; j < marks[0].length; j++){
                if (fullColumn(j)){
                    return true;
                }
            }
            return false;
        }

        private boolean fullRow(int i){
            for(int j = 0; j < marks[0].length; j++){
                if (!marks[i][j]){
                    return false;
                }
            }
            return true;
        }

        private boolean fullColumn(int j){
            for (boolean[] mark : marks) {
                if (!mark[j]) {
                    return false;
                }
            }
            return true;
        }

        public int getScore(int lastNumber) {
            int sum = 0;
            for(Integer number: number_map.keySet()){
                int[] coord = number_map.get(number);
                if(!marks[coord[0]][coord[1]]){
                    sum += number;
                }
            }
            return lastNumber * sum;
        }
    }

}
