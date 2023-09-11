import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.function.Function;

public class App {
    public static void main(String[] args) throws Exception {
        // For 4 cities in same state
        runPrimsAlgorithm("/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph_4_cities.csv", "/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph_4_cities_result.csv");

        // For 5 cities in same state
        runPrimsAlgorithm("/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph_5_cities.csv", "/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph_5_cities_result.csv");

        // For 6 cities in same state
        runPrimsAlgorithm("/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph_6_cities.csv", "/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph_6_cities_result.csv");

        // For 4 cities in between state
        runPrimsAlgorithm("/workspaces/DSA/prims-algorithm-on-map/data/between_state_graph_4_cities.csv", "/workspaces/DSA/prims-algorithm-on-map/data/between_state_graph_4_cities_result.csv");

        // For 5 cities in between state
        runPrimsAlgorithm("/workspaces/DSA/prims-algorithm-on-map/data/between_state_graph_5_cities.csv", "/workspaces/DSA/prims-algorithm-on-map/data/between_state_graph_5_cities_result.csv");

        // For 6 cities in between state
        runPrimsAlgorithm("/workspaces/DSA/prims-algorithm-on-map/data/between_state_graph_6_cities.csv", "/workspaces/DSA/prims-algorithm-on-map/data/between_state_graph_6_cities_result.csv");
    }


    // Function to call readCSV, primsAlgorithm and writeCSV
    public static void runPrimsAlgorithm(String inputPath, String outputPath) throws Exception {
        ComputeHelper ch = new ComputeHelper();
        Double[][] adjecencyMatrix = readCSV(inputPath);
        AlgorithmResult<Double[][]> result =  calculateTime(ch::primsAlgorithm, adjecencyMatrix, "Prims Algorithm");
        // Print the time taken to run the algorithm
        System.out.println("Time taken to run Prims Algorithm for " + inputPath + " is " + result.getTimeInMilli() + " milliseconds");
        writeCSV(result.getResult(), outputPath);
    }

    // writeCSV
    public static void writeCSV(Double[][] data, String path) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("Source,Destination,Weight");
        writer.newLine();
        for (Double[] d : data) {
            writer.write(String.format("%d,%d,%.2f", d[0].intValue(), d[1].intValue(), d[2]));
            writer.newLine();
        }
        writer.close();
    }

    // readCSV and return adjecency matrix
    public static Double[][] readCSV(String path) throws Exception {

        String[] cities = {};
        // Getting names of all the cities
        BufferedReader br = new BufferedReader(
                new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            cities = values;
            break;
        }
        Double[][] adjecencyMatrix = new Double[cities.length - 1][cities.length - 1];

        // Getting adjecency matrix
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(",")) {
                continue;
            }
            String[] values = line.split(",");
            for (int j = 1; j < values.length; j++) {
                adjecencyMatrix[i][j - 1] = Double.parseDouble(values[j]);
            }
            i++;
        }
        br.close();
        return adjecencyMatrix;
    }


    // Measure the execution time of for any function which take input as integer
    // array.
    public static <T, R> AlgorithmResult<R> calculateTime(Function<T, R> action, T data, String message) {
        long startTime = System.nanoTime();
        R result = action.apply(data);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double timeInMilli = executionTime / 1_000_000.0;
        System.out.println(
                String.format("Time Required to execute %s for data  was %f milliseconds", message,
                        timeInMilli));
        return new AlgorithmResult<R>(result, timeInMilli);
    }

}
