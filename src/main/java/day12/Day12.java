package day12;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static Utils.Utils.readFile;

public class Day12 {

    public static void main(String[] args) {

        String input_file = args[0];

        int first_solution = first(input_file);
        System.out.println("First solution: " + first_solution);

        int second_solution = second(input_file);
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String input_file) {
        CaveSystem cs = new CaveSystem(readFile(input_file));
        List<List<String>> paths = find_paths(cs, "start", new ArrayList<>(),
                        (cave, path) -> !(Character.isLowerCase(cave.charAt(0)) && path.contains(cave)));
        return paths.size();
    }

    static int second(String input_file) {
        CaveSystem cs = new CaveSystem(readFile(input_file));
        List<List<String>> paths = find_paths(cs, "start", new ArrayList<>(),
                Day12::valid_connection_second);

        return paths.size();
    }

    static boolean valid_connection_second(String cave, List<String> path){
        if(Character.isUpperCase(cave.charAt(0)) || !path.contains(cave)){
            return true;
        }else if ("start".equals(cave)) {
            return false;
        }else{
            for(String c: path){
                //already contains a small cave twice
                if(Character.isLowerCase(c.charAt(0)) && path.indexOf(c) != path.lastIndexOf(c)){
                    return false;
                }
            }
            return true;
        }
    }

    static List<List<String>> find_paths(
            CaveSystem cs, String current_cave, List<String> path, BiPredicate<String, List<String>> valid_connection){
        path.add(current_cave);
        if(current_cave.equals("end")){
            return Collections.singletonList(path);
        }
        List<String> connections = cs.getConnections(current_cave);
        List<List<String>> paths_from_here = new ArrayList<>();
        for(String c: connections){
            if(valid_connection.test(c, path)){
                paths_from_here.addAll(find_paths(cs, c, new ArrayList<>(path), valid_connection));
            }
        }
        return paths_from_here;
    }

    static class CaveSystem{
        Map<String, List<String>> cave_system = new HashMap<>();

        CaveSystem(List<String> description){
            for(String line: description){
                String[] caves = line.split("-");
                add_connection(caves[0], caves[1]);
            }
        }

        void add_connection(String cave1, String cave2){
            if(!cave_system.containsKey(cave1)){
                cave_system.put(cave1, new ArrayList<>());
            }
            cave_system.get(cave1).add(cave2);

            if(!cave_system.containsKey(cave2)){
                cave_system.put(cave2, new ArrayList<>());
            }
            cave_system.get(cave2).add(cave1);
        }

        List<String> getConnections(String cave){
            return cave_system.get(cave);
        }

    }

}
