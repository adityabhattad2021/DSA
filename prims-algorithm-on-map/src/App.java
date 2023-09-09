import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.annotation.Documented;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Create adjecency matrix

        // Read data from CSV files into Double arrays
        Double[][] adjecencyMatrix = readCSV("/workspaces/DSA/prims-algorithm-on-map/data/within_state_graph3.csv");

        // Printing adjecency matrix
        printAdjecencyMatrix(adjecencyMatrix);

        // Applying Prims Algorithm
        Double[][] minimum_spanning_tree = primsAlgorithm(adjecencyMatrix);

        for (Double d[] : minimum_spanning_tree) {
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

    public static void printAdjecencyMatrix(Double[][] adjecencyMatrix) {
        int numberOfVertices = adjecencyMatrix.length;
        
        for (int j = 0; j < numberOfVertices; j++) {
            for (int k = 0; k < numberOfVertices; k++) {
                if(adjecencyMatrix[j][k] == 0.0){
                    System.out.print(String.format("  %.2f  |", adjecencyMatrix[j][k]));
                }else{
                    System.out.print(String.format("%.2f  |", adjecencyMatrix[j][k]));
                }
            }
            System.out.println();

        }

    }

    // Part 5: Implement Prims Algorithm
    /*
     * This will return minimum spanning tree in tree tuple form.
     */
    public static Double[][] primsAlgorithm(Double[][] adjecencyMatrix) {
        int numberOfVertices = adjecencyMatrix.length;
        Double[][] minimum_spanning_tree = new Double[numberOfVertices - 1][3];
        Double[] temp_costs_array = new Double[numberOfVertices];

        // Find the least value in adjacency matrix
        Double leastValue = Double.MAX_VALUE;
        int indexSelectedX = 0;
        int indexSelectedY = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                if (adjecencyMatrix[i][j] < leastValue && adjecencyMatrix[i][j] != 0) {
                    leastValue = adjecencyMatrix[i][j];
                    indexSelectedX = i;
                    indexSelectedY = j;
                }
            }
        }
        minimum_spanning_tree[0][0] = (double) indexSelectedX;
        minimum_spanning_tree[0][1] = (double) indexSelectedY;
        minimum_spanning_tree[0][2] = adjecencyMatrix[indexSelectedX][indexSelectedY];

        temp_costs_array[indexSelectedX] = 0.0;
        temp_costs_array[indexSelectedY] = 0.0;

        // For vertices to done array mark the vertex as done.
        boolean[] doneIndexes = new boolean[numberOfVertices];
        doneIndexes[indexSelectedX] = true;
        doneIndexes[indexSelectedY] = true;

        int closest_to = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            if (i == indexSelectedX || i == indexSelectedY) {
                continue;
            }
            if (adjecencyMatrix[i][indexSelectedX] < adjecencyMatrix[i][indexSelectedY]) {
                temp_costs_array[i] = adjecencyMatrix[i][indexSelectedX];
                closest_to = indexSelectedX;
            } else {
                temp_costs_array[i] = adjecencyMatrix[i][indexSelectedY];
                closest_to = indexSelectedY;
            }
        }
        Double min_cost = Double.MAX_VALUE;
        int index_of_element_closest = 0;
        for (int i = 0; i < temp_costs_array.length; i++) {
            if (temp_costs_array[i] < min_cost && !doneIndexes[i]) {
                min_cost = temp_costs_array[i];
                index_of_element_closest = i;
            }
        }
        minimum_spanning_tree[1][0] = (double) closest_to;
        minimum_spanning_tree[1][1] = (double) index_of_element_closest;
        minimum_spanning_tree[1][2] = temp_costs_array[index_of_element_closest];
        temp_costs_array[index_of_element_closest] = 0.0;
        doneIndexes[index_of_element_closest] = true;

        int ttfIndex = 2;
        while (true) {
            boolean stop = true;
            for (boolean b : doneIndexes) {
                if (b == false) {
                    stop = false;
                }
            }
            if (stop) {
                break;
            }

            for (int i = 0; i < temp_costs_array.length; i++) {
                if (doneIndexes[i]) {
                    continue;
                }
                if (adjecencyMatrix[i][index_of_element_closest] < temp_costs_array[i]) {
                    closest_to = index_of_element_closest;
                    temp_costs_array[i] = adjecencyMatrix[i][index_of_element_closest];
                }
            }
            min_cost = Double.MAX_VALUE;
            for (int i = 0; i < temp_costs_array.length; i++) {
                if (doneIndexes[i]) {
                    continue;
                }
                if (temp_costs_array[i] < min_cost) {
                    min_cost = temp_costs_array[i];
                    index_of_element_closest = i;
                }
            }

            minimum_spanning_tree[ttfIndex][0] = (double) closest_to;
            minimum_spanning_tree[ttfIndex][1] = (double) index_of_element_closest;
            minimum_spanning_tree[ttfIndex][2] = temp_costs_array[index_of_element_closest];
            temp_costs_array[index_of_element_closest] = 0.0;
            doneIndexes[index_of_element_closest] = true;

            ttfIndex++;
        }

        return minimum_spanning_tree;
    }

}
