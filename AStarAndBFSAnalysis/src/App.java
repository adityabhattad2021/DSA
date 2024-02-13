import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class App {

    public static class Node implements Comparable<Node> {

        Integer[] state;
        Node parent;
        String action;
        int level;
        int g; // Cost from start node to this node
        int h; // Heuristic value of this node (number of attacking pairs)
        int f; // Total cost (g + h)

        public Node(Integer[] state, Node parent, String action, int level, int g, int h) {
            this.state = state.clone();
            this.parent = parent;
            this.action = action;
            this.level = level;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        public static String getMatrix(Integer[] state) {
            StringBuilder sb = new StringBuilder();
            Integer numberOfColumns = (int) Math.sqrt(state.length);
            sb.append("\n");
            for (int x = 0; x < numberOfColumns; x++) {
                sb.append("     ");
                for (int y = 0; y < numberOfColumns; y++) {
                    sb.append(String.format("%02d", state[y + numberOfColumns * x]) + " ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n=====================================\n");
            sb.append("Current State:");
            sb.append(getMatrix(this.state));
            sb.append("Parent State:");
            if (this.parent != null) {
                sb.append(getMatrix(this.parent.state));
            } else {
                sb.append("None\n");
            }
            sb.append("Action: " + this.action + "\n");
            sb.append("Level: " + this.level + "\n");
            sb.append("\n=====================================\n");
            return sb.toString();
        }

        public int compareTo(Node other) {
            return this.f - other.f;
        }
    }

    private static void swap(Integer[] state, int i, int j) {
        int temp = state[i];
        state[i] = state[j];
        state[j] = temp;
    }

    private static int findPositionInGoal(Integer tile, Integer[] goalState) {

        return Arrays.asList(goalState).indexOf(tile);
    }

    private static int getInversionCountForNonStandardGoal(Integer[] initialState, Integer[] goalState) {
        int inversionCount = 0;
        for (int i = 0; i < initialState.length - 1; i++) {
            for (int j = i + 1; j < initialState.length; j++) {

                if (findPositionInGoal(initialState[i], goalState) > findPositionInGoal(initialState[j], goalState)) {
                    inversionCount++;
                }
            }
        }
        return inversionCount;
    }

    public static boolean isSolvable(Integer[] initialState, Integer[] goalState) {
        int inversionCount = getInversionCountForNonStandardGoal(initialState, goalState);
        int gridWidth = (int) Math.sqrt(initialState.length);
        if (gridWidth % 2 == 1) {
            // For odd grid sizes, puzzle is solvable if inversion count is even
            return inversionCount % 2 == 0;
        } else {
            // For even grid sizes, calculate the row of the blank tile from the bottom
            int blankIndex = Arrays.asList(initialState).indexOf(0);
            int blankRowFromBottom = gridWidth - (blankIndex / gridWidth);
            // Adjust solvability criteria based on the goal state
            return (inversionCount + blankRowFromBottom) % 2 != 0;
        }
    }

    private static Integer[] executeMove(Integer[] currentState, String action) {
        Integer[] newState = Arrays.copyOf(currentState, currentState.length);
        int blankIndex = getBlankIndex(currentState);

        int numberOfColumns = (int) Math.sqrt(currentState.length);

        if (action.equals("UP")) {
            swap(newState, blankIndex, blankIndex - numberOfColumns);
        } else if (action.equals("DOWN")) {
            swap(newState, blankIndex, blankIndex + numberOfColumns);
        } else if (action.equals("LEFT")) {
            swap(newState, blankIndex, blankIndex - 1);
        } else if (action.equals("RIGHT")) {
            swap(newState, blankIndex, blankIndex + 1);
        }
        return newState;
    }

    private static int getBlankIndex(Integer[] state) {
        for (int x = 0; x < state.length; x++) {
            if (state[x] == 0) {
                return x;
            }
        }
        return -1;
    }

    private static ArrayList<String> getAllPossibleActions(Integer[] state) {
        ArrayList<String> actions = new ArrayList<>();

        int blankIndex = getBlankIndex(state);

        int numberOfColumns = (int) Math.sqrt(state.length);

        if (blankIndex > (numberOfColumns - 1))
            actions.add("UP");
        if (blankIndex <= (state.length - numberOfColumns) - 1)
            actions.add("DOWN");
        if ((blankIndex + 1) % (numberOfColumns) != 1)
            actions.add("LEFT");
        if ((blankIndex + 1) % (numberOfColumns) != 0)
            actions.add("RIGHT");

        return actions;
    }

    private static boolean isFinalState(Integer[] currentState, Integer[] finalState) {
        return Arrays.equals(currentState, finalState);
    }

    private static ArrayList<Node> buildSolution(Node finalNode) {
        ArrayList<Node> path = new ArrayList<>();
        Node temp = finalNode;
        while (temp != null) {
            path.add(temp);
            temp = temp.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static int calculateHeuristic(Integer[] currentState, Integer[] finalState) {
        int manhattanDistance = 0;
        int sideLength = (int) Math.sqrt(currentState.length);
        for (int i = 0; i < currentState.length; i++) {
            int currentVal = currentState[i];
            if (currentVal != 0) { // Skip the blank tile
                int goalIndex = Arrays.asList(finalState).indexOf(currentVal);
                int currentRow = i / sideLength, currentCol = i % sideLength;
                int goalRow = goalIndex / sideLength, goalCol = goalIndex % sideLength;
                manhattanDistance += Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
            }
        }
        return manhattanDistance;
    }

    private static boolean containsState(ArrayList<Node> explored, Integer[] toCheck) {
        for (Node node : explored) {
            if (Arrays.equals(node.state, toCheck)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isExplored(Node currentState, ArrayList<Node> states) {
        for (Node ele : states) {
            if (Arrays.equals(currentState.state, ele.state)) {
                return true;
            }
        }
        return false;
    }

    public static int solveUsingAStar(Integer[] startState, Integer[] finalState) {
        if (isSolvable(startState, finalState)) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int exploredChildren = 1;
            Node initialNode = new Node(startState, null, "Initial", 0, 0, calculateHeuristic(startState, finalState));
            pq.add(initialNode);
            ArrayList<Node> exploredNodes = new ArrayList<>();

            while (!pq.isEmpty()) {

                Node currentNode = pq.poll();
                exploredNodes.add(currentNode);
                exploredChildren++;
                System.out.println(currentNode);

                if (isFinalState(currentNode.state, finalState)) {
                    ArrayList<Node> solution = buildSolution(currentNode);
                    if (solution != null) {
                        System.out.println(
                                "----------------------------------------------------------------------------------------");
                        for (Node node : solution) {
                            System.out.println(
                                    "\n############################################################################\nCurrent State:\n"
                                            + Node.getMatrix(node.state) + "\nLevel: " + node.level + "\nAction: "
                                            + node.action
                                            + "\n############################################################################");
                        }
                        System.out.println(
                                "----------------------------------------------------------------------------------------");
                    }
                    return exploredChildren;
                }

                // Explore the child states
                ArrayList<String> possibleActions = getAllPossibleActions(currentNode.state);
                for (String action : possibleActions) {
                    Integer[] newState = executeMove(currentNode.state, action);

                    Node newNode = new Node(newState, currentNode, action, currentNode.level + 1, currentNode.g + 1,
                            calculateHeuristic(newState, finalState));
                    if (!isExplored(newNode, exploredNodes)) {
                        pq.add(newNode);
                    }
                }
            }
            return -1;
        } else {
            System.out.println("No Solution Possible");
            return -1;
        }

    }

    public static int solvePuzzleUsingBFS(Integer[] startState, Integer[] finalState) {
        if (isSolvable(startState, finalState)) {
            int exploredChildren = 1;
            Node initialNode = new Node(startState, null, "Initial", 0, 0, 0);

            // If initial node is itself the solution node.
            if (isFinalState(initialNode.state, finalState))
                return exploredChildren;

            Queue<Node> frontier = new LinkedList<>();
            ArrayList<Node> exploredNodes = new ArrayList<>();
            frontier.add(initialNode);

            while (!frontier.isEmpty()) {

                Node currentNode = frontier.poll();
                exploredNodes.add(currentNode);
                exploredChildren++;
                System.err.println(currentNode);

                ArrayList<String> possibleActions = getAllPossibleActions(currentNode.state);
                for (String action : possibleActions) {
                    Integer[] newMove = executeMove(currentNode.state, action);

                    // Check if the newly generated state has already been explored.
                    if (!containsState(exploredNodes, newMove)) {
                        Node newNode = new Node(newMove, currentNode, action, currentNode.level + 1, 0, 0);
                        // Check if the required state is the final state.
                        if (isFinalState(newNode.state, finalState)) {
                            ArrayList<Node> solution = buildSolution(newNode);
                            if (solution != null) {
                                System.out.println(
                                        "----------------------------------------------------------------------------------------");
                                for (Node node : solution) {
                                    System.out.println(
                                            "\n############################################################################\nCurrent State:\n"
                                                    + Node.getMatrix(node.state) + "\nLevel: " + node.level
                                                    + "\nAction: " + node.action
                                                    + "\n############################################################################");
                                }
                            }
                            System.out.println(
                                    "----------------------------------------------------------------------------------------");
                            return exploredChildren;
                        }

                        if (!isExplored(newNode, exploredNodes)) {
                            frontier.add(newNode);
                        }
                    }
                }
            }
            return -1;
        } else {
            System.out.println("No Solution Possible");
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {

        Integer[] startState1 = { 1, 2, 0, 3, 4, 5, 6, 7, 8 };
        Integer[] goalState1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        Integer[] startState2 = { 1, 2, 3, 5,4, 7, 6, 8, 0 };
        Integer[] goalState2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        Integer[] startState3 = { 1, 4, 2, 3, 5, 6, 7, 8, 0 };
        Integer[] goalState3 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

        int noOfStatesInAStar1 = solveUsingAStar(startState1, goalState1);
        int noOfStatesInBFS1 = solvePuzzleUsingBFS(startState1, goalState1);

        int noOfStatesInAStar2 = solveUsingAStar(startState2, goalState2);
        int noOfStatesInBFS2 = solvePuzzleUsingBFS(startState2, goalState2);

        int noOfStatesInAStar3 = solveUsingAStar(startState3, goalState3);
        int noOfStatesInBFS3 = solvePuzzleUsingBFS(startState3, goalState3);

        System.err.println("No of states in A Star: " + noOfStatesInAStar1);
        System.err.println("No of states in BFS: " + noOfStatesInBFS1);
        System.err.println("No of states in A Star: " + noOfStatesInAStar2);
        System.err.println("No of states in BFS: " + noOfStatesInBFS2);
        System.err.println("No of states in A Star: " + noOfStatesInAStar3);
        System.err.println("No of states in BFS: " + noOfStatesInBFS3);
    }
}