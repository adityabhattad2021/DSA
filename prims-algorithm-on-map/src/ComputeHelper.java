public class ComputeHelper {
    public Double[][] primsAlgorithm(Double[][] adjacencyMatrix) {
        int numberOfVertices = adjacencyMatrix.length;
        Double[][] minimum_spanning_tree = new Double[numberOfVertices - 1][3];
        Double[] temp_costs_array = new Double[numberOfVertices];

        // Find the least value in the adjacency matrix
        Double leastValue = Double.MAX_VALUE;
        int indexSelectedX = 0;
        int indexSelectedY = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] < leastValue && adjacencyMatrix[i][j] != 0) {
                    leastValue = adjacencyMatrix[i][j];
                    indexSelectedX = i;
                    indexSelectedY = j;
                }
            }
        }
        // Initialize the minimum spanning tree with the first edge
        minimum_spanning_tree[0][0] = (double) indexSelectedX;
        minimum_spanning_tree[0][1] = (double) indexSelectedY;
        minimum_spanning_tree[0][2] = adjacencyMatrix[indexSelectedX][indexSelectedY];

        temp_costs_array[indexSelectedX] = 0.0;
        temp_costs_array[indexSelectedY] = 0.0;

        // Mark the selected vertices as done
        boolean[] doneIndexes = new boolean[numberOfVertices];
        doneIndexes[indexSelectedX] = true;
        doneIndexes[indexSelectedY] = true;

        int closest_to = 0;
        // Find the closest vertices for the remaining ones
        for (int i = 0; i < numberOfVertices; i++) {
            if (i == indexSelectedX || i == indexSelectedY) {
                continue;
            }
            if (adjacencyMatrix[i][indexSelectedX] < adjacencyMatrix[i][indexSelectedY]) {
                temp_costs_array[i] = adjacencyMatrix[i][indexSelectedX];
                closest_to = indexSelectedX;
            } else {
                temp_costs_array[i] = adjacencyMatrix[i][indexSelectedY];
                closest_to = indexSelectedY;
            }
        }
        Double min_cost = Double.MAX_VALUE;
        int index_of_element_closest = 0;
        // Find the minimum cost vertex to expand to next
        for (int i = 0; i < temp_costs_array.length; i++) {
            if (temp_costs_array[i] < min_cost && !doneIndexes[i]) {
                min_cost = temp_costs_array[i];
                index_of_element_closest = i;
            }
        }
        // Update the minimum spanning tree and mark the selected vertex as done
        minimum_spanning_tree[1][0] = (double) closest_to;
        minimum_spanning_tree[1][1] = (double) index_of_element_closest;
        minimum_spanning_tree[1][2] = temp_costs_array[index_of_element_closest];
        temp_costs_array[index_of_element_closest] = 0.0;
        doneIndexes[index_of_element_closest] = true;

        int ttfIndex = 2;
        // Continue until all vertices are marked as done
        while (true) {
            boolean stop = true;
            for (boolean b : doneIndexes) {
                if (!b) {
                    stop = false;
                    break; // Exit the loop early if there are remaining vertices
                }
            }
            if (stop) {
                break;
            }

            // Update the temporary costs array and find the next minimum cost vertex
            for (int i = 0; i < temp_costs_array.length; i++) {
                if (doneIndexes[i]) {
                    continue;
                }
                if (adjacencyMatrix[i][index_of_element_closest] < temp_costs_array[i]) {
                    closest_to = index_of_element_closest;
                    temp_costs_array[i] = adjacencyMatrix[i][index_of_element_closest];
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

            // Update the minimum spanning tree and mark the selected vertex as done
            minimum_spanning_tree[ttfIndex][0] = (double) closest_to;
            minimum_spanning_tree[ttfIndex][1] = (double) index_of_element_closest;
            minimum_spanning_tree[ttfIndex][2] = temp_costs_array[index_of_element_closest];
            temp_costs_array[index_of_element_closest] = 0.0;
            doneIndexes[index_of_element_closest] = true;

            ttfIndex++;
        }

        return minimum_spanning_tree;
    }

    public void printAdjacencyMatrix(Double[][] adjacencyMatrix) {
        int numberOfVertices = adjacencyMatrix.length;

        for (int j = 0; j < numberOfVertices; j++) {
            for (int k = 0; k < numberOfVertices; k++) {
                if (adjacencyMatrix[j][k] == 0.0) {
                    System.out.print(String.format("  %.2f  |", adjacencyMatrix[j][k]));
                } else {
                    System.out.print(String.format("%.2f  |", adjacencyMatrix[j][k]));
                }
            }
            System.out.println();
        }
    }
}
