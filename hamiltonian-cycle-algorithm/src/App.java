import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Adacency matrix
        int[][] adjacencyMatrix = {
                // 1 2 3 4 5 6 7 8
                { 0, 1, 0, 1, 0, 0, 0, 1 }, // 1
                { 1, 0, 1, 0, 0, 0, 0, 1 }, // 2
                { 0, 1, 0, 1, 0, 0, 0, 0 }, // 3
                { 1, 0, 1, 0, 1, 0, 0, 0 }, // 4
                { 0, 0, 0, 1, 0, 1, 0, 1 }, // 5
                { 0, 0, 0, 0, 1, 0, 1, 0 }, // 6
                { 0, 0, 0, 0, 0, 1, 0, 1 }, // 7
                { 1, 1, 0, 0, 1, 0, 1, 0 }, // 8
        };
        printHamiltonianCycle(adjacencyMatrix);
    }

    public static void printHamiltonianCycle(int[][] matrix) {
        int start = 0;
        boolean[] visited = new boolean[matrix.length];
        visited[start] = true;
        boolean res = printHamiltonianCycle(matrix, start, start, visited);
        if (res) {
            System.out.print(" " + (1 + start));
        } else {
            System.out.println("No hamiltonian cycle found.");
        }
        System.out.println();
    }

    public static boolean printHamiltonianCycle(int[][] matrix, int original, int start, boolean[] visited) {

        if (allVisited(visited)) {
            if (matrix[start][original] == 1) {
                System.out.println("Hamiltonian path for givenadjacency matrix is: ");
                System.out.print(" " + (1 + original) + "---->");
                return true;
            } else {
                return false;
            }
        }

        List<Integer> canBeVisited = new ArrayList<Integer>();
        for (int x = 0; x < matrix[start].length; x++) {
            if (matrix[start][x] == 1 && !visited[x]) {
                canBeVisited.add(x);
            }
        }

        if (canBeVisited.isEmpty()) {
            return false;
        }

        for (Integer index : canBeVisited) {
            visited[index] = true;
            boolean res = printHamiltonianCycle(matrix, original, index, visited);
            if (res) {
                System.out.print(" " + (index + 1) + "---->");
                visited[index] = false;
                return res;
            }

        }
        return false;
    }

    public static boolean allVisited(boolean[] arry) {
        for (boolean ele : arry) {
            if (ele == false) {
                return false;
            }
        }
        return true;
    }
}
