package Utils;

import lombok.SneakyThrows;

import java.io.*;
import java.time.LocalDate;

public class DailySetupHelper {

    static String folderPattern = "src\\main\\java\\day%02d\\";
    static String inputFilePattern = "input%02d.txt";
    static String templateFile = "src\\main\\resources\\DayXXTemplate";

    public static void main(String[] args) throws IOException {

        int dayOfMonth = LocalDate.now().getDayOfMonth();
        String folder = String.format(folderPattern, dayOfMonth);

        File inputFile = new File(folder + String.format(inputFilePattern, dayOfMonth));
        if(!inputFile.getParentFile().exists()){
            inputFile.getParentFile().mkdirs();
            inputFile.createNewFile();
            copyClass(dayOfMonth, folder);
            System.out.println("Created new directory for day " + dayOfMonth + ".");
        }else{
            System.out.println("Directory for day " + dayOfMonth + " already exists.");
        }
    }

    @SneakyThrows
    private static void copyClass(int day, String folder){
        String inputFile = (folder + String.format(inputFilePattern, day)).replace("\\", "\\\\");
        try (BufferedReader br = new BufferedReader(new FileReader(templateFile))) {
            try (BufferedWriter bw =
                         new BufferedWriter(new FileWriter(folder + String.format("Day%02d.java", day)))) {

                String line;
                while((line = br.readLine()) != null) {
                    line = line.replace("XXTemplate", String.format("%02d", day));
                    line = line.replace("PATH", inputFile);
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
    }


}
