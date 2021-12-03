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
}
