import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;

public class App {
    public static void main(String[] args) throws Exception {
        ComputeHelper ch = new ComputeHelper();
        
        // Read data from CSV files into Double arrays
        Double[][] adjecencyMatrix = readCSV("/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph4.csv");
        
        AlgorithmResult<Double[][]> result =  calculateTime(ch::primsAlgorithm, adjecencyMatrix, "Prims Algorithm");
        // Printing adjecency matrix
        ch.printAdjacencyMatrix(adjecencyMatrix);

        for (Double d[] : result.getResult()) {
            System.out.println(Arrays.toString(d));
        }
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
