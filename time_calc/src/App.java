import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class App {
    public static void main(String[] args) throws Exception {
        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Read data from CSV files into Integer arrays
        Integer[] to_compute0 = readCSV(currentDirectory + "/time_calc/data/file1.csv", "numbers");
        Integer[] to_compute1 = readCSV(currentDirectory + "/time_calc/data/file2.csv", "volume");
        Integer[] to_compute2 = readCSV(currentDirectory + "/time_calc/data/file3.csv", "volume");

        // Create an instance of ComputeHelper
        ComputeHelper ch = new ComputeHelper();

        // Create lists to store computation times and data lengths
        ArrayList<Double> timeforBruteForce = new ArrayList<>();
        ArrayList<Double> timeforDivideAndConquer = new ArrayList<>();
        ArrayList<Integer> lengthOfData = new ArrayList<>();

        // Add data lengths to the lengthOfData list
        lengthOfData.add(to_compute0.length);
        lengthOfData.add(to_compute1.length);
        lengthOfData.add(to_compute2.length);

        // Calculate and store computation times for both algorithms
        timeforBruteForce.add(calculateTime(ch::bruteForce, to_compute0, "brute force"));
        timeforDivideAndConquer.add(calculateTime(ch::divideAndConquer, to_compute0, "divide and conquer"));

        timeforBruteForce.add(calculateTime(ch::bruteForce, to_compute1, "brute force"));
        timeforDivideAndConquer.add(calculateTime(ch::divideAndConquer, to_compute1, "divide and conquer"));

        timeforBruteForce.add(calculateTime(ch::bruteForce, to_compute2, "brute force"));
        timeforDivideAndConquer.add(calculateTime(ch::divideAndConquer, to_compute2, "divide and conquer"));

        // Writing observations to a .csv file
        FileWriter fileWriter = new FileWriter(currentDirectory + "/time_calc/data/observations.csv");
        try {
            fileWriter.write("length,time of brute force,time for divide and conquer\n");
            for (int x = 0; x < timeforBruteForce.size(); x++) {
                fileWriter.write(String.format("%s,%s,%s\n", Integer.toString(lengthOfData.get(x)),
                        Double.toString(timeforBruteForce.get(x)), Double.toString(timeforDivideAndConquer.get(x))));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fileWriter.close();
        }
    }

    // Read data from a CSV file and return it as an Integer array
    public static Integer[] readCSV(String path, String cloumnName) throws Exception {
        List<Integer> numberList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            String line;
            int index = -1;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (index == -1) {
                    for (int x = 0; x < fields.length; x++) {
                        if (fields[x].compareToIgnoreCase(cloumnName) == 0) {
                            index = x;
                        }
                    }
                } else {
                    numberList.add(Integer.parseInt(fields[index]));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            br.close();
        }
        Integer[] numberArray = numberList.toArray(new Integer[0]);
        return numberArray;
    }

    // Measure the execution time of for any function which take input as integer array.
    public static double calculateTime(Consumer<Integer[]> action, Integer[] data, String message) {
        long startTime = System.nanoTime();
        action.accept(data);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double timeInMilli = executionTime / 1_000_000;
        System.out.println(
                String.format("Time Required to execute %s for data of length %d was %f milliseconds", message,
                        data.length, timeInMilli));
        return timeInMilli;
    }
}
