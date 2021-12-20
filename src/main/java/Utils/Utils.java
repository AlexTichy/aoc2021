package Utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    @SneakyThrows
    public static List<String> readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            return br.lines().collect(Collectors.toList());
        }
    }

    public static int[][] parseIntGrid(List<String> input) {
        int[][] grid = new int[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            char[] line = input.get(i).toCharArray();
            grid[i] = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(line[j]));
            }
        }
        return grid;
    }
}
